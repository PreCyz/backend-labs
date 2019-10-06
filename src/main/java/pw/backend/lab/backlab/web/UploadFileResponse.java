package pw.backend.lab.backlab.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Created by Pawel Gawedzki on 06-Oct-2019. */
@Getter
@Setter
@AllArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
