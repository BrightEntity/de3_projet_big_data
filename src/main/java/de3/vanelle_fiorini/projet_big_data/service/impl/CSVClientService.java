package de3.vanelle_fiorini.projet_big_data.service.impl;

import de3.vanelle_fiorini.projet_big_data.models.CSVable;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVClientService {
    public MultipartFile getExportFileRequest(List<? extends CSVable> lignes) throws IOException {
        // List to CSV
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(lignes.stream().map(ligne -> ligne.toCSV()).collect(Collectors.toList()));

        ByteArrayInputStream input = new ByteArrayInputStream(baos.toByteArray());

        MultipartFile file = new MockMultipartFile("cvs.csv", input);

        return file;
    }
}
