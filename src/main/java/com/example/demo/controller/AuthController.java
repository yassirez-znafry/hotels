package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;


    @GetMapping("/")
    public List<UserInfos> getAllUser(){
        if(authService.getCurrentUser().getAccessLevel() == 0)   //  Only the manager and front desk service can access this method
            return null;

        List<User> users = authService.getAllUsers();
        List<UserInfos> userInfosList = new ArrayList<UserInfos>();

        for(User user : users){
            UserInfos userInfos = new UserInfos();
            userInfos.setId(user.getUserId());
            userInfos.setUsername(user.getUserName());
            userInfos.setEmail(user.getUserEmail());
            userInfos.setImage(user.getImage());

            userInfosList.add(userInfos);
        }

        return userInfosList;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("user registration successful", OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activateed Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }


    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @GetMapping("/user/{id}")
    public UserInfos UserInfos(@PathVariable long id) throws JSONException {

        User user = authService.getUserById(id);
        UserInfos userInfos = new UserInfos(user.getUserName(), user.getUserEmail(), user.getImage(), user.getUserId());
        return userInfos;

    }

    @GetMapping("/user/myInfos")
    public UserInfos UserInfos() throws JSONException {
        User user = authService.getUserById(authService.getCurrentUser().getUserId());

        UserInfos userInfos = new UserInfos(user.getUserName(), user.getUserEmail(), user.getImage(), user.getUserId());
        return userInfos;

    }

}
