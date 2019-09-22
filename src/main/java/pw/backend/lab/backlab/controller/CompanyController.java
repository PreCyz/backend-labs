package pw.backend.lab.backlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;
import pw.backend.lab.backlab.service.SecurityService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {

    private CompanyRepository repository;
    private SecurityService securityService;

    @Autowired
    public CompanyController(CompanyRepository repository, SecurityService securityService) {
        this.repository = repository;
        this.securityService = securityService;
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createCompanies(@RequestHeader Map<String, String> requestHeaders,
                                                  @Valid @RequestBody List<Company> companies) {
        if (securityService.isAuthorized(requestHeaders)) {
            List<Company> result = repository.saveAll(companies);
            return ResponseEntity.ok(result.stream().map(c -> String.valueOf(c.getId())).collect(joining(",")));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access to resources.");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompany(@RequestHeader Map<String, String> requestHeaders, @PathVariable Long id) {
        if (securityService.isAuthorized(requestHeaders)) {
            return ResponseEntity.ok(repository.findById(id).orElseGet(() -> Company.EMPTY));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Company.EMPTY);
    }
}
