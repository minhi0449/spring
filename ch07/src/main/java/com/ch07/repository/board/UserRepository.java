package com.ch07.repository.board;

import com.ch07.entity.board.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
