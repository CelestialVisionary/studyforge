package com.exam.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件上传服务
 * 支持MinIO和本地文件存储两种方式
 */

import java.util.Map;

public interface FileUploadService {
    /**
     * 文件上传业务方法
     * @param file 上传的文件对象
     * @param folder 在minio中存储的文件夹（轮播图：banners 视频：videos）
     * @return 返回包含url等信息的Map
     */
    Map<String, Object> uploadFile(MultipartFile file, String folder) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}