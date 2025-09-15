package com.helmes.sectors.controller;

import com.helmes.sectors.dto.RegisterDto;
import com.helmes.sectors.exception.UsernameIsAlreadyTakenException;
import com.helmes.sectors.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @Valid RegisterDto registerDto,
                           BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            authService.register(registerDto.getUsername(), registerDto.getPassword());

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        } catch (UsernameIsAlreadyTakenException e) {
            bindingResult.addError(
                    new FieldError(
                            "registerDto",
                            "username",
                            "Username is already taken"
                    )
            );
            return "register";
        }

        return "register";
    }
}