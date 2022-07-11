package com.siyi.ceph.demo.startup;

import com.siyi.ceph.demo.service.CephOperator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-12 00:27
 * @Description:
 */
@SpringBootApplication
public class CephDemoApplication {

    public static void main(String[] args) {

        System.out.println("start....");
        String username = "admin";
        String monIp = "192.168.31.67:6789;192.168.31.68:6789;192.168.31.69:6789";
        String userKey = "AQCtocpiYXWLLhAAsfP692iq9SE5Y3IMNVp1pg==";
        String mountPath = "/";
        CephOperator cephOperate = null;
        try {
            String opt = (args == null || args.length < 1)? "" : args[0];
            cephOperate = new CephOperator(username, monIp, userKey, mountPath);
            if("upload".equals(opt)) {
                cephOperate.uploadFileByPath("/temp_upload_fs", args[1]);
            }else if("download".equals(opt)) {
                cephOperate.downloadFileByPath("/temp_download_fs", args[1]);
            }else {
                System.out.println("Unrecognized Command! Usage  opt[upload|download] filename[path]!");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(null != cephOperate) {
                cephOperate.umount();
            }
        }
        System.out.println("end....");

    }

}