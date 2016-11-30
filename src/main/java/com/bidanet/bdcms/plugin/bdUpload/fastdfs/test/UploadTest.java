package com.bidanet.bdcms.plugin.bdUpload.fastdfs.test;


import com.bidanet.bdcms.plugin.bdUpload.common.MyException;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.*;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool.UploadListener;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool.UploadThread;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/24.
 */
public class UploadTest {
    public static TrackerClient tracker;
    public static TrackerServer trackerServer;

    public static void main(String args[]) {
        UploadListener uploadListener = new UploadListener() {
            public void success(String url) {
                System.out.println("url = " + url);
            }

            public void error(Exception e) {

            }
        };

        File file = new File("C:\\Users\\Administrator\\Desktop\\test.avi");
//        File file = new File("G:\\其他\\dahuaxiyou3.mp4");

//        uploadFile(file , uploadListener);

        try {
            String configPath = System.getProperty("user.dir") + "\\src\\fdfs_client.conf";
            System.out.println(configPath);
            ClientGlobal.init(configPath);
            for (int i = 0; i < 20; i++) {
                UploadThread thread = new UploadThread();
                thread.uploadByFile(0, file, uploadListener);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    private static void uploadFile(File file, UploadListener uploadListener) {
        String configPath = System.getProperty("user.dir") + "\\src\\fdfs_client.conf";
        try {
            ClientGlobal.init(configPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        tracker = new TrackerClient();
        try {
            trackerServer = tracker.getConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }

        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        String file_id;
        try {
            String prefix = "aa_";
            file_id = client.upload_file1(file.getPath(), prefix, null);
            if (file_id == null) {
                System.out.println("upload file fail, error code: " + client.getErrorCode());
            } else {
                System.out.println(file_id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

}
