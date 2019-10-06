package pw.backend.lab.backlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.backend.lab.backlab.model.CompanyLogo;

import java.util.Optional;

/** Created by Pawel Gawedzki on 06-Oct-2019. */
public interface CompanyLogoRepository extends JpaRepository<CompanyLogo, String> {
    Optional<CompanyLogo> findByCompanyId(long companyId);
}
