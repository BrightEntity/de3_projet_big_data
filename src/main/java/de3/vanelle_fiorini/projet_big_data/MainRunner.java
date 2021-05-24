package de3.vanelle_fiorini.projet_big_data;

import com.amazonaws.services.s3.AmazonS3;
import de3.vanelle_fiorini.projet_big_data.models.CSVable;
import de3.vanelle_fiorini.projet_big_data.models.Categorie;
import de3.vanelle_fiorini.projet_big_data.models.Cv;
import de3.vanelle_fiorini.projet_big_data.models.Label;
import de3.vanelle_fiorini.projet_big_data.service.impl.AmazonS3ClientServiceImpl;
import de3.vanelle_fiorini.projet_big_data.service.impl.CSVClientService;
import de3.vanelle_fiorini.projet_big_data.service.impl.HiveClientServiceImpl;
import jdk.internal.util.xml.impl.Input;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainRunner implements CommandLineRunner {
    @Autowired
    private HiveClientServiceImpl hiveClientService;

    @Autowired
    private AmazonS3ClientServiceImpl amazonS3ClientService;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private CSVClientService csvClientService;

    @Override
    public void run(String... args) throws Exception {
        try {
            List<Cv> cvs = hiveClientService.getCvsFromHive(100);
            List<Categorie> categories = hiveClientService.getCategoriesFromHive();
            List<Label> labels = hiveClientService.getLabelsFromHive();

            // List to CSV
            try {
                amazonS3ClientService.uploadFileToS3Bucket(csvClientService.getExportFileRequest(cvs), false);
                amazonS3ClientService.uploadFileToS3Bucket(csvClientService.getExportFileRequest(categories), false);
                amazonS3ClientService.uploadFileToS3Bucket(csvClientService.getExportFileRequest(labels), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
