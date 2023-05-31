package com.example.demobisect.service;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@AllArgsConstructor
public class ServerLocationController {

    private final Environment environment;

    @GetMapping("/server/location")
    public String getServerLocation() throws UnknownHostException {
        String serverPort = environment.getProperty("local.server.port");
        InetAddress serverAddress = InetAddress.getLocalHost();
        return serverAddress.getHostAddress() + ":" + serverPort;
    }
}
