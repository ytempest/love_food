package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.AccessoriesMapper;
import com.ytempest.mapper.CookInfoMapper;
import com.ytempest.mapper.MainmaterialsMapper;
import com.ytempest.mapper.ProceduresMapper;
import com.ytempest.service.CookInfoService;
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
                                              Integer pageNum, Integer pageSize) throws ServiceException, SQLException {
        // 获取用户的记录总数
        long total = cookMapper.countCookList(cookGroup, cookType);
        // 计算总页面数
        int pageCount = (int) (total % pageSize == 0
                ? total / pageSize
                : total / pageSize + 1);
        // 判断输入的页码是否超过数据的页码范围
        if (pageNum < 1) {
            throw new IllegalArgumentException(
                    "page number is out of page count");
        }
        if (pageNum > pageCount) {
            throw new ServiceException(ServiceException.COOK_LIST_END, "已经到底");
        }

        // 4、封装PageVO数据
        PageVO<CookBaseInfoVO> pageVO = new PageVO<>(total, pageSize, pageNum,
                pageCount);
        pageVO.setList(cookMapper.selectCookList(cookGroup, cookType,
                (pageNum - 1) * pageSize, pageSize));

        return pageVO;
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
    public void addCook(HttpServletRequest request) throws ServiceException {
        try {
            CookBaseInfoVO cookInfo = obtainCook(request);
            cookMapper.insert(cookInfo);

            mainMapper.insertList(
                    obtainMainmaterials(request, cookInfo.getCookId()));

            accMapper.insertList(
                    obtainAccessories(request, cookInfo.getCookId()));

            proceMapper.insertList(
                    obtainProcedures(request, cookInfo.getCookId()));

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        } catch (SQLException e) {
            throw new ServiceException("插入失败");
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
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new ServiceException("无法获取上传的菜谱成品图片");
        }
        return list;
    }

    private CookBaseInfoVO obtainCook(HttpServletRequest request) throws ServiceException {
        CookBaseInfoVO vo = new CookBaseInfoVO();
        try {
            vo.setCookId(null);
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
                }
            }
        } catch (IOException e) {
            throw new ServiceException("无法获取上传的菜谱成品图片");
        }
        return vo;
    }
}
