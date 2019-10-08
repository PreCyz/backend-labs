package pw.backend.lab.backlab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/** Created by Pawel Gawedzki on 06-Oct-2019. */
@Entity
@Table(name = "company_logo")
@Data
@NoArgsConstructor
public class CompanyLogo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;
    private long companyId;
    @Lob
    private byte[] data;

    public CompanyLogo(String fileName, String fileType, long companyId, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.companyId = companyId;
        this.data = data;
    }
}
