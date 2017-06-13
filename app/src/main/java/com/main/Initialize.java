package com.main;

import com.server.RemoteServer;
import com.utils.storage.StorageUtil;
import com.utils.storage.factory.StorageFactory;

/**
 * Created by Charan on 3/19/2017.
 */
public class Initialize {
    private StorageUtil storage;
    private String serverUrl;
    private String storageType;

    public Initialize(String storageType, String serverUrl) {
        this.storageType = storageType;
        this.serverUrl = serverUrl;
    }

    public Initialize(String storageType) {
        this.storageType = storageType;
    }

    public void initializeAll () {
        setStorage(initializeDB(getStorageType()));
        initializeServer(getServerUrl());
    }

    private StorageUtil initializeDB(String storageType) {
        storage = new StorageFactory().getStorage(storageType);
        return storage;
    }

    private void initializeServer(String url) {
        RemoteServer.getRemoteServer(url);
    }

    private void setStorage(StorageUtil storage) {

        this.storage = storage;
    }
    public StorageUtil getStorage() {

        return storage;
    }
    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
