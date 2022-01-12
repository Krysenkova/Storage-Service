package com.example.storageservice.utils;

public interface FileTransferService {

    boolean downloadFile(String localFilePath, String remoteFilePath);

}
