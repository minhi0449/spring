package kr.co.ch04.dao;

import kr.co.ch04.dto.User1DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User1RowMapper implements RowMapper<User1DTO> {

    @Override
    public User1DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        User1DTO dto = new User1DTO(); // 기본 생성자 사용 가능해짐
        dto.setUid(rs.getString(1));
        dto.setName(rs.getString(2));
        dto.setBirth(rs.getString(3));
        dto.setHp(rs.getString(4));
        dto.setAge(rs.getInt(5));

        return dto;
    }
}