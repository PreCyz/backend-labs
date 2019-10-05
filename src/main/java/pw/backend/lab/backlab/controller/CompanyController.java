package pw.backend.lab.backlab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;
import pw.backend.lab.backlab.service.SecurityService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private CompanyRepository repository;
    private SecurityService securityService;

    @Autowired
    public CompanyController(CompanyRepository repository, SecurityService securityService) {
        this.repository = repository;
        this.securityService = securityService;
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createCompanies(@RequestHeader HttpHeaders headers,
                                                  @Valid @RequestBody List<Company> companies) {
        logger.info("Controller request headers: {}",
                headers.entrySet()
                        .stream()
                        .map(entry -> String.format("%s->[%s]", entry.getKey(), String.join(",", entry.getValue())))
                        .collect(joining(","))
        );
        if (securityService.isAuthorized(headers)) {
            List<Company> result = repository.saveAll(companies);
            return ResponseEntity.ok(result.stream().map(c -> String.valueOf(c.getId())).collect(joining(",")));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access to resources.");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompany(@RequestHeader HttpHeaders headers,
                                              @PathVariable Long id) {
        logger.info("Controller request headers {}",
                headers.entrySet()
                        .stream()
                        .map(entry -> String.format("%s->[%s]", entry.getKey(), String.join(",", entry.getValue())))
                        .collect(joining(","))
        );
        if (securityService.isAuthorized(headers)) {
            return ResponseEntity.ok(repository.findById(id).orElseGet(() -> Company.EMPTY));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Company.EMPTY);
    }
}
