package com.tomaslerichedemo.AppApiUsers.controllers;

import com.tomaslerichedemo.AppApiUsers.models.CreateUserRequestModel;
import com.tomaslerichedemo.AppApiUsers.models.CreateUserResponseModel;
import com.tomaslerichedemo.AppApiUsers.services.UserService;
import com.tomaslerichedemo.AppApiUsers.shared.UsersDto;
import org.h2.value.ValueLob;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UsersDto usersDto = modelMapper.map(userDetails, UsersDto.class);

        UsersDto createdUser = userService.createUser(usersDto);

        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
