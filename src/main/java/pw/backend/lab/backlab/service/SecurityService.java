package pw.backend.lab.backlab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.joining;

@Service
public class SecurityService {

    Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final String SECURITY_HEADER = "security_header";
    private final String SECURITY_HEADER_VALUE = "secureMe";

    public boolean isAuthenticated(HttpHeaders headers) {
        if (headers == null) {
            return false;
        }
        logger.info("Request headers [{}].",
                headers.entrySet()
                        .stream()
                        .map(entry -> String.format("%s->%s", entry.getKey(), entry.getValue()))
                        .collect(joining(","))
        );
        return headers.containsKey(SECURITY_HEADER) && SECURITY_HEADER_VALUE.equals(headers.getFirst(SECURITY_HEADER));
    }

    public boolean isAuthorized(HttpHeaders headers) {
        return isAuthenticated(headers);
    }
}
