package com.ytempest.controller;

import com.ytempest.common.ResultUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.service.CookInfoService;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;
import com.ytempest.vo.PageVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import javax.annotation.Resource;

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

}
