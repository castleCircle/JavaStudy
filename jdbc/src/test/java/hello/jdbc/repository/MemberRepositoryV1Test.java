package hello.jdbc.repository;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class MemberRepositoryV1Test {

  MemberRepositoryV1 repository;

  @BeforeEach
  void beforeEach(){
    //기본 DriverManager - 항상 새로운 커넥션 획득
//    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);

    //connection pooling
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    repository = new MemberRepositoryV1(dataSource);
  }

  @Test
  void crud() throws SQLException {
    Member member = new Member("memberV3",10000);
    repository.save(member);

    //FindById
    final Member findMember = repository.findById(member.getMemberId());
    log.info("findMember={}",findMember);

    Assertions.assertThat(findMember).isEqualTo(member);

    repository.update(member.getMemberId(),20000);
    final Member updateMember = repository.findById(member.getMemberId());
    Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

    repository.delete(member.getMemberId());
    Assertions.assertThatThrownBy(()->repository.findById(member.getMemberId())).isInstanceOf(
        NoSuchElementException.class);

    try{
      Thread.sleep(1000);
    }catch (Exception e){

    }

  }



}