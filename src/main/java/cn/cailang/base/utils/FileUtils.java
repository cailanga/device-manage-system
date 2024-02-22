package cn.cailang.base.utils;

import java.io.File;

/**
 * @ClassName: FileUtils
 * @Description: TODO
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/26 16:00
 * @Version 1.0
 **/
public class FileUtils {
    public static void createFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            System.out.println("Folder already exists.");
            return;
        }
        boolean result = folder.mkdir();
        if (result) {
            System.out.println("Folder created successfully.");
        } else {
            File parentFolder = folder.getParentFile();
            if (!parentFolder.isDirectory()) {
                createFolder(parentFolder.getPath());
            }
            boolean retry = folder.mkdir();
            if (retry) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create folder.");
            }
        }
    }
}
