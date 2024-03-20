package com.digitalcoffee.user.controller;

import com.digitalcoffee.user.dto.request.*;
import com.digitalcoffee.user.dto.response.AuthenticationResponse;
import com.digitalcoffee.user.dto.response.IntrospectResponse;
import com.digitalcoffee.user.dto.response.UserResponse;
import com.digitalcoffee.user.service.AuthenticationService;
import com.digitalcoffee.user.service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/register")
    ApiResponse<UserResponse> register(@RequestBody @Valid RegisterRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createCustomer(request))
                .build();
    }

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody LoginRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{username}")
    ApiResponse<UserResponse> getUser(@PathVariable("username") String username){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(username))
                .build();
    }

    @PutMapping("/{username}")
    ApiResponse<UserResponse> updateUser(@PathVariable String username, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(username, request))
                .build();
    }
}
