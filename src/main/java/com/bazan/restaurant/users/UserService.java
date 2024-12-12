package com.bazan.restaurant.users;

import com.bazan.restaurant.users.DTOs.LoginRequest;
import com.bazan.restaurant.users.DTOs.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public List<UserProfile> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserProfile create(UserRequest userProfile) {
        UserProfile user = UserProfile.Create(
                userProfile.name(),
                userProfile.email(),
                encoder.encode(userProfile.password()),
                userProfile.birthDay(),
                userProfile.role()
        );
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        return "token";
    }
}
