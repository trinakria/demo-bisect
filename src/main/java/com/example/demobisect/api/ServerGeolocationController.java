package com.example.demobisect.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@RestController
public class ServerGeolocationController {

    private final DatabaseReader databaseReader;

    public ServerGeolocationController() throws IOException {
        ClassPathResource resource = new ClassPathResource("GeoLite2-City_20230530/GeoLite2-City.mmdb");
        databaseReader = new DatabaseReader.Builder(resource.getFile()).build();
    }

    @GetMapping("/server/location")
    public String getServerLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String ipAddress = getClientIpAddress(request);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);

        CityResponse cityResponse = databaseReader.city(inetAddress);
        String countryName = cityResponse.getCountry().getName();
        String cityName = cityResponse.getCity().getName();

        return countryName + ", " + cityName;
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
