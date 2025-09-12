package com.helmes.sectors.controller;

import com.helmes.sectors.dto.UserDto;
import com.helmes.sectors.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public void saveUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }
}