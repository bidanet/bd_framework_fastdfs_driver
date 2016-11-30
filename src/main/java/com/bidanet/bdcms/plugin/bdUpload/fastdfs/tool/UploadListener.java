package com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface UploadListener {
    void success(String url);
    void error(Exception e);
}
