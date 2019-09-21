package pw.backend.lab.backlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @PostMapping(path = "")
    public ResponseEntity<String> createCompanies(@RequestHeader("securityH") String secureH,
                                @Valid @RequestBody List<Company> companies) {
        if ("secureMe".equals(secureH)) {
            List<Company> result = repository.saveAll(companies);
            return ResponseEntity.ok(result.stream().map(c -> String.valueOf(c.getId())).collect(joining(",")));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not find securityH");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompany(@RequestHeader("securityH") String secureH, @PathVariable Long id) {
        if (!"secureMe".equals(secureH)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Company.EMPTY);
        }
        return ResponseEntity.ok(repository.findById(id).orElseGet(() -> Company.EMPTY));
    }
}
