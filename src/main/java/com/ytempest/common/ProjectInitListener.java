package com.ytempest.common;

import com.ytempest.util.FileUtils;
import com.ytempest.util.LogUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author ytempest
 * Description：
 */
public class ProjectInitListener implements ServletContextListener {

    private static final String TAG = "ProjectInitListener";

    /**
     * 初始化一些基本数据
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 初始化工程的路径
        FileUtils.initProjectPath(servletContextEvent);

        // 初始化存放话题图片的文件夹
        if (FileUtils.initDirs(FileUtils.getTopicImageDir())) {
            LogUtils.d(TAG, "contextInitialized: 创建存放话题图片的文件夹，创建结果：成功");
        }

        // 初始化存放菜谱成品图片的文件夹
        if (FileUtils.initDirs(FileUtils.getCookImageDir())) {
            LogUtils.d(TAG, "contextInitialized: 创建存放菜谱成品图片的文件夹，创建结果：成功");
        }

        // 初始化菜谱步骤的图片的文件夹
        if (FileUtils.initDirs(FileUtils.getCookProcedureImageDir())) {
            LogUtils.d(TAG, "contextInitialized: 创建存放菜谱步骤图片的文件夹，创建结果：成功");
        }

        // 初始化存放用户头像的文件夹
        if (FileUtils.initDirs(FileUtils.getUserHeadDir())) {
            LogUtils.d(TAG, "contextInitialized: 创建存放用户头像的文件夹，创建结果：成功");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
