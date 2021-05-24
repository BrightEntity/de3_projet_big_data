package de3.vanelle_fiorini.projet_big_data;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import de3.vanelle_fiorini.projet_big_data.service.impl.AmazonS3ClientServiceImpl;
import de3.vanelle_fiorini.projet_big_data.service.impl.HiveClientServiceImpl;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.sql.DataSource;

@Configuration
public class BigDataProjectConfig {
    @Bean(name = "awsSessionToken")
    public String getAwsSessionToken() {
        return awsSessionToken;
    }

    @Value("${cloud.aws.credentials.session-token}")
    private String awsSessionToken;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsKeySecret;

    @Value("${hive.warehouseLocation}")
    private String warehouseLocation;

    @Bean(name = "jdbcHiveConnectionString")
    public String getJdbcHiveConnectionString() {
        return jdbcHiveConnectionString;
    }

    @Value("${spark.sql.hive.hiveServer2.jdbc.url}")
    private String jdbcHiveConnectionString;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.credentials.access-key}")
    private String awsKeyId;
    @Value("${cloud.aws.s3.audio.bucket}")
    private String awsS3Bucket;

    private HiveCollector hiveCollector;

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

    @Bean(name = "warehouseLocation")
    public String getWarehouseLocation() { return this.warehouseLocation; }

    @Bean(name = "hiveDataSource")
    public DataSource getHiveDataSource(String jdbcHiveConnectionString) {
        return DataSourceBuilder.create().url(jdbcHiveConnectionString)
                .username("hive")
                .password("hive")
                .driverClassName("org.apache.hive.jdbc.HiveDriver")
                .build();
    }

    @Bean
    public AmazonS3 amazons3(AWSCredentialsProvider awsCredentialsProvider) {
        return AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider).withRegion(this.awsRegion).build();
    }

    @Bean(name = "awsCredentialsProvider")
    public AWSCredentialsProvider getAWSCredentials() {
        BasicSessionCredentials awsCredentials = new BasicSessionCredentials(this.awsKeyId, this.awsKeySecret, this.awsSessionToken);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    @Bean(name = "awsS3Bucket")
    public String getAWSS3Bucket() {
        return awsS3Bucket;
    }

}
