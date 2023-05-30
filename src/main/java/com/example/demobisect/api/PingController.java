package com.example.demobisect.api;

import com.example.demobisect.api.dto.PingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class PingController {

    @GetMapping(value = "/ping/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PingDTO> ping(@PathVariable String value) {
        return new ResponseEntity<>(new PingDTO(value, LocalDateTime.now()), HttpStatus.OK);
    }
}
