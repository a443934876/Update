package com.xiaoxi.update.file;

import java.io.File;

/**
 * Created by zhouhui on 2017/12/21.
 */

public class FileUtil {
    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static File createDirFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
