package com.example.demobisect.api;

import com.example.demobisect.api.dto.PingDTO;
import com.example.demobisect.service.PingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class PingController {

    private final PingService pingService;

    private final static Logger LOGGER = LoggerFactory.getLogger(PingController.class);

    @GetMapping(value = "/ping/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PingDTO> ping(@PathVariable String value) {
        LOGGER.debug("Pinging {}", value);
        return new ResponseEntity<>(pingService.ping(value), HttpStatus.OK);
    }
}
