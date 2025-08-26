package com.wookis.userservice.controller;

import com.wookis.userservice.dto.request.RequestUser;
import com.wookis.userservice.dto.response.ResponseUser;
import com.wookis.userservice.dto.response.UserDto;
import com.wookis.userservice.service.UserService;
import com.wookis.userservice.vo.Greeting;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        UserDto savedUser = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(savedUser, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service, " +
                "port(local.server.port) = %s, " +
                "port(server.port) = %s",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        log.info("users.welcome ip: {}, {}, {} ,{}",
                request.getRemoteAddr(),
                request.getRemoteHost(),
                request.getRequestURI(),
                request.getRequestURL()
        );

        return greeting.getMessage();
    }
}
