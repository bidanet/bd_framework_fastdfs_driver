package com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool;

import com.bidanet.bdcms.plugin.bdUpload.common.FileTool;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.*;

/**
 * Created by Administrator on 2016/10/24.
 */
public class DownloadTool {

    public TrackerClient tracker;
    public TrackerServer trackerServer;
    public DownloadFileDiscard callback;

    public DownloadTool() throws Exception {
        this.tracker = new TrackerClient();
        this.trackerServer = tracker.getConnection();
        this.callback = new DownloadFileDiscard();
    }

    public int downloadFile(String file_id) throws Exception {
        int errno;
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        try {
            errno = client.download_file1(file_id, this.callback);

            if (errno != 0) {
                System.out.println("Download file fail, file_id: " + file_id + ", error no: " + errno);
            }
            return errno;
        } catch (Exception ex) {
            System.out.println("Download file fail, error mesg = " + ex.getMessage());
            return -1;
        }
    }


    public class DownloadFileDiscard implements DownloadCallback {
        public DownloadFileDiscard() {

        }

        byte[] file = {};
        int index = 0;

        public int recv(long file_size, byte[] data, int bytes) {
            System.out.println("callback  data = " + data + ", bytes = " + bytes + ",filesize = " + file_size);
            for (int i = 0; i < data.length; i++) {
                file[index++] = data[i];
            }
            if (bytes == 200) {
                FileTool.byte2File(data, "C:\\Users\\Administrator\\Desktop\\download", "shipin.avi");
            }
            return 0;
        }
    }

}
