package hello.springtx.propagation;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

@Slf4j
@SpringBootTest
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  LogRepository logRepository;

  /**
   * memberService off
   * memberREpository on
   * logREpository on
   */
  @Test
  void outerTxOff_success(){

    String username = "outerTxOff_success";

    memberService.joinV1(username);

    assertTrue(memberRepository.find(username).isPresent());
    assertTrue(logRepository.find(username).isPresent());

  }

  /**
   * memberService off
   * memberREpository on
   * logREpository on Exception
   */
  @Test
  void outerTxOff_fail(){

    String username = "로그예외outerTxOff_success";

    org.assertj.core.api.Assertions.assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

    assertTrue(memberRepository.find(username).isPresent());
    assertTrue(logRepository.find(username).isEmpty());

  }

  /**
   * memberService on
   * memberREpository off
   * logREpository off
   */
  @Test
  void singleTx(){

    String username = "singleTx_success";

    memberService.joinV1(username);

    assertTrue(memberRepository.find(username).isPresent());
    assertTrue(logRepository.find(username).isPresent());

  }

  /**
   * memberService on
   * memberREpository on
   * logREpository on
   */
  @Test
  void outerTxOn_success(){

    String username = "outerTxOn_success";

    memberService.joinV1(username);

    assertTrue(memberRepository.find(username).isPresent());
    assertTrue(logRepository.find(username).isPresent());

  }

  /**
   * memberService on
   * memberREpository on
   * logREpository on Exception
   */
  @Test
  void outerTxOn_fail(){

    String username = "로그예외outerTxOn_success";

    org.assertj.core.api.Assertions.assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

    assertTrue(memberRepository.find(username).isEmpty());
    assertTrue(logRepository.find(username).isEmpty());

  }


  /**
   * memberService on
   * memberREpository on
   * logREpository on Exception
   */
  @Test
  void recoverException_fail(){

    String username = "로그예외_recoverException_fail";

    org.assertj.core.api.Assertions.assertThatThrownBy(()->memberService.joinV2(username)).isInstanceOf(
        UnexpectedRollbackException.class);

//    memberService.joinV2(username);

    assertTrue(memberRepository.find(username).isEmpty());
    assertTrue(logRepository.find(username).isEmpty());

  }

  /**
   * memberService on
   * memberREpository on
   * logREpository on(requires_new) Exception
   */
  @Test
  void recoverException_success(){

    String username = "로그예외_recoverException_success";

    memberService.joinV2(username);

    assertTrue(memberRepository.find(username).isPresent());
    assertTrue(logRepository.find(username).isEmpty());

  }

}