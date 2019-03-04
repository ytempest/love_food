package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.AccessoriesMapper;
import com.ytempest.mapper.CookInfoMapper;
import com.ytempest.mapper.MainmaterialsMapper;
import com.ytempest.mapper.ProceduresMapper;
import com.ytempest.service.CookInfoService;
import com.ytempest.util.CollectionUtils;
import com.ytempest.util.DateUtils;
import com.ytempest.util.FileUtils;
import com.ytempest.util.LogUtils;
import com.ytempest.util.NumberUtils;
import com.ytempest.util.TextUtils;
import com.ytempest.util.Utils;
import com.ytempest.vo.AccessoriesVO;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;
import com.ytempest.vo.MainmaterialsVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.ProceduresVO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ytempest
 * Description：
 */
@Service("CookInfoService")
public class CookInfoServiceImpl implements CookInfoService {

    private static final String TAG = "CookInfoServiceImpl";

    @Resource(name = "CookInfoMapper")
    private CookInfoMapper cookMapper;

    @Resource(name = "MainmaterialsMapper")
    private MainmaterialsMapper mainMapper;

    @Resource(name = "AccessoriesMapper")
    private AccessoriesMapper accMapper;

    @Resource(name = "ProceduresMapper")
    private ProceduresMapper proceMapper;

