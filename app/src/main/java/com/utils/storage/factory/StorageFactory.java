package com.utils.storage.factory;

import com.utils.storage.StorageUtil;
import com.utils.storage.local.LocalStorage;
import com.utils.storage.remote.RemoteStorage;

/**
 * Created by Charan-celigo on 3/19/2017.
 */
public class StorageFactory {
    public StorageUtil getStorage (String type) {
        StorageUtil storage;
        if (type.equalsIgnoreCase("local")) {
            storage = new LocalStorage();
        } else if (type.equalsIgnoreCase("remote")) {
            storage = new RemoteStorage();
        } else {
            storage = null;
        }
        return storage;
    }
}
