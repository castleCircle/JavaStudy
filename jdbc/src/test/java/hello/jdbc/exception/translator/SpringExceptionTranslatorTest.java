package hello.jdbc.exception.translator;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;

import hello.jdbc.exception.translator.ExTranslatorV1Test.Repository;
import hello.jdbc.exception.translator.ExTranslatorV1Test.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

@Slf4j
public class SpringExceptionTranslatorTest {

  DataSource dataSource;

  @BeforeEach
  void init(){
    dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
  }

  @Test
  void sqlExceptionErrorCode(){
    String sql = "select bad grammer";


    try{
      Connection con = dataSource.getConnection();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.executeQuery();
    }catch (SQLException e){
      Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);
      int errorCode = e.getErrorCode();
      log.info("errorCode={}",e);
      log.info("error",e);
    }
  }

  @Test
  void exceptionTranslator(){
    String sql = "select bad grammer";

    try{
      Connection con = dataSource.getConnection();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.executeUpdate();
    }catch(SQLException e){
      Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);
      final SQLErrorCodeSQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(
          dataSource);

      final DataAccessException resultEx = exTranslator.translate("select", sql, e);
      log.info("resultEx",resultEx);

      Assertions.assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);

    }
  }


}
