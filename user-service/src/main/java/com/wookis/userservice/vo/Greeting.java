package com.wookis.userservice.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Greeting {

    @Value("${greeting.message}")
    private String message;
}
