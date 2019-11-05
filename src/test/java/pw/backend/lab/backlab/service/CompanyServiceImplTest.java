package pw.backend.lab.backlab.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/** Created by Pawel Gawedzki on 05-Oct-2019. */
@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTest {
    @Mock
    private CompanyRepository repository;
    @Spy
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    public void givenNotExistingId_whenUpdateCompany_theReturnCompanyEMPTY() {
        when(repository.existsById(anyLong())).thenReturn(false);

        Company actual = companyService.updateCompany(1L, new Company());

        assertThat(actual).isEqualTo(Company.EMPTY);
        verify(repository, times(1)).existsById(eq(1L));
        verify(repository, times(0)).save(any(Company.class));
    }

    @Test
    public void givenExistingId_whenUpdateCompany_theReturnUpdatedCompany() {
        Company updatedCompany = new Company();
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any(Company.class))).thenReturn(updatedCompany);

        Company actual = companyService.updateCompany(1L, updatedCompany);

        assertThat(actual).isEqualTo(updatedCompany);
        verify(repository, times(1)).existsById(eq(1L));
        verify(repository, times(1)).save(eq(updatedCompany));
    }

}