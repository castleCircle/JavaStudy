package com.example.demo.user.service.port;

import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.infrastructure.UserEntity;
import java.util.Optional;

/**
 * infrastructure에 있으면 안된다. 상위모듈인 service에서 infrastructure인 패키지에 의존하는 그림이 되게됨.
 */
public interface UserRepository {
  Optional<UserEntity> findByIdAndStatus(long id, UserStatus userStatus);

  Optional<UserEntity> findByEmailAndStatus(String email, UserStatus userStatus);

  UserEntity save(UserEntity userEntity);

  Optional<UserEntity> findById(long id);
}
