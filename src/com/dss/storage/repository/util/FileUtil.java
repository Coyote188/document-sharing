package com.dss.storage.repository.util;

import java.io.InputStream;

public interface FileUtil
{
    public final static String FILE_STORE_LOCATION = "document";
    
    public final static String DOCPREIMAGE_STORE_LOCATION = "preimage";
    
    public final static String AVATAR_STORE_LOCATION = "avatar";

    public String saveFile(String fileName, String location, byte[] in);

    public InputStream getFile(String md5, String location);

    public void removeFile(String name, String fileStoreLocation);

}