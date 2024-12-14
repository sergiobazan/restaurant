package com.bazan.restaurant.users;

import com.bazan.restaurant.shared.services.IJwtService;
import com.bazan.restaurant.users.DTOs.LoginRequest;
import com.bazan.restaurant.users.DTOs.UserRequest;
import com.bazan.restaurant.users.DTOs.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final IJwtService jwtService;

    @Override
    public List<UserProfile> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto create(UserRequest userProfile) {
        UserProfile user = UserProfile.Create(
                userProfile.name(),
                userProfile.email(),
                encoder.encode(userProfile.password()),
                userProfile.birthDay(),
                userProfile.role()
        );
        var createdUser = userRepository.save(user);
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthDay(),
                user.getRole()
        );
    }

    @Override
    public String login(LoginRequest loginRequest) throws Exception {
        var user = userRepository.findByEmail(loginRequest.email());

        if (user == null)
            throw new Exception("Invalid Credentials");

        if (!encoder.matches(loginRequest.password(), user.getPassword()))
            throw new Exception("Invalid Credentials");

        return jwtService.generateToken(user);
    }

    @Override
    public UserResponseDto getUser(String auth) throws Exception {
        if (auth == null || !auth.startsWith("Bearer ")) throw new Exception("Missing Authentication");
        String token = auth.substring(7);
        var userEmail = this.jwtService.getUserEmailFromToken(token);
        var user = userRepository.findByEmail(userEmail);
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthDay(),
                user.getRole()
        );
    }
}
