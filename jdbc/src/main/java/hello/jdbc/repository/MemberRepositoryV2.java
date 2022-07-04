package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

/**
 *
 * JDBC - DataSource 사용
 */

@Slf4j
public class MemberRepositoryV2 {

  private final DataSource dataSource;

  public MemberRepositoryV2(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Member save(Member member) throws SQLException {
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
      log.error("db error",e);
      throw e;
    }finally {
      close(connection,pstmt,null);
    }

  }

  public Member findById(String memberId) throws SQLException {
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
      throw e;
    }finally {
      close(connection,pstmt,null);
    }

  }

  public Member findById(Connection connection,String memberId) throws SQLException {
    String sql = "select * from member where member_id=?";

    PreparedStatement pstmt = null;
    ResultSet rs =null;

    try{
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
      throw e;
    }finally {
      //connection은 여기서 닫지 않는다.
      JdbcUtils.closeResultSet(rs);
      JdbcUtils.closeStatement(pstmt);
//      JdbcUtils.closeConnection(con);
    }

  }

  public void update(String memberId,int money) throws SQLException {
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
      throw e;
    }finally {
      close(connection,pstmt,null);
    }
  }

  public void update(Connection connection,String memberId,int money) throws SQLException {
    String sql = "update member set money=? where member_id=?";

    PreparedStatement pstmt = null;

    try {
      pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1,money);
      pstmt.setString(2,memberId);
      final int resultSize = pstmt.executeUpdate();
      log.info("resultSize={}",resultSize);
    }catch (SQLException e){
      log.error("db error",e);
      throw e;
    }finally {
      JdbcUtils.closeStatement(pstmt);
    }
  }

  public void delete(String memberId) throws SQLException {
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
      throw e;
    }finally {
      close(connection,pstmt,null);
    }
  }

  private void close(Connection con, Statement stmt, ResultSet rs){

    JdbcUtils.closeResultSet(rs);
    JdbcUtils.closeStatement(stmt);
    JdbcUtils.closeConnection(con);

  }

  private Connection getConnection() throws SQLException {

    final Connection connection = dataSource.getConnection();
    log.info("get connnection = {},class={}",connection,connection.getClass());
    return connection;
  }

}
