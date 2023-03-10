package com.numble.bankingserver.repository;

import com.numble.bankingserver.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String name);

    Optional<User> findByLoginId(String email);

    void deleteByLoginId(String test);
}
