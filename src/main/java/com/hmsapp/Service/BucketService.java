package com.hmsapp.Service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class BucketService {

    @Autowired
    private AmazonS3 amazonS3;

    public URL uploadFile(MultipartFile file, String bucketName) {
        if(file.isEmpty()){
            throw new RuntimeException("Empty  to upload file");
        }
        try {
            File convFile=new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
            file.transferTo(convFile);
            amazonS3.putObject(bucketName, file.getOriginalFilename(), convFile);
            return amazonS3.getUrl(bucketName,file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
