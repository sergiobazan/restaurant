package com.bazan.restaurant.users;

import com.bazan.restaurant.users.DTOs.LoginRequest;
import com.bazan.restaurant.users.DTOs.LoginResponse;
import com.bazan.restaurant.users.DTOs.UserRequest;
import com.bazan.restaurant.users.DTOs.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody UserRequest userRequest
    ) {
        try {
            var user = userService.create(userRequest);
            var response = UserResponse.Success("User Created successfully", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = UserResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        try {
            var token = userService.login(loginRequest);
            var response = LoginResponse.Success(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = LoginResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(
            @RequestHeader("Authorization") String auth
    ){
        try {
            var user = userService.getUser(auth);
            var response = UserResponse.Success("Success", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
