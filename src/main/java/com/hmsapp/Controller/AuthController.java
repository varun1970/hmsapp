package com.hmsapp.Controller;

import com.hmsapp.PayLoad.JwtToken;
import com.hmsapp.PayLoad.LoginDto;
import com.hmsapp.PayLoad.UserDto;
import com.hmsapp.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        userDto.setRole("ROLE_USER");
        UserDto saveDto = userService.add(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @PostMapping("property/sign-up")
    public ResponseEntity<?> createPropertyOwnerAccount(@RequestBody UserDto userDto){
        userDto.setRole("ROLE_OWNER");
        UserDto saveDto = userService.add(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @PostMapping("blog/sign-up")
    public ResponseEntity<?> createBlogManagerAccount(@RequestBody UserDto userDto){
        userDto.setRole("ROLE_BLOGMANAGER");
        UserDto saveDto = userService.add(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String tokens=userService.verifyLogin(loginDto);
        if(tokens!=null) {
            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(tokens);
            jwtToken.setType("JWT");
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }
        return new  ResponseEntity<>("/invalid", HttpStatus.UNAUTHORIZED);
    }

}
