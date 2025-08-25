package com.wookis.userservice.controller;

import com.wookis.userservice.vo.Greeting;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;

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
