package study.querydsl;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

  @Autowired
  EntityManager em;

  JPAQueryFactory queryFactory;

  @BeforeEach
  public void before(){

    queryFactory= new JPAQueryFactory(em);


    Team teamA = new Team("teamA");
    Team teamB = new Team("teamB");
    em.persist(teamA);
    em.persist(teamB);

    Member member1 = new Member("member1",10,teamA);
    Member member2 = new Member("member2",20,teamA);

    Member member3 = new Member("member3",30,teamB);
    Member member4 = new Member("member4",40,teamB);

    em.persist(member1);
    em.persist(member2);
    em.persist(member3);
    em.persist(member4);
  }

  @Test
  public void startJPQL(){
    final Member findMember = em.createQuery(
            "select m from Member m where m.username = :username", Member.class)
        .setParameter("username", "member1")
        .getSingleResult();

    assertThat(findMember.getUsername()).isEqualTo("member1");
  }

  @Test
  public void startQuerydsl(){
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    final Member findMember = queryFactory.select(member)
        .from(member)
        .where(member.username.eq("member1"))
        .fetchOne();

    assertThat(findMember.getUsername()).isEqualTo("member1");
  }

  @Test
  public void search(){
    final Member findMEmber = queryFactory.selectFrom(member)
        .where(member.username.eq("member1").and(member.age.eq(10)))
        .fetchOne();

    assertThat(findMEmber.getUsername()).isEqualTo("member1");
  }

  @Test
  public void searchAndParam(){
    final Member findMEmber = queryFactory.selectFrom(member)
        .where(
            member.username.eq("member1"),
            member.age.eq(10),
            null
        )
        .fetchOne();

    assertThat(findMEmber.getUsername()).isEqualTo("member1");
  }

  @Test
  public void resultFetch(){
//    final List<Member> fetch = queryFactory.selectFrom(member)
//        .fetch();
//
//    final Member fetchOne = queryFactory.selectFrom(QMember.member)
//        .fetchOne();
//
//    final Member fetchFirst = queryFactory.selectFrom(QMember.member)
//        .fetchFirst();

    final QueryResults<Member> results = queryFactory.selectFrom(member)
        .fetchResults();

    results.getTotal();
  }

  /**
   * 회원 정렬 순서
   * 1. 회원 나이 내림차순
   * 2. 회원 이름 오름차순
   * 단 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
   */
  @Test
  public void sort(){
      em.persist(new Member(null,100));
      em.persist(new Member("member5",100));
      em.persist(new Member("member6",100));

    final List<Member> result = queryFactory.selectFrom(member)
        .where(member.age.eq(100))
        .orderBy(member.age.desc(), member.username.asc().nullsLast())
        .fetch();

    Member member5 = result.get(0);
    Member member6 = result.get(1);
    Member memberNull = result.get(2);

    assertThat(member5.getUsername()).isEqualTo("member5");
    assertThat(member6.getUsername()).isEqualTo("member6");
  }

  @Test
  public void paging1(){
    final List<Member> fetch = queryFactory.selectFrom(member)
        .orderBy(member.username.desc())
        .offset(1)
        .limit(2)
        .fetch();

    assertThat(fetch.size()).isEqualTo(2);
  }

  @Test
  public void paging2(){
    final QueryResults<Member> queryResults = queryFactory.selectFrom(member)
        .orderBy(member.username.desc())
        .offset(1)
        .limit(2)
        .fetchResults();

    assertThat(queryResults.getTotal()).isEqualTo(4);
    assertThat(queryResults.getLimit()).isEqualTo(2);
    assertThat(queryResults.getResults()).isEqualTo(1);
  }

  @Test
  public void aggregation(){
    final List<Tuple> result = queryFactory
        .select(
            member.count(),
            member.age.sum(),
            member.age.avg(),
            member.age.max(),
            member.age.min()
        )
        .from(member)
        .fetch();

    Tuple tuple = result.get(0);
    assertThat(tuple.get(member.count())).isEqualTo(4);
  }

  /**
   * 팀의 이름과 각 팀의 평균 연령을 구해라.
   * @throws Exception
   */
  @Test
  public void group() throws Exception{
    final List<Tuple> result = queryFactory
        .select(team.name, member.age.avg())
        .from(member)
        .join(member.team, team)
        .groupBy(team.name)
        .fetch();

    Tuple teamA = result.get(0);
    Tuple teamB = result.get(1);

    final StringPath name = team.name;

    assertThat(teamA.get(team.name)).isEqualTo("teamA");
    assertThat(teamA.get(member.age.avg())).isEqualTo(15);

    assertThat(teamB.get(team.name)).isEqualTo("teamB");
    assertThat(teamB.get(member.age.avg())).isEqualTo(35);

  }

  @Test
  public void join(){
    final List<Member> result = queryFactory
        .selectFrom(member)
        .join(member.team, team)
        .where(team.name.eq("teamA"))
        .fetch();

    assertThat(result)
        .extracting("username")
        .containsExactly("member1","member2");
  }

  /**
   * 세타 조인
   * 회원의 이름이 팀이름과 같은 회원 조회
   */
  @Test
  public void theta_join(){
    em.persist(new Member("teamA"));
    em.persist(new Member("teamB"));

    final List<Member> result = queryFactory.select(member)
        .from(member, team)
        .where(member.username.eq(team.name))
        .fetch();
  }

  /**
   * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
   * JPQL: select m, t from Member m left join m.team t on t.name = 'teamA'
   */
  @Test
  public void join_on_filtering(){
    final List<Tuple> result = queryFactory
        .select(member, team)
        .from(member)
        .leftJoin(member.team, team)
        .on(team.name.eq("teamA"))
        .fetch();

    for(Tuple tuple: result){
      System.out.println("tuple" + tuple);
    }
  }

  /**
   * 연관관게 없는 엔티티 외부 조인
   */
  @Test
  public void join_on_no_relation(){
    em.persist(new Member("teamA"));
    em.persist(new Member("teamB"));
    em.persist(new Member("teamC"));

    final List<Tuple> fetch = queryFactory.select(member, team)
        .from(member)
        .leftJoin(team).on(member.username.eq(team.name))
        .fetch();

    for(Tuple tuple : fetch){
      System.out.println("tuple = " + tuple);
    }
  }

  @PersistenceUnit
  EntityManagerFactory emf;

  @Test
  public void fetchJoinNo(){
    em.flush();
    em.clear();

    final Member member = queryFactory.selectFrom(QMember.member)
        .where(QMember.member.username.eq("member1"))
        .fetchOne();

    final boolean loaded = emf.getPersistenceUnitUtil().isLoaded(member.getTeam());

    assertThat(loaded).as("폐치 조인 미적용").isFalse();

  }

  @Test
  public void fetchJoinUse(){
    em.flush();
    em.clear();

    final Member findMember = queryFactory.selectFrom(member)
        .join(member.team,team).fetchJoin()
        .where(member.username.eq("member1"))
        .fetchOne();

    final boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());

    assertThat(loaded).as("폐치 조인 적용").isTrue();
  }

  /**
   * 나이가 가장 많은 회원 조회
   */
  @Test
  public void subQuery(){

    QMember memberSub = new QMember("memberSub");

    final List<Member> result = queryFactory
        .selectFrom(member)
        .where(member.age.eq(
            JPAExpressions.select(memberSub.age.max())
                .from(memberSub)
        ))
        .fetch();

    assertThat(result).extracting("age")
        .containsExactly(40);

  }

  /**
   * 나이가 평균 이상인 회원
   */
  @Test
  public void subQueryGoe(){

    QMember memberSub = new QMember("memberSub");

    final List<Member> result = queryFactory
        .selectFrom(member)
        .where(member.age.goe(
            JPAExpressions.select(memberSub.age.avg())
                .from(memberSub)
        ))
        .fetch();

    assertThat(result).extracting("age")
        .containsExactly(30,40);

  }

  @Test
  public void selectSubQuery(){

    QMember memberSub = new QMember("memberSub");

    final List<Tuple> fetch = queryFactory
        .select(member.username,
            JPAExpressions.select(memberSub.age.avg())
                .from(memberSub)
        )
        .from(member)
        .fetch();
  }

  @Test
  public void basicCase(){
    final List<String> fetch = queryFactory
        .select(member.age.when(10).then("열살")
            .when(20).then("스무살")
            .otherwise("기타"))
        .from(member)
        .fetch();

    for(String s : fetch){
      System.out.println("s = " + s);
    }
  }

  @Test
  public void complexCase(){
    final List<String> fetch = queryFactory
        .select(new CaseBuilder()
            .when(member.age.between(0,20)).then("0~20살")
            .when(member.age.between(21,30)).then("21~30살")
            .otherwise("기타"))
        .from(member)
        .fetch();

    for(String s : fetch){
      System.out.println("s = " + s);
    }
  }

  @Test
  public void constant(){
    final List<Tuple> result = queryFactory
        .select(member.username, Expressions.constant("A"))
        .from(member)
        .fetch();

    for (Tuple tuple : result) {
      System.out.println("tuple=" + tuple);
    }
  }

  @Test
  public void concat(){
    final List<String> result = queryFactory
        .select(member.username.concat("_").concat(member.age.stringValue()))
        .from(member)
        .where(member.username.eq("member1"))
        .fetch();

    for (String s : result) {
      System.out.println("s= " + s);
    }
  }

  @Test
  public void simpleProjection(){
    final List<String> result = queryFactory
        .select(member.username)
        .from(member)
        .fetch();

    for(String s: result){
      System.out.println("s =" + s);
    }
  }

  @Test
  public void tupleProjection(){
    final List<Tuple> fetch = queryFactory
        .select(member.username, member.age)
        .from(member)
        .fetch();

    for (Tuple tuple : fetch) {
      final String s = tuple.get(member.username);
      final Integer integer = tuple.get(member.age);

      System.out.println(s);
      System.out.println(integer);
    }
  }

  @Test
  public void findDtoByJPQL(){
    final List<MemberDto> result = em.createQuery(
            "select new study.querydsl.dto.MemberDto(m.username,m.age) from Member m", MemberDto.class)
        .getResultList();

    for (MemberDto memberDto : result) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  public void findDtoBySetter(){
    final List<MemberDto> result = queryFactory
        .select(Projections.bean(MemberDto.class, member.username, member.age))
        .from(member)
        .fetch();

    for (MemberDto memberDto : result) {
      System.out.println("memberDto =" + memberDto);

    }
  }

  @Test
  public void findDtoByField(){
    final List<MemberDto> result = queryFactory
        .select(Projections.fields(MemberDto.class, member.username, member.age))
        .from(member)
        .fetch();

    for (MemberDto memberDto : result) {
      System.out.println("memberDto =" + memberDto);
    }
  }

  @Test
  public void findDtoByConstructor(){
    final List<MemberDto> result = queryFactory
        .select(Projections.constructor(MemberDto.class,
            member.username,
            member.age))
        .from(member)
        .fetch();

    for (MemberDto memberDto : result) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  public void findUserDto(){
    final List<UserDto> result = queryFactory
        .select(Projections.fields(UserDto.class,
            member.username.as("name"),
            member.age))
        .from(member)
        .fetch();

    for (UserDto userDto : result) {
      System.out.println(userDto);
    }

  }

  @Test
  public void findUserDtoSubquery(){

    QMember memberSub = new QMember("memberSub");

    final List<UserDto> result = queryFactory
        .select(
            Projections.fields(
            UserDto.class,
            member.username.as("name"),
        ExpressionUtils.as(
            JPAExpressions.select(memberSub.age.max())
                .from(memberSub),"age")
            )
        )
        .from(member)
        .fetch();

    for (UserDto userDto : result) {
      System.out.println(userDto);
    }

  }

  @Test
  public void findDtoByQueryProjection(){
    final List<MemberDto> result = queryFactory
        .select(new QMemberDto(member.username, member.age))
        .from(member)
        .fetch();

    for (MemberDto memberDto : result) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  public void dynamicQuery_BooleanBuilder(){
    String usernameParam = "member1";
//    Integer ageParam = 10;
    Integer ageParam = null;

    List<Member> result = searchMember1(usernameParam,ageParam);
    assertThat(result.size()).isEqualTo(1);
  }

  private List<Member> searchMember1(String usernameCond, Integer ageCond) {

    BooleanBuilder builder = new BooleanBuilder();

    if(usernameCond != null){
      builder.and(member.username.eq(usernameCond));
    }

    if(ageCond !=null){
      builder.and(member.age.eq(ageCond));
    }

    return queryFactory
        .selectFrom(member)
        .where(builder)
        .fetch();
  }


  @Test
  public void dynamicQuery_whereParam(){
    String usernameParam = "member1";
    Integer ageParam = null;


    List<Member> result = searchMember2(usernameParam,ageParam);
    assertThat(result.size()).isEqualTo(1);

  }

  /**
   * where 조건에 null은 무시됨
   */
  private List<Member> searchMember2(String usernameParam, Integer ageParam) {
    return queryFactory
        .selectFrom(member)
        .where(usernameEq(usernameParam),ageEq(ageParam))
        .fetch();
  }

  private BooleanExpression usernameEq(String usernameParam) {
    return usernameParam == null ? null : member.username.eq(usernameParam);
  }

  private BooleanExpression ageEq(Integer ageParam) {
    return ageParam != null ? member.age.eq(ageParam) : null;
  }

  private BooleanExpression allEq(String usernameParam, Integer ageCond){
    return usernameEq(usernameParam).and(ageEq(ageCond));
  }

  @Test
  public void bulkUpdate(){

    //member1 = 10

    final long count = queryFactory
        .update(member)
        .set(member.username, "비회원")
        .where(member.age.lt(28))
        .execute();

    //여기서는 @BeforeEach에 데이터를 기본적으로 넣어주고 있기 떄문
    //이것을 하지 않으면 영속성 컨텍스트에 들어가 있던 데이터가 영속성 컨텍스트에 데이터가 들어가 있어서 다시 조회할때 update 되지 않기떄문에
//    //기존에 있던 데이터가 영속성 컨텍스트에 남아있음
//    em.flush();
//    em.clear();

    final List<Member> result = queryFactory
        .selectFrom(member)
        .fetch();

    for (Member member1 : result) {
      System.out.println("result = " + member1);
    }

  }

  @Test
  public void bulkAdd(){
    final long count = queryFactory
        .update(member)
        .set(member.age, member.age.add(1))
        .execute();
  }

  @Test
  public void bulkDelete(){
    final long execute = queryFactory
        .delete(member)
        .where(member.age.gt(18))
        .execute();
  }

  @Test
  public void sqlFunction(){
    final List<String> result = queryFactory
        .select(
            Expressions.stringTemplate("function('replace',{0},{1},{2})"
                , member.username, "member", "M"))
        .from(member)
        .fetch();

    for (String s : result) {
      System.out.println("s = " + s);
    }
  }

  @Test
  public void sqlFunction2(){
    final List<String> result = queryFactory
        .select(member.username)
        .from(member)
//        .where(member.username.eq(
//            Expressions.stringTemplate("function('lower',{0})", member.username)))
        .where(member.username.eq(member.username.lower()))
        .fetch();

    for (String s : result) {
      System.out.println("s = " + s);
    }
  }
}
