package com.wsbxd.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * description: 文件操作工具类
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/9/23 15:29
 */
public class FileUtil {

    /**
     * 多个文件合并为一个文件
     * @param path              将要合并的文件目录
     * @param mergeToFile       合并的文件
     * @param deleteThisFile    是否删除被合并文件
     */
    public static void multiFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
        mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        //对files排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
                if (o1Index > o2Index) {
                    return 1;
                } else if (o1Index == o2Index){
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(mergeToFile), "UTF-8");
            for (File file : files) {
                BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line = null;
                while ((line = bufr.readLine()) != null) {
                    out.println(line);
                }
                bufr.close();

                if (deleteThisFile) {
                    file.delete();
                }
            }
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        } finally {
            out.close();
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            }
            else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 读取JSON文件返回对象
     * @param path 文件路径
     * @return  json对象
     * @throws IOException IO异常
     */
    public static JSONObject readJsonFile(String path) throws IOException {
        //读取关系文件获取其对象
        Resource resource = new ClassPathResource(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuilder subjectRelationJson = new StringBuilder();
        reader.lines().forEach(subjectRelationJson::append);
        return JSONObject.parseObject(subjectRelationJson.toString());
    }
}
