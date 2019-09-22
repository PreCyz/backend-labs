package pw.backend.lab.backlab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public static final String SECURITY_HEADER = "security-header";
    private final String SECURITY_HEADER_VALUE = "secureMe";

    public boolean isAuthenticated(HttpHeaders headers) {
        if (headers == null) {
            return false;
        }
        return headers.containsKey(SECURITY_HEADER) && SECURITY_HEADER_VALUE.equals(headers.getFirst(SECURITY_HEADER));
    }

    public boolean isAuthorized(HttpHeaders headers) {
        return isAuthenticated(headers) || true;
    }
}
