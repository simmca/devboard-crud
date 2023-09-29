package com.project.management.devboard.controllers;

import com.project.management.devboard.controllers.dto.LoginDto;
import com.project.management.devboard.controllers.dto.LoginResponseDto;
import com.project.management.devboard.controllers.dto.RegisterUserDto;
import com.project.management.devboard.dao.UserDao;
import com.project.management.devboard.exceptions.LoginException;
import com.project.management.devboard.models.User;
import com.project.management.devboard.security.jwt.TokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * AuthenticationController is a class used for handling requests to authenticate Users.
 *
 * It depends on an instance of a UserDao for retrieving and storing user data. This is provided
 * through dependency injection.
 */
@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            User user = userDao.getUserByUsername(loginDto.getUsername());
            return new LoginResponseDto(jwt, user);
        }
        catch (LoginException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public User register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            User user = userDao.createUser(
                    new User(newUser.getUsername(),newUser.getPassword(), newUser.getRole(), newUser.getName())
            );
            return user;
        }
        catch (LoginException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

}

