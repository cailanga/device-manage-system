package cn.cailang.base.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 自己码对象工具类
 */
public class ClassUtils {
    /**
     * 获取一个包下面的所有类全限定名
     * @param packageNames 包
     * @return
     */
    public static List<Class> getAllClassName(String packageNames){
        String[] split = packageNames.split(",");
        List<Class> result = new ArrayList<>();
        for (String packageName : split) {
            // 包相对路径
            String packagePath = packageName.replace(".","/"); //把包路径中的点替换为目录
            // 资源URL
            URL url = ClassUtils.class.getClassLoader().getResource(""); //获取classapth
            File[] files = new File(url.getPath() + packagePath) //获取classapth+packagePath,就是完整的路径
                    .listFiles(file -> file.getName().endsWith(".class"));
            if(files == null || files.length < 1){
                continue;
            }
            for(File file : files){
                // 输出类名称
                String fileName = file.getName();
                fileName = fileName.substring(0,fileName.lastIndexOf("."));
                //全限定类名
                String allName = packageName+"."+fileName;
                try {
                    result.add(Class.forName(allName));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}