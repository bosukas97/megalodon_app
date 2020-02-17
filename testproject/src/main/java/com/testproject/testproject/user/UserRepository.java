package com.testproject.testproject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // extend the JpaRepository and tell it the entity we want to manage along with PK of that Entity
public interface UserRepository extends JpaRepository<User, Integer> {
}
