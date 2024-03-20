package com.digitalcoffee.user.service;

import com.digitalcoffee.commons.UserRole;
import com.digitalcoffee.user.dto.request.UserCreationRequest;
import com.digitalcoffee.user.dto.request.UserUpdateRequest;
import com.digitalcoffee.user.dto.response.UserResponse;
import com.digitalcoffee.user.entity.UserRoot;
import com.digitalcoffee.user.exception.AppException;
import com.digitalcoffee.user.exception.ErrorCode;
import com.digitalcoffee.user.mapper.UserMapper;
import com.digitalcoffee.user.proxy.CustomerServiceProxy;
import com.digitalcoffee.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    CustomerServiceProxy customerService;
    public UserResponse createUser(UserCreationRequest request, UserRole role){
        if (userRepository.existsByUsername(request.getUsername().toLowerCase()))
            throw new AppException(ErrorCode.USER_EXISTED);

        UserRoot userRoot = userMapper.toUser(request);
        userRoot.addRole(role);
        userRoot.setUsername(userRoot.getUsername().toLowerCase());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userRoot.setPassword(passwordEncoder.encode(request.getPassword()));

        customerService.createCustomer(request.getUsername());
        return userMapper.toUserResponse(userRepository.save(userRoot));
    }

    public UserResponse updateUser(String username, UserUpdateRequest request) {
        UserRoot userRoot = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(userRoot, request);

        return userMapper.toUserResponse(userRepository.save(userRoot));
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }
}
