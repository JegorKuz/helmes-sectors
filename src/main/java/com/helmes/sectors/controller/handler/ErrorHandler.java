package com.helmes.sectors.controller.handler;

import com.helmes.sectors.dto.ErrorResponseDto;
import com.helmes.sectors.exception.InvalidUsernameOrPasswordException;
import com.helmes.sectors.exception.SectorIdDoesNotExistException;
import com.helmes.sectors.exception.UsernameIsAlreadyTakenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException e) {
        log.error("{} was thrown", e.getClass().getSimpleName(), e);
        return new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.name(),
                "Invalid username or password.",
                e.getMessage(),
                LocalDateTime.now().format(formatter)
        );
    }

    @ExceptionHandler(UsernameIsAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleUsernameIsAlreadyTakenException(UsernameIsAlreadyTakenException e) {
        log.error("{}  was thrown", e.getClass().getSimpleName(), e);
        return new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.name(),
                "Username is already taken",
                e.getMessage(),
                LocalDateTime.now().format(formatter)
        );
    }

    @ExceptionHandler(SectorIdDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleSectorIdDoesNotExistException(SectorIdDoesNotExistException e) {
        log.error("{}  was thrown", e.getClass().getSimpleName(), e);
        return new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.name(),
                "Sector ID does not exist.",
                e.getMessage(),
                LocalDateTime.now().format(formatter)
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception e) {
        log.error("Exception was thrown", e);
        return new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "Internal server error happened, try again later.",
                e.getMessage(),
                LocalDateTime.now().format(formatter)
        );
    }
}