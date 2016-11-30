package com.bidanet.bdcms.plugin.bdUpload.core;

import javax.print.DocFlavor;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface FastDfsFileDrive {

    String uploadImage(File file);

    String uploadImage(InputStream in , String fileName);

}
