package pw.backend.lab.backlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.backend.lab.backlab.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
