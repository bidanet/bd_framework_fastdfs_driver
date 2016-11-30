package com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool;

/**
 * Created by Administrator on 2016/10/24.
 */
public class DownloadThread extends Thread {

    private int thread_index;
    private String fileId;

    public DownloadThread(int index , String fileId) {
        this.thread_index = index;
        this.fileId = fileId;
    }

    public void run() {
        try {
            DownloadTool downloadTool = new DownloadTool();
            downloadTool.downloadFile(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
