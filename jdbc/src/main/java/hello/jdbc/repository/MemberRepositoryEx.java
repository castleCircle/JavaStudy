package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

public interface MemberRepositoryEx {

  Member save(Member member);

 Member findById(String memberId);

  void update(String memberId,int money);

 void delete(String memberId);
}
