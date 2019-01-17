package com.ytempest.controller;

import com.ytempest.util.NumberUtils;
import com.ytempest.util.DateUtils;
import com.ytempest.util.ResultUtils;
import com.ytempest.util.FileUtils;
import com.ytempest.util.TextUtils;
import com.ytempest.util.Utils;
import com.ytempest.exception.ServiceException;
import com.ytempest.service.CookInfoService;
import com.ytempest.vo.AccessoriesVO;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;
import com.ytempest.vo.MainmaterialsVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.ProceduresVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/cook")
public class CookController {
    private static final String TAG = "CookController";

    @Resource(name = "CookInfoService")
    private CookInfoService service;

    /**
     * 获取菜谱列表
     */
    @GetMapping("/list")
    public BaseResult getCookList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("cookGroup") String cookGroup,
                                  @RequestParam("cookType") String cookType) {

        BaseResult result = ResultUtils.result();

        try {
            PageVO<CookBaseInfoVO> cookList = service.getCookList(cookGroup, cookType, pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", cookList);
        } catch (ServiceException e) {
            if (e.getErrorCode() == ServiceException.TOPIC_LIST_END) {
                ResultUtils.setError(result, "已经到底", ResultUtils.NullList);
            } else {
                ResultUtils.setError(result, "获取失败", ResultUtils.NullList);
            }
        } catch (SQLException e) {
            ResultUtils.setError(result, "获取失败", ResultUtils.NullList);
        }

        return result;
    }


    /**
     * 获取菜谱列表
     */
    @GetMapping("/info")
    public BaseResult getCookInfo(@RequestParam("cookId") Long cookId) {
        BaseResult result = ResultUtils.result();
        try {
            CookDetailInfoVO info = service.getCookInfo(cookId);
            ResultUtils.setSuccess(result, "获取成功", info);
        } catch (ServiceException e) {
            ResultUtils.setError(result, "获取失败", ResultUtils.NullObj);
        }
        return result;
    }


    /**
     * 添加菜谱
     */
    @RequestMapping(value = "/addCook")
    public BaseResult addCook(HttpServletRequest request) {
        BaseResult result = ResultUtils.result();
        try {
            CookBaseInfoVO cookInfo = obtainCook(request);

            List<MainmaterialsVO> mainmaterialsList = obtainMainmaterials(request);

            List<AccessoriesVO> accessoriesList = obtainAccessories(request);

            List<ProceduresVO> proceduresList = obtainProcedures(request);

            ResultUtils.setSuccess(result, "添加成功", proceduresList);
        } catch (IOException e) {
            ResultUtils.setError(result, "添加失败", ResultUtils.NullObj);
        }

        return result;
    }

    private List<MainmaterialsVO> obtainMainmaterials(HttpServletRequest request) {
        List<MainmaterialsVO> list = new ArrayList<>();
        String mainName;
        String mainAmount;
        for (int i = 1; ; i++) {
            mainName = request.getParameter("mainName" + i);
            mainAmount = request.getParameter("mainAmount" + i);
            if (!TextUtils.isEmpty(mainName) && !TextUtils.isEmpty(mainAmount)) {
                MainmaterialsVO vo = new MainmaterialsVO();
                vo.setMainId(null);
                vo.setCookId(null);
                vo.setMainName(mainName);
                vo.setMainAmount(mainAmount);
                list.add(vo);
            } else {
                break;
            }
        }

        return list;
    }

    private List<AccessoriesVO> obtainAccessories(HttpServletRequest request) {
        List<AccessoriesVO> list = new ArrayList<>();
        String accName;
        String accAmount;
        for (int i = 1; ; i++) {
            accName = request.getParameter("accName" + i);
            accAmount = request.getParameter("accAmount" + i);
            if (!TextUtils.isEmpty(accName) && !TextUtils.isEmpty(accAmount)) {
                AccessoriesVO vo = new AccessoriesVO();
                vo.setAccId(null);
                vo.setCookId(null);
                vo.setAccName(accName);
                vo.setAccAmount(accAmount);
                list.add(vo);
            } else {
                break;
            }
        }

        return list;
    }

    private List<ProceduresVO> obtainProcedures(HttpServletRequest request) throws IOException {
        List<ProceduresVO> list = new ArrayList<>();
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
                    vo.setCookId(null);
                    vo.setProceNo(NumberUtils.parseInteger(proceNo));
                    vo.setProceDesc(proceDesc);
                    vo.setProceImageUrl(FileUtils.generateCookProcedureImageUrl(newImageName));
                    list.add(vo);
                } else {
                    break;
                }
            }
        }
        return list;
    }

    private CookBaseInfoVO obtainCook(HttpServletRequest request) throws IOException {
        CookBaseInfoVO vo = new CookBaseInfoVO();
        vo.setCookId(null);
        vo.setCookGroup(request.getParameter("cookGroup"));
        vo.setCookType(request.getParameter("cookType"));
        vo.setCookImageUrl(request.getParameter("cookImageUrl"));
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
                vo.setCookImageUrl(FileUtils.generateCookImageUrl(imageName));
            }
        }
        return vo;
    }
}
