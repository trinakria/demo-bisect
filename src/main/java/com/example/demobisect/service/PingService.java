package com.example.demobisect.service;


import com.example.demobisect.api.dto.PingDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PingService {

    public PingDTO ping(String value) {
        return new PingDTO(value, LocalDateTime.now());
    }
}
