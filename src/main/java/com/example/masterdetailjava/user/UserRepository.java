package com.example.masterdetailjava.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    List<UserEntity> findAll();

    Optional<UserEntity> findByLogin(String login);
}
