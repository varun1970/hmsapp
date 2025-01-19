package com.hmsapp.Service;

import com.hmsapp.Entity.User;
import com.hmsapp.Exception.DublicateData;
import com.hmsapp.PayLoad.LoginDto;
import com.hmsapp.PayLoad.ProfileDto;
import com.hmsapp.PayLoad.UserDto;
import com.hmsapp.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;
    private final OTPService otpService;  // Inject OTPService

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService, OTPService otpService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.otpService = otpService;  // Initialize OTPService
    }

    public UserDto add(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        Optional<User> getUserName = userRepository.findByUserName(user.getUserName());
        if (getUserName.isPresent()) {
            throw new DublicateData("Username already exists");
        }
        Optional<User> getUserEmail = userRepository.findByEmailId(user.getEmailId());
        if (getUserEmail.isPresent()) {
            throw new DublicateData("Email already exists");
        }
        Optional<User> getUserMobile = userRepository.findByMobileNumber(user.getMobileNumber());
        if (getUserMobile.isPresent()) {
            throw new DublicateData("Mobile number already exists");
        }
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10)));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<User> opUser = userRepository.findByUserName(loginDto.getUserName());
        if (opUser.isPresent()) {
            User user = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
                return jwtService.generateTokens(user.getUserName());
            }
        }
        return null;
    }

    // Verify OTP and login user
    public String loginWithOtp(String phoneNumber, String otp) {
        // First, verify the OTP using OTPService
        boolean isOtpValid = otpService.verifyOtp(phoneNumber, otp);

        if (isOtpValid) {
            // OTP is valid, generate JWT token
            Optional<User> userOptional = userRepository.findByMobileNumber(phoneNumber);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return jwtService.generateTokens(user.getUserName());
            }
        }
        return null;  // OTP is invalid or expired
    }

    public ProfileDto getProfile(User user) {
        Optional<User> u = userRepository.findById(user.getId());
        if (u.isPresent()) {
            User ug = u.get();
            ProfileDto dto = new ProfileDto();
            dto.setId(ug.getId());
            dto.setUserName(ug.getUserName());
            dto.setEmailId(ug.getEmailId());
            dto.setMobileNumber(ug.getMobileNumber());
            return dto;
        } else {
            throw new RuntimeException("Invalid user");
        }
    }



}
