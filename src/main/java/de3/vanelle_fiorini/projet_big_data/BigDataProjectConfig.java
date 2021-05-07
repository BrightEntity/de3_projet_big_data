package de3.vanelle_fiorini.projet_big_data;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BigDataProjectConfig {
    @Value("${cloud.aws.credentials.secret-key}")
    private String awsKeySecret;



    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.credentials.access-key}")
    private String awsKeyId;
    @Value("${cloud.aws.s3.audio.bucket}")
    private String awsS3Bucket;

    private HiveCollector hiveCollector;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Hello world");
        };
    }

    @Bean(name = "awsKeyId")
    public String getAWSKeyId() {
        return awsKeyId;
    }

    @Bean(name = "awsKeySecret")
    public String getAwsKeySecret() {
        return awsKeySecret;
    }

    @Bean(name = "awsRegion")
    public String getAwsRegionString() {
        return awsRegion;
    }

    @Bean
    public AmazonS3 amazons3(AWSCredentialsProvider credentials) {
        return AmazonS3ClientBuilder.standard().withCredentials(credentials).withRegion(this.awsRegion).build();
    }

    @Bean(name = "awsCredentialsProvider")
    public AWSCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.awsKeyId, this.awsKeySecret);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    @Bean(name = "awsS3Bucket")
    public String getAWSS3Bucket() {
        return awsS3Bucket;
    }

    @Bean
    public void monProgramme(HiveCollector hiveCollector) {
        hiveCollector.getAmazonS3();
    }

}
