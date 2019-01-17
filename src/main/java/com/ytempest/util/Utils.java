package com.ytempest.util;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ytempest
 * Description：
 */
public class Utils {
    /**
     * 判断该请求是有Multipart
     */
    public static boolean isHaveMultipart(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data".
        return multipartResolver.isMultipart(request);
    }
}
