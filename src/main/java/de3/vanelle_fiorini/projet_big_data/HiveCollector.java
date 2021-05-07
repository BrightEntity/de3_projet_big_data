package de3.vanelle_fiorini.projet_big_data;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HiveCollector {
    private AmazonS3 amazonS3;

    @Autowired
    public HiveCollector(AmazonS3 amazons3) {
        this.amazonS3 = amazons3;
    }

    public void getS3Infos() {
        System.out.println(amazonS3.getRegionName());
    }

    public AmazonS3 getAmazonS3() {
        return this.amazonS3;
    }
}
