package com.hmsapp.Service;

import com.hmsapp.Entity.User;
import com.hmsapp.Exception.DublicateData;
import com.hmsapp.PayLoad.LoginDto;
import com.hmsapp.PayLoad.ProfileDto;
import com.hmsapp.PayLoad.UserDto;
import com.hmsapp.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;

@Service
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    JWTService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public UserDto add(UserDto dto) {
        User user = modelMapper.map(dto,User.class);
        Optional<User> getUserName=userRepository.findByUserName(user.getUserName());
        if(getUserName.isPresent()){
            throw new DublicateData("Name User already");
        }
        Optional<User> getUserEmail=userRepository.findByEmailId(user.getEmailId());
        if(getUserEmail.isPresent()){
            throw new DublicateData("Email User already");
        }
        Optional<User> getUserMobile=userRepository.findByMobileNumber(user.getMobileNumber());
        if(getUserMobile.isPresent()){
            throw new DublicateData("Mobile already exists");
        }
        user.setPassword(BCrypt.hashpw(dto.getPassword(),gensalt(10)));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    public String verifyLogin( LoginDto loginDto) {
     Optional<User>  opUsre =userRepository.findByUserName(loginDto.getUserName());
     if(opUsre.isPresent()){
         User user = opUsre.get();
         if( BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
             return jwtService.generateTokens(user.getUserName());
         }
     }
        return null;
    }

    public ProfileDto getProfile(User user) {
        Optional<User> u=  userRepository.findById(user.getId());
        if(u.isPresent()) {
            User ug=u.get();
            ProfileDto dto = new ProfileDto();
            dto.setId(ug.getId());
            dto.setUserName(ug.getUserName());
            dto.setEmailId(ug.getEmailId());
            dto.setMobileNumber(ug.getMobileNumber());
            return dto;
        }
        else {
            throw new RuntimeException("invalid");
        }
    }
}
