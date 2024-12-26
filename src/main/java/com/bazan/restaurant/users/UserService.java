package com.bazan.restaurant.users;

import com.bazan.restaurant.orders.DTOs.DishResponseDto;
import com.bazan.restaurant.orders.DTOs.OrderResponseDto;
import com.bazan.restaurant.restaurants.DTOs.RestaurantResponseDto;
import com.bazan.restaurant.restaurants.IRestaurantRepository;
import com.bazan.restaurant.shared.services.IJwtService;
import com.bazan.restaurant.users.DTOs.ClientOrderDto;
import com.bazan.restaurant.users.DTOs.LoginRequest;
import com.bazan.restaurant.users.DTOs.UserRequest;
import com.bazan.restaurant.users.DTOs.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final IJwtService jwtService;
    private final IRestaurantRepository restaurantRepository;

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

    @Override
    public RestaurantResponseDto getRestaurantByOwnerId(long id) throws Exception {
        var restaurant = restaurantRepository
                .findByOwnerId(id)
                .orElseThrow(() -> new Exception("No restaurant was found"));

        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getSlug(),
                restaurant.getAddress(),
                restaurant.getDescription(),
                restaurant.getOpenAt(),
                restaurant.getCloseAt(),
                null
        );
    }

    @Override
    public List<ClientOrderDto> getOrderByClientId(long clientId, String range) {
        LocalDate start = getStartDate(range);
        LocalDate end = getEndDate(range);
        var orders = userRepository.getOrdersByClientId(clientId, start, end);
        return orders.stream()
                .map(order -> new ClientOrderDto(
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getRestaurant().getName(),
                        order.getDescription(),
                        order.getStatus(),
                        order.getPaymentStatus(),
                        order.getCreatedAt(),
                        order.getOrderItems().stream().map(oi -> new DishResponseDto(
                                oi.getDish().getId(),
                                oi.getDish().getName(),
                                oi.getDish().getType()
                        )).toList()
                ))
                .toList();
    }

    private static LocalDate getStartDate(String range) {
        LocalDate today = LocalDate.now();
        return switch (range) {
          case "today" -> today;
          case "week" -> today.with(DayOfWeek.MONDAY);
          case "month" -> today.withDayOfMonth(1);
          case "all" -> LocalDate.of(1990, 1, 1);
          default -> throw new IllegalArgumentException("Invalid range: " + range);
        };
    }

    private static LocalDate getEndDate(String range) {
        LocalDate today = LocalDate.now();
        return switch (range) {
            case "today" -> today;
            case "week" -> today.with(DayOfWeek.SUNDAY);
            case "month" -> today.with(lastDayOfMonth());
            case "all" -> LocalDate.of(2050, 1, 1);
            default -> throw new IllegalArgumentException("Invalid range: " + range);
        };
    }
}
