package de3.vanelle_fiorini.projet_big_data.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import de3.vanelle_fiorini.projet_big_data.service.AmazonS3ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService {
    private AmazonS3 amazonS3;
    private String awsS3Bucket;
    private static final Logger logger = LoggerFactory.getLogger(AmazonS3ClientServiceImpl.class);

    @Autowired
    public AmazonS3ClientServiceImpl(String awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3Bucket) {
        this.awsS3Bucket = awsS3Bucket;

        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion).build();
    }

    @Override
    @Async
    public void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess) {
        String fileName = multipartFile.getOriginalFilename();

        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            PutObjectRequest objRequest = new PutObjectRequest(this.awsS3Bucket, fileName, file);
            this.amazonS3.putObject(objRequest);

            file.delete();
        } catch (IOException | AmazonServiceException e) {
            logger.error("error [" + e.getMessage() + "] occured while uploading [" + fileName + "]");
        }
    }

    @Override
    @Async
    public void deleteFileFromS3Bucket(String fileName) {

        try {
            this.amazonS3.deleteObject(new DeleteObjectRequest(awsS3Bucket, fileName));
        }
        catch (AmazonServiceException e) {
            logger.error("error [" + e.getMessage() + "] occured while removing [" + fileName +  "]");
        }
        }
}
