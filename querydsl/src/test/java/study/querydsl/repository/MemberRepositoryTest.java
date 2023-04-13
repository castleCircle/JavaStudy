package study.querydsl.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {


  @Autowired
  EntityManager em;

  @Autowired
  MemberRepository memberRepository;

  @Test
  public void basicTest(){
    Member member = new Member("member1",10);
    memberRepository.save(member);

    Member findMember = memberRepository.findById(member.getId()).get();
    assertThat(findMember).isEqualTo(member);

    List<Member> result1 =memberRepository.findAll();
    assertThat(result1).containsExactly(member);

    final List<Member> result2 = memberRepository.findByUsername("member1");
    assertThat(result2).containsExactly(member);

  }


  @Test
  public void searchTest(){

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

    MemberSearchCondition condition = new MemberSearchCondition();
    condition.setAgeGoe(35);
    condition.setAgeLoe(40);
    condition.setTeamName("teamB");

    final List<MemberTeamDto> result = memberRepository.search(condition);

    assertThat(result).extracting("username").containsExactly("member4");

  }

  @Test
  public void searchSimpleTest(){

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

    
    MemberSearchCondition condition = new MemberSearchCondition();
    PageRequest pageRequest = PageRequest.of(0,3);

    final Page<MemberTeamDto> result = memberRepository.searchPageSimple(condition,
        pageRequest);
//
//    final Page<MemberTeamDto> result = memberRepository.searchPageComplex(condition,
//        pageRequest);

//    assertThat(result.getSize()).isEqualTo(3);
//    assertThat(result.getContent()).extracting("username").containsExactly("member1","member2","member3");

  }

  @Test
  public void searchComplexTest(){

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


    MemberSearchCondition condition = new MemberSearchCondition();
    PageRequest pageRequest = PageRequest.of(0,6);

    final Page<MemberTeamDto> result = memberRepository.searchPageComplex(condition,
        pageRequest);

    assertThat(result.getContent().size()).isEqualTo(4);
//    assertThat(result.getContent()).extracting("username").containsExactly("member1","member2","member3");

  }

  /**
   * QuerydslPredicateExecutor 을 사용하면 쿼리를 좀 편하게 사용할수 있지만
   * 좀만 더 들어가면 결과적으로는 dto나 이런걸 만들어서 repository로 넘겨서 작업해야해서
   * 사실 실무에서는 쓰기 힘들다
   */
  @Test
  public void query(){

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

    QMember member = QMember.member;
    final Iterable<Member> members = memberRepository.findAll(member.age
        .between(10, 40).and(member.username.eq("member1")));

    for(Member tempMember : members){
      System.out.println("members = " + tempMember);
    }

  }


}
