package com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool;

import java.io.File;

/**
 * Created by Administrator on 2016/10/24.
 */
public class UploadThread extends Thread {

    public static java.util.concurrent.ConcurrentLinkedQueue file_ids;
    public static int total_download_count = 0;
    public static int success_download_count = 0;
    public static int fail_download_count = 0;
    public static int total_upload_count = 0;
    public static int success_upload_count = 0;
    public static int upload_thread_count = 0;

    private int thread_index;
    private String filePath;
    private UploadTool uploader;
    private UploadListener uploadListener;
    private File file = null;

    public UploadThread() {
    }

    public void uploadByPath(int index , String filePath , UploadListener uploadListener){
        this.filePath = filePath;
        this.thread_index = index;
        this.uploadListener = uploadListener;
        this.start();
    }

    public void uploadByFile(int index , File file , UploadListener uploadListener){
        this.file = file;
        this.thread_index = index;
        this.uploadListener = uploadListener;
        this.start();
    }

    public void run() {
        try {
            upload_thread_count++;
            uploader = new UploadTool();
            uploader.setUploadListener(uploadListener);
            System.out.println("upload thread " + this.thread_index + " start");
            for (int i = 0; i < 1; i++) {
                total_upload_count++;
                if (file != null) {
                    if (uploader.uploadFile(file) == 0) {
                        success_upload_count++;
                    }
                }else {
                    if (uploader.uploadFile(filePath) == 0) {
                        success_upload_count++;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } finally {
            upload_thread_count--;
        }

        System.out.println("upload thread " + this.thread_index
                + " exit, total_upload_count: " + total_upload_count
                + ", success_upload_count: " + success_upload_count
                + ", total_download_count: " + total_download_count
                + ", success_download_count: " + success_download_count);
    }




}
