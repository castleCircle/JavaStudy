package com.example.demo.user.infrastructure;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.service.port.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

  @Override
  public Optional<User> findById(long id) {
    return userJpaRepository.findById(id)
        .map(UserEntity::toModel);
  }

  @Override
  public Optional<User> findByIdAndStatus(long id, UserStatus userStatus) {
    return userJpaRepository.findByIdAndStatus(id,userStatus).map(UserEntity::toModel);
  }

  @Override
  public Optional<User> findByEmailAndStatus(String email, UserStatus userStatus) {
    return userJpaRepository.findByEmailAndStatus(email,userStatus).map(UserEntity::toModel);
  }

  //도메인 모델은 왜 인프라스트럭쳐 정보를 모르는게 좋으락?
  @Override
  public User save(User user) {
    return userJpaRepository.save(UserEntity.fromModel(user)).toModel();
  }
}
