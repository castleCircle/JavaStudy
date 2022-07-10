package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/***
 * 트랜잭션 - @Transactional AOP
 */

@Slf4j
public class MemberServiceV3_3 {

  private final MemberRepositoryV3 memberRepositoryV3;

  public MemberServiceV3_3(
      MemberRepositoryV3 memberRepositoryV3) {
    this.memberRepositoryV3 = memberRepositoryV3;
  }

  @Transactional
  public void accountTransfer(String fromId,String toId,int money) throws SQLException {
        bizLogic(fromId, toId, money);
  }

  private void bizLogic(String fromId, String toId, int money) throws SQLException {
    final Member fromMember = memberRepositoryV3.findById(fromId);
    final Member toMember = memberRepositoryV3.findById(toId);

    memberRepositoryV3.update(fromId, fromMember.getMoney() - money);
    validation(toMember);
    memberRepositoryV3.update(toId,fromMember.getMoney()+ money);
  }

  private void validation(Member toMember) {
    if(toMember.getMemberId().equals("ex")){
      throw new IllegalStateException("이체중 예외 발생");
    }
  }

}
