package com.bidanet.bdcms.plugin.bdUpload.core;

import com.bidanet.bdcms.plugin.bdUpload.common.FileUploadTool;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/29.
 */
public class FastDfsFileDriveImpl implements FastDfsFileDrive {


    protected String uploadToFastdfs(String filePath){
        String path = FileUploadTool.uploadFile(new File(filePath));
        return path;
    }

    public String uploadImage(File file) {
        return uploadToFastdfs(file.getPath());
    }

    @Override
    public String uploadImage(InputStream in , String fileName){
        File file = new File(fileName);
        try {
            FileUtils.copyInputStreamToFile(in,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadToFastdfs(file.getPath());
    }

}
