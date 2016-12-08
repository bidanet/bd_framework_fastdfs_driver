package com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool;

import com.bidanet.bdcms.plugin.bdUpload.fastdfs.StorageClient1;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.StorageServer;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.TrackerClient;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.TrackerServer;

import java.io.File;

import static com.bidanet.bdcms.plugin.bdUpload.fastdfs.ClientGlobal.g_tracker_group;

/**
 * Created by Administrator on 2016/10/24.
 */
public class UploadTool {

    public static java.util.concurrent.ConcurrentLinkedQueue file_ids;
    public TrackerClient tracker;
    public TrackerServer trackerServer;

    private UploadListener uploadListener;


    public UploadTool() throws Exception {
        this.tracker = new TrackerClient();
        this.trackerServer = tracker.getConnection();
    }

    public int uploadFile(String filePath) throws Exception {
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        String file_id;
        try {
            String prefix = filePath.substring(filePath.lastIndexOf(".") + 1);
            file_id = client.upload_file1(filePath, prefix, null);
            if (file_id == null) {
                System.out.println("upload file fail, error code: " + client.getErrorCode());
                return -1;
            }
            String trackerPath = g_tracker_group.tracker_servers[0].toString().substring(1,g_tracker_group.tracker_servers[0].toString().length()-5);
            String file_path = trackerPath + "8080/" + file_id;
            System.out.println(file_path);
            if (uploadListener!=null){
                uploadListener.success(file_path);
            }
            System.out.println("file_id = " + file_id);
            return 0;
        } catch (Exception ex) {
            System.out.println("upload file fail, error mesg: " + ex.getMessage());
            if (uploadListener!=null){
                uploadListener.error(ex);
            }
            return -1;
        }
    }

    public int uploadFile(File file) throws Exception {
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        String file_id;
        try {

            String prefix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
//            file_id = client.upload_file1(FileTool.File2byteByFile(file), prefix, null);
            String s = file.getPath();
            file_id = client.upload_file1(file.getPath(), prefix, null);
            if (file_id == null) {
                System.out.println("upload file fail, error code: " + client.getErrorCode());
                return -1;
            }
            String trackerPath = g_tracker_group.tracker_servers[0].toString().substring(1,g_tracker_group.tracker_servers[0].toString().length()-5);
            String file_path = trackerPath + "8080/" + file_id;
            System.out.println(file_path);
            if (uploadListener!=null){
                uploadListener.success(file_path);
            }
            System.out.println("file_id = " + file_id);
            return 0;
        } catch (Exception ex) {
            System.out.println("upload file fail, error mesg: " + ex.getMessage());
            if (uploadListener!=null){
                uploadListener.error(ex);
            }
            return -1;
        }
    }


    public void setUploadListener(UploadListener uploadListener) {
        this.uploadListener = uploadListener;
    }

}
