package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/***
 * 트랜잭션 - 트랜잭션 매니저
 */
@RequiredArgsConstructor
@Slf4j
public class MemberServiceV3_1 {

//  private final DataSource dataSource;
  private final PlatformTransactionManager transactionManager;
  private final MemberRepositoryV3 memberRepositoryV3;

  public void accountTransfer(String fromId,String toId,int money) throws SQLException {

    final TransactionStatus status = transactionManager.getTransaction(
        new DefaultTransactionDefinition());

    try{
      //비지니스
      final Member fromMember = memberRepositoryV3.findById(fromId);
      final Member toMember = memberRepositoryV3.findById(toId);

      memberRepositoryV3.update(fromId,fromMember.getMoney()- money);
      validation(toMember);
      memberRepositoryV3.update(toId,fromMember.getMoney()+ money);
      transactionManager.commit(status);
    }catch(Exception e){
      transactionManager.rollback(status);
      throw new IllegalStateException(e);
    }

  }

  private void release(Connection con) {
    if(con != null){
      try{
        con.setAutoCommit(true);
        con.close();
      }catch(Exception e){
        log.info("error",e);
      }
    }
  }

  private void validation(Member toMember) {
    if(toMember.getMemberId().equals("ex")){
      throw new IllegalStateException("이체중 예외 발생");
    }
  }

}
