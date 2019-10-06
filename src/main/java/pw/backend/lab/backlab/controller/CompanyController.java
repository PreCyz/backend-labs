package pw.backend.lab.backlab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;
import pw.backend.lab.backlab.model.CompanyLogo;
import pw.backend.lab.backlab.service.CompanyLogoService;
import pw.backend.lab.backlab.service.CompanyService;
import pw.backend.lab.backlab.service.SecurityService;
import pw.backend.lab.backlab.web.UploadFileResponse;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private CompanyRepository repository;
    private SecurityService securityService;
    private CompanyService companyService;
    private CompanyLogoService companyLogoService;

    @Autowired
    public CompanyController(CompanyRepository repository, SecurityService securityService, CompanyService companyService) {
        this.repository = repository;
        this.securityService = securityService;
        this.companyService = companyService;
    }

    @Autowired
    public void setCompanyLogoService(CompanyLogoService companyLogoService) {
        this.companyLogoService = companyLogoService;
    }

    @PostMapping(path = "")
    public ResponseEntity<String> createCompanies(@RequestHeader HttpHeaders headers,
                                                  @Valid @RequestBody List<Company> companies) {
        logHeaders(headers);
        if (securityService.isAuthorized(headers)) {
            List<Company> result = repository.saveAll(companies);
            return ResponseEntity.ok(result.stream().map(c -> String.valueOf(c.getId())).collect(joining(",")));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access to resources.");
    }

    private void logHeaders(@RequestHeader HttpHeaders headers) {
        logger.info("Controller request headers {}",
                headers.entrySet()
                        .stream()
                        .map(entry -> String.format("%s->[%s]", entry.getKey(), String.join(",", entry.getValue())))
                        .collect(joining(","))
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompany(@RequestHeader HttpHeaders headers,
                                              @PathVariable Long id) {
        logHeaders(headers);
        if (securityService.isAuthorized(headers)) {
            return ResponseEntity.ok(repository.findById(id).orElseGet(() -> Company.EMPTY));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Company.EMPTY);
    }

    @GetMapping(path = "")
    public ResponseEntity<Collection<Company>> getAllCompanies(@RequestHeader HttpHeaders headers) {
        logHeaders(headers);
        if (securityService.isAuthorized(headers)) {
            return ResponseEntity.ok(repository.findAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
    }

    @PutMapping(path = "/{companyId}")
    public ResponseEntity<Company> updateCompany(@RequestHeader HttpHeaders headers,
                                                 @PathVariable Long companyId,
                                                 @RequestBody Company updatedCompany) {
        logHeaders(headers);
        Company result;
        if (securityService.isAuthorized(headers)) {
            result = companyService.updateCompany(companyId, updatedCompany);
            if (Company.EMPTY.equals(result)) {
                return ResponseEntity.badRequest().body(updatedCompany);
            }
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Company.EMPTY);
    }

    @DeleteMapping(path = "/{companyId}")
    public ResponseEntity<String> updateCompany(@RequestHeader HttpHeaders headers, @PathVariable Long companyId) {
        logHeaders(headers);
        if (securityService.isAuthorized(headers)) {
            boolean deleted = companyService.deleteCompany(companyId);
            if (!deleted) {
                return ResponseEntity.badRequest().body(String.format("Company with id %s does not exists.", companyId));
            }
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }

    @PostMapping("/{companyId}/logo")
    @Transactional
    public UploadFileResponse uploadLogo(@PathVariable Long companyId, @RequestParam("file") MultipartFile file) {
        CompanyLogo companyLogo = companyLogoService.storeLogo(companyId, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/companies/" + companyId + "/logo/")
                .path(companyLogo.getFileName())
                .toUriString();

        return new UploadFileResponse(companyLogo.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping(value = "/{companyId}/logo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Transactional
    public @ResponseBody byte[] getLog(@PathVariable Long companyId) {
        CompanyLogo companyLogo = companyLogoService.getCompanyLogo(companyId);
        return companyLogo.getData();
    }

    @GetMapping(value = "/{companyId}/logo2")
    @Transactional
    public ResponseEntity<Resource> getLogo2(@PathVariable Long companyId) {
        CompanyLogo companyLogo = companyLogoService.getCompanyLogo(companyId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(companyLogo.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + companyLogo.getFileName() + "\"")
                .body(new ByteArrayResource(companyLogo.getData()));
    }

}
