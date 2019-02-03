package com.ytempest.service.impl;

import com.ytempest.encrypt.DecryptUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.ActivityInfoMapper;
import com.ytempest.mapper.CollectionInfoMapper;
import com.ytempest.mapper.CookInfoMapper;
import com.ytempest.mapper.UserInfoMapper;
import com.ytempest.service.UserInfoService;
import com.ytempest.util.DateUtils;
import com.ytempest.util.FileUtils;
import com.ytempest.util.LogUtils;
import com.ytempest.util.NumberUtils;
import com.ytempest.encrypt.MD5Utils;
import com.ytempest.util.Utils;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.CollectionInfo;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.UserInfoVO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static final String TAG = "UserInfoServiceImpl";

    /*
     * 每一页显示的记录数
     */
    private final int PAGE_SIZE = 10;

    @Resource(name = "UserInfoMapper")
    private UserInfoMapper userMapper;

    @Resource(name = "CollectionInfoMapper")
    private CollectionInfoMapper collectMapper;

    @Resource(name = "CookInfoMapper")
    private CookInfoMapper cookMapper;

    @Resource(name = "ActivityInfoMapper")
    private ActivityInfoMapper actMapper;

    @Override
    public void addUser(UserInfoVO user) throws Exception {
        userMapper.insert(user);
    }

    @Override
    public void lockUser(String account) throws Exception {
        UserInfoVO vo = userMapper.selectByAccount(account);
        vo.setUserStatus(vo.getUserStatus() == 1 ? 0 : 1);
        userMapper.updateById(vo);
    }

    @Override
    public void updateUser(UserInfoVO model) throws Exception {
        userMapper.updateById(model);
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
        long total = userMapper.countAll();
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
        pageVO.setList(userMapper.selectAll((pageNum - 1) * PAGE_SIZE, PAGE_SIZE));

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
        long count = userMapper.countFuzzySearch(key);
        List<UserInfoVO> list = userMapper.fuzzySearchByAccount(key,
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
        return userMapper.selectByAccount(account);
    }

    /**********   API   **********/

    @Override
    public UserInfoVO login(String account, String password) throws ServiceException {

        String decrypt = DecryptUtils.decrypt(password.trim());
        String pwd = MD5Utils.encode(decrypt);

        try {
            UserInfoVO vo = userMapper.selectByAccount(account.trim());

            if (vo == null) {
                throw new ServiceException("该账号不存在");
            } else if (!pwd.equals(vo.getUserPwd())) {
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
            UserInfoVO oldInfo = userMapper.selectById(String.valueOf(newInfo.getUserId()));
            LogUtils.e(TAG, "updateBaseUserInfo: oldInfo = " + oldInfo);

            Utils.update(oldInfo, newInfo);
            userMapper.updateById(oldInfo);
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

    @Override
    public void updateUserPwd(Long userId, String oldPwd, String newPwd, String confirmPwd) throws ServiceException {
        try {
            UserInfoVO info = userMapper.selectById(String.valueOf(userId));
            String userPwd = info.getUserPwd();
            // 如果密码正确
            if (userPwd.equals(MD5Utils.deprecatedEncode(oldPwd))) {
                // 如果前后输入的密码不一致
                if (!newPwd.equals(confirmPwd)) {
                    throw new ServiceException("新密码不一致");
                } else {
                    info.setUserPwd(MD5Utils.deprecatedEncode(newPwd));
                    userMapper.updateById(info);
                }
            } else {
                throw new ServiceException("密码错误");
            }
        } catch (SQLException e) {
            throw new ServiceException("修改失败，请重试");
        }
    }

    @Override
    public boolean isCollect(Long userId, Long cookId) throws ServiceException {
        try {
            CollectionInfo info = collectMapper.selectBy(userId, cookId);
            if (info != null) {
                return true;
            }
        } catch (SQLException e) {
            throw new ServiceException("查询失败");
        }
        return false;
    }

    @Override
    public void collectCook(Long userId, Long cookId) throws ServiceException {
        CollectionInfo info = null;
        try {
            info = collectMapper.selectBy(userId, cookId);
            // 如果还没有收藏
            if (info == null) {
                info = new CollectionInfo(null, userId, cookId);
                collectMapper.insert(info);
            } else {
                // 如果已经收藏
                collectMapper.deleteById(info.getCollectId());
            }
        } catch (Exception e) {
            throw new ServiceException("操作失败，请重试");
        }
    }

    @Override
    public PageVO<CookBaseInfoVO> getCollectList(Long userId, Integer pageNum, Integer pageSize) throws ServiceException {
        try {
            long total = cookMapper.countCollectList(userId);

            int pageCount = Utils.getPageCount(pageNum, pageSize, total);

            // 封装PageVO数据
            PageVO<CookBaseInfoVO> pageVO = new PageVO<>(total, pageSize, pageNum,
                    pageCount);
            pageVO.setList(cookMapper.selectCollectList(userId,
                    (pageNum - 1) * pageSize, pageSize));
            return pageVO;
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }


    @Override
    public PageVO<ActivityInfoVO> getActivityList(Long userId, Integer pageNum, Integer pageSize) throws ServiceException {
        try {
            long total = actMapper.countActivityList(userId);

            int pageCount = Utils.getPageCount(pageNum, pageSize, total);

            // 封装PageVO数据
            PageVO<ActivityInfoVO> pageVO = new PageVO<>(total, pageSize, pageNum,
                    pageCount);
            pageVO.setList(actMapper.selectActivityList(userId,
                    (pageNum - 1) * pageSize, pageSize));
            return pageVO;
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }

}