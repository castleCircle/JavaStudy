package com.example.demo.user.service.port;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.infrastructure.UserEntity;
import java.util.Optional;

/**
 * infrastructure에 있으면 안된다. 상위모듈인 service에서 infrastructure인 패키지에 의존하는 그림이 되게됨.
 */
public interface UserRepository {
  Optional<User> findByIdAndStatus(long id, UserStatus userStatus);

  Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);

  User save(User user);

  Optional<User> findById(long id);
}
