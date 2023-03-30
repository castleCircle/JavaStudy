package study.querydsl;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
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

}
