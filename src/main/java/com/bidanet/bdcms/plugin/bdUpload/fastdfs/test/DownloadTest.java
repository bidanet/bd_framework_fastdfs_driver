package com.bidanet.bdcms.plugin.bdUpload.fastdfs.test;


import com.bidanet.bdcms.plugin.bdUpload.common.MyException;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.ClientGlobal;
import com.bidanet.bdcms.plugin.bdUpload.fastdfs.tool.DownloadThread;

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/24.
 */
public class DownloadTest {

    public static void main(String args[]) {
        try {
            String configPath = System.getProperty("user.dir") + "\\src\\fdfs_client.conf";
            System.out.println(configPath);
            ClientGlobal.init(configPath);
            new DownloadThread(0 , "group1/M00/00/00/wKhkP1gNn8-AMH5bAo-8jvV0Qjo445.avi").start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }


    }

}
