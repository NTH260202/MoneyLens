package com.example.moneylens.domain.repository;

import com.example.moneylens.domain.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameOrEmail(String id, String id1);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u.id from User u where u.id = :id")
    Optional<String> lockById(@Param("id") String id);
}
