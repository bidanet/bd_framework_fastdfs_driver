package com.bidanet.bdcms.plugin.bdUpload.common;

import com.bidanet.bdcms.plugin.bdUpload.fastdfs.*;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool.UploadTool;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/15.
 */
public class FileUploadTool {

    UploadTool uploader;

    public static String uploadFile(File file) {
        String path="";
        try {
//            String e = "/resources/fdfs_client.conf/";
//            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("fdfs_client.conf");
//            System.out.println(applicationContext);
            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            String e = url.getPath() + "fdfs_client.conf";
            System.out.println(e);
            ClientGlobal.init(e);
            path = upload(file);
        } catch (IOException var5) {
            var5.printStackTrace();
            return "上传失败";
        } catch (MyException var6) {
            var6.printStackTrace();
            return "上传失败";
        }
        return path;
    }



    private static String upload(File file) {
        Object storageServer = null;
        String file_path="";
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageClient1 client = new StorageClient1(trackerServer, (StorageServer)storageServer);

            String ex = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            String file_id = client.upload_file1(FileTool.File2byteByFile(file), ex, (NameValuePair[])null);
            if(file_id == null) {
                System.out.println("upload file fail, error code: " + client.getErrorCode());
            } else {
                String trackerPath = ClientGlobal.g_tracker_group.tracker_servers[0].toString().substring(1, ClientGlobal.g_tracker_group.tracker_servers[0].toString().length() - 5);
                file_path = trackerPath + "8080/" + file_id;
                System.out.println(file_path);
                System.out.println("file_id = " + file_id);
            }
        } catch (Exception var8) {
            System.out.println("upload file fail, error mesg: " + var8.getMessage());
        }
        return file_path;
    }

}
