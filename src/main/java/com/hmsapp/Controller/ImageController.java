package com.hmsapp.Controller;

import com.hmsapp.Service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
// This is for handling file upload.
public class ImageController {

    @Autowired
    BucketService bucketService;
    @PostMapping("/upload/file/{bucketName}/property/{propertyId}")
    public ResponseEntity<?> uplodPropertyPhotos(@RequestParam MultipartFile file,
                                                 @PathVariable String bucketName,
                                                 @PathVariable Long propertyId){
        String imageUrl= String.valueOf(bucketService.uploadFile(file, bucketName));
        return new  ResponseEntity<>(imageUrl, HttpStatus.OK);
    }



}
