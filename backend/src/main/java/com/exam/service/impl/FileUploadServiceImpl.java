package com.exam.service.impl;

import com.exam.config.properties.MinioProperties;
import com.exam.service.FileUploadService;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传服务
 * 支持MinIO和本地文件存储两种方式
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;
    
    // 本地文件存储路径
    @Value("${file.upload.path:./uploads/}")
    private String localUploadPath;
    // 本地文件访问URL前缀
    @Value("${file.upload.url-prefix:http://localhost:8080/files/}")
    private String localFileUrlPrefix;
    
    @Override
    public Map<String, Object> uploadFile(MultipartFile file, String folder) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 尝试使用MinIO上传
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!bucketExists) {
                String policy = """
                        {
                            "Statement" : [ {
                            "Action" : "s3:GetObject",
                            "Effect" : "Allow",
                            "Principal" : "*",
                            "Resource" : "arn:aws:s3:::%s/*"
                        } ],
                        "Version":"2012-10-17"
                        }""".formatted(minioProperties.getBucketName());
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(minioProperties.getBucketName()).config(policy).build());
            }
            String objectName = folder+"/"+ new SimpleDateFormat("yyyyMMdd").format(new Date()) +UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .contentType(file.getContentType())
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                    .build());
            String url = String.join("/", minioProperties.getEndPoint(), minioProperties.getBucketName(), objectName);
            log.info("MinIO文件上传成功，回显地址为：{}", url);
            
            // 将URL放入Map返回
            result.put("url", url);
        } catch (Exception e) {
            // MinIO上传失败，使用本地文件存储
            log.warn("MinIO上传失败，切换到本地文件存储，错误信息：{}", e.getMessage());
            
            // 创建本地存储目录
            String localFolder = localUploadPath + folder + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
            File directory = new File(localFolder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;
            
            // 保存文件到本地
            Path filePath = Paths.get(localFolder, fileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath);
            }
            
            // 生成访问URL
            String url = localFileUrlPrefix + folder + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + fileName;
            log.info("本地文件上传成功，回显地址为：{}", url);
            
            // 将URL放入Map返回
            result.put("url", url);
        }
        
        result.put("filename", file.getOriginalFilename());
        result.put("size", file.getSize());
        result.put("contentType", file.getContentType());
        
        return result;
    }
}