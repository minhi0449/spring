package com.ch08.service;

import com.ch08.dto.UserDTO;
import com.ch08.entity.User;
import com.ch08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public static void insertUser(UserDTO userDTO) {
        // 회원가입
        User user = UserDTO.toEntity();
        userRepository.save(user);
    }


    public UserDTO selectUser(String uid) {

        Optional<User> optUser = userRepository.findById(uid);

        if(optUser.isPresent()) {
            User user = optUser.get();
            return user.toDTO();
        }
        return null;
    }
}
