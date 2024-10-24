package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

/**
 *
 * SQLExceptioonTranslator
 */

@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository{

  private final DataSource dataSource;
  private final SQLExceptionTranslator exTranslator;

  public MemberRepositoryV4_2(DataSource dataSource) {
    this.dataSource = dataSource;
    this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
  }

  @Override
  public Member save(Member member){
    String sql = "insert into member(member_id,money) values (?, ?)";

    Connection connection = null;
    PreparedStatement pstmt = null;

    try {
      connection = getConnection();
      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1,member.getMemberId());
      pstmt.setInt(2,member.getMoney());
      pstmt.executeUpdate();
      return member;
    }catch (SQLException e){
      throw exTranslator.translate("save",sql,e);
    }finally {
      close(connection,pstmt,null);
    }

  }
  @Override
  public Member findById(String memberId) {
    String sql = "select * from member where member_id=?";

    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs =null;

    try{
      connection = getConnection();
      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1,memberId);

      rs = pstmt.executeQuery();
      if(rs.next()){
        Member member = new Member();
        member.setMemberId(rs.getString("member_id"));
        member.setMoney(rs.getInt("money"));
        return member;
      }else {
        throw new NoSuchElementException("member not found memberId="+memberId);
      }

    }catch (SQLException e){
      log.error("db error",e);
      throw exTranslator.translate("findByID",sql,e);
    }finally {
      close(connection,pstmt,null);
    }

  }

  @Override
  public void update(String memberId,int money){
    String sql = "update member set money=? where member_id=?";

    Connection connection = null;
    PreparedStatement pstmt = null;

    try {
      connection = getConnection();
      pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1,money);
      pstmt.setString(2,memberId);
      final int resultSize = pstmt.executeUpdate();
      log.info("resultSize={}",resultSize);
    }catch (SQLException e){
      log.error("db error",e);
      throw exTranslator.translate("update",sql,e);
    }finally {
      close(connection,pstmt,null);
    }
  }

  @Override
  public void delete(String memberId) {
    String sql = "delete from member where member_id=?";

    Connection connection = null;
    PreparedStatement pstmt = null;

    try {
      connection = getConnection();
      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1,memberId);
      pstmt.executeUpdate();
    }catch (SQLException e){
      log.error("db error",e);
      throw exTranslator.translate("delete",sql,e);
    }finally {
      close(connection,pstmt,null);
    }
  }

  private void close(Connection con, Statement stmt, ResultSet rs){
    JdbcUtils.closeResultSet(rs);
    JdbcUtils.closeStatement(stmt);
    DataSourceUtils.releaseConnection(con,dataSource);
  }

  private Connection getConnection() throws SQLException {
    //트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
    final Connection connection = DataSourceUtils.getConnection(dataSource);
    log.info("get connnection = {},class={}",connection,connection.getClass());
    return connection;
  }

}
