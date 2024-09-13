package com.ch04.dao;


import com.ch04.dto.User1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class User1DAO {

    private JdbcTemplate jdbcTemplates;

    @Autowired
    public User1DAO(JdbcTemplate jdbcTemplates) {
        this.jdbcTemplates = jdbcTemplates;
    }

    public void insertUser1(User1DTO dto){

        String sql = "insert into `user1` values(?,?,?,?,?)";
        Object[] params ={
                dto.getUid(),
                dto.getName(),
                dto.getBirth(),
                dto.getHp(),
                dto.getAge()
        };

        jdbcTemplates.update(sql, params);
        // sql 뒤에 맵핑(params)
    }

    public User1DTO selectUser1(String uid){
        String sql = "select * from `user1` where uid=?";
        User1DTO dto = (User1DTO) jdbcTemplates.queryForObject(sql, new User1RowMapper(), uid);
        return dto;
    }
    public List<User1DTO> selectUser1s(){
        String sql = "select * from user1";
        return jdbcTemplates.query(sql, new User1RowMapper());
    }

    public void updateUser1(User1DTO dto){

        String sql = "update `user1` set name=?,birth=?,hp=?,age=? where uid=?";
        Object[] params ={
                dto.getName(),
                dto.getBirth(),
                dto.getHp(),
                dto.getAge(),
                dto.getUid()
        };

        jdbcTemplates.update(sql, params);
    }

    public void deleteUser1(String uid){
        String sql = "delete from `user1` where uid=?";
        jdbcTemplates.update(sql, uid);
    }

}
