package com.ytempest.common;

import java.io.File;

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
        // 初始化存放话题图片的文件夹
        String projectPath = Utils.getProjectPath(servletContextEvent);
        File topicImageDir = new File(projectPath, Configure.TOPIC_IMAGE_DIR);
        if (!topicImageDir.exists()) {
            boolean result = topicImageDir.mkdir();
            topicImageDir.setReadable(true);
            LogUtils.d(TAG, "contextInitialized: 创建存放话题图片的文件夹，创建结果：" + result);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
