package hello.jdbc.connection;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
public class ConnectionTest {

  @Test
  void driverManager() throws SQLException {
    final Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    final Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    log.info("connection={},class={}",connection,connection.getClass());
    log.info("connection1={},class={}",connection1,connection1.getClass());
  }

  @Test
  void dataSourceDriverManager() throws SQLException {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,
        USERNAME, PASSWORD);
    useDataSource(dataSource);
  }

  @Test
  void dataSourceConnectionPool() throws SQLException, InterruptedException {
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    dataSource.setMaximumPoolSize(10);
    dataSource.setPoolName("MyPool");

    useDataSource(dataSource);
    Thread.sleep(1000);

  }

  private void useDataSource(DataSource dataSource) throws SQLException {
    final Connection connection = dataSource.getConnection();
    final Connection connection1 = dataSource.getConnection();
    log.info("connection={},class={}",connection,connection.getClass());
    log.info("connection1={},class={}",connection1,connection1.getClass());
  }

}
