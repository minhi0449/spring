package com.ch09.repository;

import com.ch09.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface User1Repository extends JpaRepository<User1, String> {

}