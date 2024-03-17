package com.digitalcoffee.auth.service;

import com.digitalcoffee.commons.UserRole;
import com.digitalcoffee.auth.dto.request.RegisterRequest;
import com.digitalcoffee.auth.dto.request.UserUpdateRequest;
import com.digitalcoffee.auth.dto.response.UserResponse;
import com.digitalcoffee.auth.entity.UserRoot;
import com.digitalcoffee.auth.exception.AppException;
import com.digitalcoffee.auth.exception.ErrorCode;
import com.digitalcoffee.auth.mapper.UserMapper;
import com.digitalcoffee.auth.proxy.CustomerServiceProxy;
import com.digitalcoffee.auth.repository.UserRepository;
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
    public UserResponse createCustomer(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        UserRoot userRoot = userMapper.toUser(request);
        userRoot.addRole(UserRole.CUSTOMER);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userRoot.setPassword(passwordEncoder.encode(request.getPassword()));

        customerService.createCustomer(request.getUsername());
        return userMapper.toUserResponse(userRepository.save(userRoot));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        UserRoot userRoot = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(userRoot, request);

        return userMapper.toUserResponse(userRepository.save(userRoot));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
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
