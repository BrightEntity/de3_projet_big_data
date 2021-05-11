package de3.vanelle_fiorini.projet_big_data;

import com.amazonaws.services.s3.AmazonS3;
import de3.vanelle_fiorini.projet_big_data.service.impl.AmazonS3ClientServiceImpl;
import de3.vanelle_fiorini.projet_big_data.service.impl.HiveClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainRunner implements CommandLineRunner {
    @Autowired
    private HiveClientServiceImpl hiveClientService;

    @Autowired
    private AmazonS3ClientServiceImpl amazonS3ClientService;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public void run(String... args) throws Exception {
        amazonS3.listBuckets().stream().forEach(n -> System.out.println(n.getName()));
    }
}
