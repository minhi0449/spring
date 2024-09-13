package com.ch06.dao;

import java.util.List;

public class User1DAO {


    public void insertUser1(User1DTO user1DTO);
    public List<User1DTO> selectUser1s();
    public User1DTO selectUser1(String uid);
    public void updateUser1(User1DTO user1DTO);
    public void deleteUser1(String uid);


}
