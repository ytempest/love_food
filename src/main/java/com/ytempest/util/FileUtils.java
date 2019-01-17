package com.ytempest.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContextEvent;

public class FileUtils {

    private static String PROJECT_PATH;

    public static String getProjectPath() {
        return PROJECT_PATH;
    }

    public static void initProjectPath(ServletContextEvent contextEvent) {
        PROJECT_PATH = contextEvent.getServletContext().getRealPath(".");
    }

    public static String generateImageName(String filename) {
        return UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
    }

    public static String getTopicImageDir() {
        return PROJECT_PATH + File.separator + Configure.TOPIC_IMAGE_DIR;
    }

    public static String getCookImageDir() {
        return PROJECT_PATH + File.separator + Configure.COOK_IMAGE_DIR;
    }

    public static String getCookProcedureImageDir() {
        return PROJECT_PATH + File.separator + Configure.COOK_PROCEDURE_DIR;
    }

    public static String getUserHeadDir() {
        return PROJECT_PATH + File.separator + Configure.USER_HEAD_DIR;
    }

    public static String generateTopicImageUrl(String name) {
        return String.format("/%s/%s",
                Configure.TOPIC_IMAGE_DIR, name);
    }

    public static String generateCookImageUrl(String name) {
        return String.format("/%s/%s",
                Configure.COOK_IMAGE_DIR, name);
    }

    public static String generateCookProcedureImageUrl(String name) {
        return String.format("/%s/%s",
                Configure.COOK_PROCEDURE_DIR, name);
    }


    public static String generateUserHeadUrl(String name) {
        return String.format("/%s/%s",
                Configure.USER_HEAD_DIR, name);
    }


    public static boolean initDirs(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return false;
    }


    interface Configure {
        String IMAGE_DIR = "image";
        String TOPIC_IMAGE_DIR = IMAGE_DIR + "/topicImage";
        String COOK_IMAGE_DIR = IMAGE_DIR + "/cookImage";
        String COOK_PROCEDURE_DIR = IMAGE_DIR + "/cookProcedure";
        String USER_HEAD_DIR = IMAGE_DIR + "/userHead";
    }


}
