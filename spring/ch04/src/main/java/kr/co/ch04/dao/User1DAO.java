package kr.co.ch04.dao;

import kr.co.ch04.dto.User1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class User1DAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser1(User1DTO dto){
        String sql = "insert into user1 values(?,?,?,?)";
        Object[] params = {
                dto.getUid(),
                dto.getName(),
                dto.getHp(),
                dto.getAge()
        };
        jdbcTemplate.update(sql, params);
    }


}
