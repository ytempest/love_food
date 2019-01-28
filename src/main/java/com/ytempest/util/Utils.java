package com.ytempest.util;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.UserInfoVO;

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

    public static void update(UserInfoVO oldInfo, UserInfoVO newInfo) {
        if (newInfo == null) {
            return;
        }
        if (newInfo.getUserHeadUrl() != null) {
            oldInfo.setUserHeadUrl(newInfo.getUserHeadUrl());
        }
        if (newInfo.getUserSex() != null) {
            oldInfo.setUserSex(newInfo.getUserSex());
        }
        if (newInfo.getUserBirth() != null) {
            oldInfo.setUserBirth(newInfo.getUserBirth());
        }
        if (newInfo.getUserPhone() != null) {
            oldInfo.setUserPhone(newInfo.getUserPhone());
        }
        if (newInfo.getUserEmail() != null) {
            oldInfo.setUserEmail(newInfo.getUserEmail());
        }
        if (newInfo.getUserQQ() != null) {
            oldInfo.setUserQQ(newInfo.getUserQQ());
        }
    }

    public static String getString(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return null;
    }


    public static int getPageCount(Integer pageNum, Integer pageSize, long total) throws ServiceException {
        int pageCount = (int) (total % pageSize == 0
                ? total / pageSize
                : total / pageSize + 1);
        // 判断输入的页码是否超过数据的页码范围
        if (pageNum < 1) {
            throw new ServiceException("页码数必须要大于等于1");
        }
        if (pageNum > pageCount) {
            throw new ServiceException("已经到底");
        }
        return pageCount;
    }


}