    @Override
    public PageVO<CookBaseInfoVO> getCookList(String cookGroup, String cookType,
                                              Integer pageNum, Integer pageSize) throws ServiceException {
        try {
            // 获取记录总数
            long total = cookMapper.countCookList(cookGroup, cookType);
            if (total == 0) {
                throw new ServiceException("亲，该分类暂无菜谱");
            }

            // 计算总页面数
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

            // 4、封装PageVO数据
            PageVO<CookBaseInfoVO> pageVO = new PageVO<>(total, pageSize, pageNum,
                    pageCount);
            pageVO.setList(cookMapper.selectCookList(cookGroup, cookType,
                    (pageNum - 1) * pageSize, pageSize));

            return pageVO;
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }

    @Override
    public CookDetailInfoVO getCookInfo(Long cookId) throws ServiceException {
        try {
            return cookMapper.selectCook(cookId);
        } catch (SQLException e) {
            throw new ServiceException("查询失败");
        }
    }

    @Override
    public CookBaseInfoVO addCook(HttpServletRequest request) throws ServiceException {
        try {
            CookBaseInfoVO cookInfo = obtainCook(request);
            cookMapper.insert(cookInfo);

            List<MainmaterialsVO> mainList = obtainMainmaterials(request, cookInfo.getCookId());
            List<AccessoriesVO> accList = obtainAccessories(request, cookInfo.getCookId());
            List<ProceduresVO> proList = obtainProcedures(request, cookInfo.getCookId());

            mainMapper.insertList(mainList);
            accMapper.insertList(accList);
            proceMapper.insertList(proList);
            return cookInfo;
        } catch (SQLException e) {
            throw new ServiceException("插入失败");
        }
    }

    @Override
    public void deleteCook(Long cookId) throws ServiceException {
        if (cookId != null) {
            try {
                CookBaseInfoVO cook = cookMapper.selectById(String.valueOf(cookId));
                List<ProceduresVO> proceList = proceMapper.selectList(cookId);

                mainMapper.delete(cookId);
                accMapper.delete(cookId);
                proceMapper.delete(cookId);
                cookMapper.deleteCook(cookId);
                deleteCookImage(cook, proceList);
            } catch (SQLException e) {
                throw new ServiceException("删除失败");
            }
        } else {
            throw new ServiceException("菜谱Id不能为空");
        }
    }

    private void deleteCookImage(CookBaseInfoVO cook, List<ProceduresVO> proceList) {
        if (cook != null) {
            FileUtils.deleteImage(cook.getCookImageUrl());
        }
        if (!CollectionUtils.isEmpty(proceList)) {
            for (ProceduresVO proce : proceList) {
                FileUtils.deleteImage(proce.getProceImageUrl());
            }
        }
    }

    @Override
    public CookDetailInfoVO updateCook(HttpServletRequest request) throws ServiceException {
        try {
            CookBaseInfoVO newCookInfo = obtainCook(request);
            Long cookId = newCookInfo.getCookId();
            CookDetailInfoVO oldCookInfo = cookMapper.selectCook(cookId);
            List<ProceduresVO> oldProceList = proceMapper.selectList(cookId);
            // 更新旧菜谱的基本数据
            cookMapper.updateById(newCookInfo);

            // 先删除旧数据
            mainMapper.delete(cookId);
            accMapper.delete(cookId);
            proceMapper.delete(cookId);
            // 再添加新数据
            mainMapper.insertList(obtainMainmaterials(request, cookId));
            accMapper.insertList(obtainAccessories(request, cookId));
            proceMapper.insertList(obtainProcedures(request, cookId));

            // 删除旧菜谱的成品图片和步骤的图片
            deleteCookImage(oldCookInfo, oldProceList);
            return cookMapper.selectCook(cookId);
        } catch (SQLException e) {
            throw new ServiceException("修改失败");
        }
    }

    private List<MainmaterialsVO> obtainMainmaterials(HttpServletRequest request, long cookId) {
        List<MainmaterialsVO> list = new ArrayList<>();
        String mainName;
        String mainAmount;
        for (int i = 1; ; i++) {
            mainName = request.getParameter("mainName" + i);
            mainAmount = request.getParameter("mainAmount" + i);
            if (!TextUtils.isEmpty(mainName) && !TextUtils.isEmpty(mainAmount)) {
                MainmaterialsVO vo = new MainmaterialsVO();
                vo.setMainId(null);
                vo.setCookId(cookId);
                vo.setMainName(mainName);
                vo.setMainAmount(mainAmount);
                list.add(vo);
            } else {
                break;
            }
        }

        return list;
    }

    private List<AccessoriesVO> obtainAccessories(HttpServletRequest request, long cookId) {
        List<AccessoriesVO> list = new ArrayList<>();
        String accName;
        String accAmount;
        for (int i = 1; ; i++) {
            accName = request.getParameter("accName" + i);
            accAmount = request.getParameter("accAmount" + i);
            if (!TextUtils.isEmpty(accName) && !TextUtils.isEmpty(accAmount)) {
                AccessoriesVO vo = new AccessoriesVO();
                vo.setAccId(null);
                vo.setCookId(cookId);
                vo.setAccName(accName);
                vo.setAccAmount(accAmount);
                list.add(vo);
            } else {
                break;
            }
        }

        return list;
    }

    private List<ProceduresVO> obtainProcedures(HttpServletRequest request, long cookId) throws ServiceException {
        List<ProceduresVO> list = new ArrayList<>();
        try {
            if (Utils.isHaveMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> imageMap = multiRequest.getFileMap();
                String proceNo = null;
                String proceDesc = null;
                MultipartFile proceImage = null;
                for (int i = 1; ; i++) {
                    proceNo = request.getParameter("proceNo" + i);
                    proceDesc = request.getParameter("proceDesc" + i);
                    proceImage = imageMap.get("proceImage" + i);
                    if (proceImage != null && !TextUtils.isEmpty(proceNo)
                            && !TextUtils.isEmpty(proceDesc)) {
                        String imageName = proceImage.getOriginalFilename();
                        String newImageName = FileUtils.generateImageName(imageName);
                        File image = new File(FileUtils.getCookProcedureImageDir(), newImageName);
                        //上传
                        proceImage.transferTo(image);

                        ProceduresVO vo = new ProceduresVO();
                        vo.setProceId(null);
                        vo.setCookId(cookId);
                        vo.setProceNo(NumberUtils.parseInteger(proceNo));
                        vo.setProceDesc(proceDesc);
                        vo.setProceImageUrl(FileUtils.generateCookProcedureImageUrl(newImageName));
                        list.add(vo);

                    } else if (proceImage == null &&
                            TextUtils.isEmpty(proceNo) && TextUtils.isEmpty(proceDesc)) {
                        break;
                    } else {
                        throw new ServiceException("步骤的参数(proceNo、proceDesc、proceImage)不能为空");
                    }
                }
            }
        } catch (
                IOException e) {
            LogUtils.e(TAG, "obtainProcedures: " + e.getCause());
            throw new ServiceException("无法获取上传的菜谱成品图片");
        }
        return list;
    }

    private CookBaseInfoVO obtainCook(HttpServletRequest request) throws ServiceException {
        CookBaseInfoVO vo = new CookBaseInfoVO();
        try {
            vo.setCookId(NumberUtils.parseLong(request.getParameter("cookId")));
            vo.setCookGroup(request.getParameter("cookGroup"));
            vo.setCookType(request.getParameter("cookType"));
            vo.setCookUserId(NumberUtils.parseLong(request.getParameter("cookUserId")));
            vo.setCookTitle(request.getParameter("cookTitle"));
            vo.setCookDesc(request.getParameter("cookDesc"));
            vo.setCookPublishTime(DateUtils.parseDate(request.getParameter("cookPublishTime")));

            if (Utils.isHaveMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
                MultipartFile file = fileMap.get("cookImage");
                if (file != null) {
                    String imageName = file.getOriginalFilename();
                    String newImageName = FileUtils.generateImageName(imageName);
                    File image = new File(FileUtils.getCookImageDir(), newImageName);

                    //上传
                    file.transferTo(image);
                    vo.setCookImageUrl(FileUtils.generateCookImageUrl(newImageName));
                } else {
                    LogUtils.e(TAG, "obtainCook: 无法获取菜谱的成品图片");
                    throw new ServiceException("无法获取菜谱的成品图片");
                }
            }
        } catch (IOException e) {
            throw new ServiceException("无法获取上传的菜谱成品图片");
        }
        return vo;
    }

    @Override
    public PageVO<CookBaseInfoVO> getPartakeCookList(Long actId, Integer pageNum, Integer pageSize) throws ServiceException {
        try {
            long total = cookMapper.countPartakeCookList(actId);
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

            // 封装PageVO数据
            PageVO<CookBaseInfoVO> pageVO = new PageVO<>(total, pageSize,
                    pageNum, pageCount);
            pageVO.setList(cookMapper.selectPartakeCookList(actId, (pageNum - 1) * pageSize, pageSize));

            return pageVO;
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }

    @Override
    public PageVO<CookBaseInfoVO> getUserCookList(Long userId, Integer pageNum, Integer pageSize) throws ServiceException {

        try {
            long total = cookMapper.countUserCookList(userId);
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

            // 封装PageVO数据
            PageVO<CookBaseInfoVO> pageVO = new PageVO<>(total, pageSize,
                    pageNum, pageCount);
            pageVO.setList(cookMapper.selectUserCookList(userId, (pageNum - 1) * pageSize, pageSize));

            return pageVO;
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }
}
