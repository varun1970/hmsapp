package com.hmsapp.Controller;

import com.hmsapp.Entity.User;
import com.hmsapp.PayLoad.JwtToken;
import com.hmsapp.PayLoad.LoginDto;
import com.hmsapp.PayLoad.ProfileDto;
import com.hmsapp.PayLoad.UserDto;
import com.hmsapp.Service.OTPService;
import com.hmsapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;//http//:localhost:8080/api/auth/login-with-otp?phoneNumber=1234567&otp=1234

    @Autowired
    private OTPService otpService;

    public AuthController(UserService userService, OTPService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    // Endpoint to generate OTP
    @PostMapping("/generate-otp")
    public ResponseEntity<?> generateOtp(@RequestParam String phoneNumber) {
        // Generate the OTP
        String otp = otpService.generateOtp(phoneNumber);

        return new ResponseEntity<>("OTP sent successfully to " + phoneNumber + ": " + otp, HttpStatus.OK);
    }

    // Endpoint to verify OTP and log in
    @PostMapping("/login-with-otp")
    public ResponseEntity<?> loginWithOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        // Call the UserService to verify OTP and login
        String token = userService.loginWithOtp(phoneNumber, otp);

        if (token != null) {
            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(token);
            jwtToken.setType("JWT");
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired OTP.", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userDto.setRole("ROLE_USER");
        UserDto saveDto = userService.add(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @PostMapping("/property/sign-up")
    public ResponseEntity<?> createPropertyOwnerAccount(@RequestBody UserDto userDto) {
        userDto.setRole("ROLE_OWNER");
        UserDto saveDto = userService.add(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @PostMapping("/blog/sign-up")
    public ResponseEntity<?> createBlogManagerAccount(@RequestBody UserDto userDto) {
        userDto.setRole("ROLE_BLOG MANAGER");
        UserDto saveDto = userService.add(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String tokens = userService.verifyLogin(loginDto);
        if (tokens != null) {
            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(tokens);
            jwtToken.setType("JWT");
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }
        return new ResponseEntity<>("/invalid", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public ProfileDto getProfile(@AuthenticationPrincipal User user) {
        return userService.getProfile(user);
    }
}
