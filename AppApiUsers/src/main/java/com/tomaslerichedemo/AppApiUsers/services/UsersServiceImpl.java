package com.tomaslerichedemo.AppApiUsers.services;

import com.tomaslerichedemo.AppApiUsers.data.UserEntity;
import com.tomaslerichedemo.AppApiUsers.data.UsersRepository;
import com.tomaslerichedemo.AppApiUsers.shared.UsersDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UserService {

    UsersRepository usersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UsersDto createUser(UsersDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());

        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);


        usersRepository.save(userEntity);

        UsersDto returnValue = modelMapper.map(userEntity, UsersDto.class);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);
        if(userEntity == null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UsersDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UsersDto.class);
    }
}
