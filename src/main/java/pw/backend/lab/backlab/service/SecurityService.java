package pw.backend.lab.backlab.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecurityService {

    private final String SECURITY_HEADER = "securityH";
    private final String SECURITY_HEADER_VALUE = "secureMe";

    public boolean isAuthenticated(Map<String, String> requestHeaders) {
        return requestHeaders != null && requestHeaders.containsKey(SECURITY_HEADER) &&
                SECURITY_HEADER_VALUE.equals(requestHeaders.get(SECURITY_HEADER));
    }

    public boolean isAuthorized(Map<String, String> requestHeaders) {
        return isAuthenticated(requestHeaders);
    }
}
