package com.hmsapp.Service;

import com.hmsapp.Entity.User;
import com.hmsapp.Exception.DublicateData;
import com.hmsapp.PayLoad.LoginDto;
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
        User user = mapToEntity(dto);
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
        return mapToDto(savedUser);
    }
      UserDto mapToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
     User mapToEntity(UserDto dto){
        return modelMapper.map(dto,User.class);
    }


    public String verifyLogin( LoginDto loginDto) {
     Optional<User>  opUsre =userRepository.findByUserName(loginDto.getUserName());
     if(opUsre.isPresent()){
         User user = opUsre.get();
         if( BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
             return jwtService.generateTokens(user.getUserName());
         }
     }
     else {
          return null;
     }
        return null;
    }
}
