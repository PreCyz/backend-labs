package pw.backend.lab.backlab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;

/** Created by Pawel Gawedzki on 05-Oct-2019. */
@Service
class CompanyServiceImpl implements CompanyService {
    private final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private CompanyRepository repository;

    CompanyServiceImpl() { /*Needed only for initializing spy in unit tests*/}

    @Autowired
    CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Company result = Company.EMPTY;
        if (repository.existsById(id)) {
            updatedCompany.setId(id);
            result = repository.save(updatedCompany);
            logger.info("Company with id {} updated.", id);
        }
        return result;
    }

    @Override
    public boolean deleteCompany(Long companyId) {
        boolean result = false;
        if (repository.existsById(companyId)) {
            repository.deleteById(companyId);
            logger.info("Company with id {} deleted.", companyId);
            result = true;
        }
        return result;
    }
}
