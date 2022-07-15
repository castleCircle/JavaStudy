package hello.jdbc.exception.translator;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;

import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import hello.jdbc.repository.ex.MyDuplicateKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

@Slf4j
public class ExTranslatorV1Test {

  Repository repository;
  Service service;

  @BeforeEach
  void init(){
    final DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    repository = new Repository(dataSource);
    service = new Service(repository);
  }

  @Test
  void duplicateKeySave(){
    service.create("myID");
    service.create("myID");
  }


  @Slf4j
  @RequiredArgsConstructor
  static class Service{
    private final Repository repository;

    public void create(String memberId){
      try {
        repository.save(new Member(memberId, 0));
        log.info("saveId={}", memberId);
      }catch (MyDuplicateKeyException e){
        log.info("키 중복, 복구 시도");
        final String retryID = generateNewId(memberId);
        repository.save(new Member(retryID,0));
      }catch (MyDbException e){
        log.info("db",e);
      }
    }

    private String generateNewId(String memberId){
      return memberId + new Random().nextInt(100);
    }
  }



  @RequiredArgsConstructor
  static class Repository{

    private final DataSource dataSource;

    public Member save(Member member) {
      String sql = "insert into member(member_id,money) values(?,?)";
      Connection connection =null;
      PreparedStatement pstmt = null;

      try{
        connection = dataSource.getConnection();
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,member.getMemberId());
        pstmt.setInt(2,member.getMoney());
        pstmt.executeUpdate();
        return member;
      }catch (SQLException e){

        if(e.getErrorCode() == 23505){
          throw new MyDuplicateKeyException(e);
        }
        throw new MyDbException(e);

      }finally {
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(connection);
      }
    }
  }

}
