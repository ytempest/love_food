package com.ytempest.controller;

import com.ytempest.exception.ServiceException;
import com.ytempest.service.ActivityInfoService;
import com.ytempest.service.PartakeActivityService;
import com.ytempest.util.ResultUtils;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.PartakeActivityVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private static final String TAG = "ActivityController";

    @Resource(name = "ActivityInfoService")
    private ActivityInfoService actService;

    @Resource(name = "PartakeActivityService")
    private PartakeActivityService parService;

    /**
     * 获取活动列表
     */
    @GetMapping("/list")
    public BaseResult getCookList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {

        BaseResult result = ResultUtils.result();

        try {
            PageVO<ActivityInfoVO> list = actService.getActivityList(pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", list);
        } catch (ServiceException e) {
            if (e.getErrorCode() == ServiceException.ACTIVITYLIST_END) {
                ResultUtils.setError(result, "已经到底", ResultUtils.NullList);
            } else {
                ResultUtils.setError(result, e.getMessage(), ResultUtils.NullList);
            }
        }

        return result;
    }

    /**
     * 获取活动的详细信息
     */
    @GetMapping("/info")
    public BaseResult getActivityInfo(@RequestParam("actId") Long actId) {

        BaseResult result = ResultUtils.result();
        try {
            ActivityInfoVO activityInfo = actService.getActivityInfo(actId);
            ResultUtils.setSuccess(result, "获取成功", activityInfo);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullList);
        }

        return result;
    }

    /**
     * 判断用户是否已经参加了指定的活动
     */
    @PostMapping("/isPartake")
    public BaseResult isPartake(@RequestParam("userId") Long userId,
                                @RequestParam("actId") Long actId) {

        BaseResult result = ResultUtils.result();
        try {
            PartakeActivityVO info = parService.isPartake(userId, actId);
            ResultUtils.setSuccess(result, "查询成功", info != null);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullList);
        }

        return result;
    }

    /**
     * 获取该活动所有的参赛菜谱
     */
    @PostMapping("/getPartakeCookList")
    public BaseResult getPartakeCookList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam("actId") Long actId) {

        BaseResult result = ResultUtils.result();
        try {
            PageVO<CookBaseInfoVO> list = actService.getPartakeCookList(actId, pageNum, pageSize);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        return result;
    }

}
