package com.ytempest.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ytempest.util.DateUtils;
import com.ytempest.util.FileUtils;
import com.ytempest.util.LogUtils;
import com.ytempest.util.NumberUtils;
import com.ytempest.util.SecurityUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.UserInfoMapper;
import com.ytempest.service.UserInfoService;
import com.ytempest.util.TextUtils;
import com.ytempest.util.Utils;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.UserInfoVO;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static final String TAG = "UserInfoServiceImpl";

    /*
     * 每一页显示的记录数
     */
    private final int PAGE_SIZE = 10;

    @Resource(name = "UserInfoMapper")
    private UserInfoMapper mapper;

    @Override
    public void addUser(UserInfoVO user) throws Exception {
        mapper.insert(user);
    }

    @Override
    public void lockUser(String account) throws Exception {
        UserInfoVO vo = mapper.selectByAccount(account);
        vo.setUserStatus(vo.getUserStatus() == 1 ? 0 : 1);
        mapper.updateById(vo);
    }

    @Override
    public void updateUser(UserInfoVO model) throws Exception {
        mapper.updateById(model);
    }


    @Override
    public PageVO<UserInfoVO> page(String pageNumStr) throws Exception {
        // 1、判断页码是否为空，如果没有指定分页的页码就取默认值1
        if (pageNumStr == null) {
            pageNumStr = "1";
        }

        // 2、获取页码
        int pageNum = 0;
        try {
            pageNum = Integer.valueOf(pageNumStr);
        } catch (Exception e) {
            throw new Exception("the format or pageNum is illegal");
        }

        // 3、获取PageVO数据模型所需要的相关参数
        // 获取用户的记录总数
        long total = mapper.countAll();
        // 计算总页面数
        int pageCount = (int) (total % PAGE_SIZE == 0
                ? total / PAGE_SIZE
                : total / PAGE_SIZE + 1);
        // 判断输入的页码是否超过数据的页码范围
        if (pageNum < 1 || pageNum > pageCount) {
            throw new IllegalArgumentException(
                    "page number is out of page count");
        }

        // 4、封装PageVO数据
        PageVO<UserInfoVO> pageVO = new PageVO<UserInfoVO>(total, PAGE_SIZE, pageNum,
                pageCount);
        pageVO.setList(mapper.selectAll((pageNum - 1) * PAGE_SIZE, PAGE_SIZE));

        return pageVO;
    }

    @Override
    public PageVO<UserInfoVO> fuzzySearch(String key, String pageStr)
            throws Exception {

        // 1、将页码转为int类型，如果页码有误则取默认值-1
        int page = 0;
        try {
            page = Integer.valueOf(pageStr);
        } catch (Exception e) {
            page = 1;
        }

        // 2、获取PageVO需要的相关数据
        // 获取模糊搜索后的记录总数
        long count = mapper.countFuzzySearch(key);
        List<UserInfoVO> list = mapper.fuzzySearchByAccount(key,
                (page - 1) * PAGE_SIZE, PAGE_SIZE);
        // 计算页面的总数
        int pageCount = (int) (count % PAGE_SIZE == 0
                ? count / PAGE_SIZE
                : count / PAGE_SIZE + 1);

        // 4、将数据封装到PageVO中
        PageVO<UserInfoVO> pageVO = new PageVO<UserInfoVO>(count, PAGE_SIZE, page,
                pageCount);
        pageVO.setList(list);

        return pageVO;

    }

    @Override
    public UserInfoVO selectByAccount(String account) throws Exception {
        return mapper.selectByAccount(account);
    }

    /**********   API   **********/

    @Override
    public UserInfoVO login(String account, String password) throws ServiceException {

        try {
            UserInfoVO vo = mapper.selectByAccount(account.trim());
            String encryptedPassword = SecurityUtils.encrypt(password.trim());

            if (vo == null) {
                throw new ServiceException("该账号不存在");
            } else if (!encryptedPassword.equals(vo.getUserPwd())) {
                throw new ServiceException("密码错误");
            }
            return vo;
        } catch (SQLException e) {
            throw new ServiceException("账号异常");
        }
    }


    @Override
    public UserInfoVO updateBaseUserInfo(HttpServletRequest request) throws ServiceException {
        UserInfoVO newInfo = obtainUserInfo(request);
        LogUtils.e(TAG, "updateBaseUserInfo: newInfo = " + newInfo);
        try {
            UserInfoVO oldInfo = mapper.selectById(String.valueOf(newInfo.getUserId()));
            LogUtils.e(TAG, "updateBaseUserInfo: oldInfo = " + oldInfo);

            Utils.update(oldInfo, newInfo);
            mapper.updateById(oldInfo);
            return oldInfo;
        } catch (SQLException e) {
            FileUtils.deleteImage(newInfo.getUserHeadUrl());
            throw new ServiceException("修改失败");
        }
    }

    private UserInfoVO obtainUserInfo(HttpServletRequest request) throws ServiceException {
        try {
            if (Utils.isHaveMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = multiRequest.getFileMap();

                String userHeadUrl = null;
                MultipartFile userHead = fileMap.get("userHead");
                // 如果需要修改头像
                if (userHead != null) {
                    String imageName = userHead.getOriginalFilename();
                    String newImageName = FileUtils.generateImageName(imageName);
                    File image = new File(FileUtils.getUserHeadDir(), newImageName);
                    //上传
                    userHead.transferTo(image);
                    userHeadUrl = FileUtils.generateUserHeadUrl(newImageName);
                }

                // 封装信息
                UserInfoVO info = new UserInfoVO();
                info.setUserId(NumberUtils.parseLong(request.getParameter("userId")));
                info.setUserHeadUrl(userHeadUrl);
                info.setUserSex(
                        Utils.getString(request.getParameter("userSex")));
                info.setUserBirth(
                        DateUtils.parseDate(request.getParameter("userBirth")));
                info.setUserPhone(
                        Utils.getString(request.getParameter("userPhone")));
                info.setUserEmail(
                        Utils.getString(request.getParameter("userEmail")));
                info.setUserQQ(
                        Utils.getString(request.getParameter("userQQ")));
                return info;
            } else {
                throw new ServiceException("请求异常");
            }
        } catch (IOException e) {
            throw new ServiceException("无法获取上传的头像");
        }


    }
}