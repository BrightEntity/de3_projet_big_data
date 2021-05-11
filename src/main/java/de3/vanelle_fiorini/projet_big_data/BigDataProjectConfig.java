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
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClientFactory;
import com.mongodb.client.MongoClients;
import de3.vanelle_fiorini.projet_big_data.service.impl.AmazonS3ClientServiceImpl;
import de3.vanelle_fiorini.projet_big_data.service.impl.HiveClientServiceImpl;
import de3.vanelle_fiorini.projet_big_data.service.impl.MongoDBClientServiceImpl;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

    @Value("${spring.data.mongodb.host}")
    private String mongoDBHost;

    @Value("${spring.data.mongodb.username}")
    private String mongoDBUsername;

    @Value("${spring.data.mongodb.password}")
    private String mongoDBPassword;

    @Bean(name = "mongoDBUsername")
    public String getMongoDBUsername() {
        return mongoDBUsername;
    }
    @Bean(name = "mongoDBPassword")
    public String getMongoDBPassword() {
        return mongoDBPassword;
    }
    @Bean(name = "mongoDBDatabase")
    public String getMongoDBDatabase() {
        return mongoDBDatabase;
    }

    @Value("${spring.data.mongodb.database}")
    private String mongoDBDatabase;

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

    @Bean(name = "warehouseLocation")
    public String getWarehouseLocation() { return this.warehouseLocation; }

    @Bean(name = "mongoDBHost")
    public String getMongoDBHost() { return this.mongoDBHost; }

    @Bean(name = "mongoClient")
    public MongoClient getMongoClient(String mongoDBHost, String mongoDBDatabase, String mongoDBUsername, String mongoDBPassword) {
        String connectionString = "mongodb://" + mongoDBHost + "/" + mongoDBDatabase;

        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .retryWrites(true)
                .build());
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

    @Bean
    public SparkSession sparkSession(String warehouseLocation, String jdbcHiveConnectionString) {
        return SparkSession.builder()
                .appName("Spark Hive Collector")
                .master("local")
                .config("spark.sql.hive.hiveServer2.jdbc.url", jdbcHiveConnectionString)
                .config("spark.sql.warehouse.dir", warehouseLocation)
                .enableHiveSupport()
                .getOrCreate();
    }

    @Bean(name = "awsS3Bucket")
    public String getAWSS3Bucket() {
        return awsS3Bucket;
    }

    /*
    @Bean
    public void monProgramme(AmazonS3ClientServiceImpl amazons3Client, HiveClientServiceImpl hiveClient) {
        //hiveCollector.getAmazonS3();
        //hiveClient.getDataFromHive();
        // Une fois les champs récupérés on va transformer ça en csv
    }
    */

}
