package com.example.storageservice.storage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StorageInfoList {
    List<StorageInfo> storageInfoList;

    public StorageInfoList() {

    }

    public StorageInfoList(List<StorageInfo> storageInfoList) {
        this.storageInfoList = storageInfoList;
    }
}
