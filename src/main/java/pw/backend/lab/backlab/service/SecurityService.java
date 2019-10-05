package pw.backend.lab.backlab.service;

import org.springframework.http.HttpHeaders;

/** Created by Pawel Gawedzki on 05-Oct-2019. */
public interface SecurityService {
    boolean isAuthenticated(HttpHeaders headers);
    boolean isAuthorized(HttpHeaders headers);
}
