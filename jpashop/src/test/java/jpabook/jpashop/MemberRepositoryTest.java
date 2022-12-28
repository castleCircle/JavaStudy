package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {
//
//  @Autowired MemberRepository memberRepository;
//
//  @Test
//  @Transactional
//  @Rollback(value = false)
//  public void testMember() throws Exception{
//
//    //given
//    Member member = new Member();
//    member.setUsername("memberA");
//
//    Long savedId = memberRepository.save(member);
//
//    Member findMember = memberRepository.find(savedId);
//
//    System.out.println("============");
//    System.out.println(findMember);
//
//    org.assertj.core.api.Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//
//  }

}