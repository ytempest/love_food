package com.ytempest.controller;

import com.ytempest.exception.ServiceException;
import com.ytempest.service.ActivityInfoService;
import com.ytempest.service.CookInfoService;
import com.ytempest.service.PartakeActivityService;
import com.ytempest.util.NumberUtils;
import com.ytempest.util.ResultUtils;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.PartakeActivityVO;
import com.ytempest.vo.UserActivityPrizeVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private static final String TAG = "ActivityController";

    @Resource(name = "ActivityInfoService")
    private ActivityInfoService actService;

    @Resource(name = "PartakeActivityService")
    private PartakeActivityService parService;

    @Resource(name = "CookInfoService")
    private CookInfoService cookService;

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
            if (e.getErrorCode() == ServiceException.ACTIVITY_LIST_END) {
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
    @GetMapping("/getPartakeCookList")
    public BaseResult getPartakeCookList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam("actId") Long actId) {

        BaseResult result = ResultUtils.result();
        try {
            PageVO<CookBaseInfoVO> list = cookService.getPartakeCookList(actId, pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", list);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 获取该活动所有获奖名单
     */
    @GetMapping("/getAwardList")
    public BaseResult getAwardList(@RequestParam("actId") Long actId) {
        BaseResult result = ResultUtils.result();
        try {
            List<UserActivityPrizeVO> awardList = actService.getAwardList(actId);
            Map<String, List<UserActivityPrizeVO>> map = new LinkedHashMap<>();
            for (UserActivityPrizeVO prize : awardList) {
                String prizeName = prize.getPrizeName();
                List<UserActivityPrizeVO> list = map.get(prizeName);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(prizeName, list);
                }
                list.add(prize);
            }
            ResultUtils.setSuccess(result, "获取成功", map);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 参加指定的活动
     */
    @RequestMapping("/partake")
    public BaseResult partakeActivity(HttpServletRequest request) {
        BaseResult result = ResultUtils.result();
        try {
            CookBaseInfoVO cook = cookService.addCook(request);
            PartakeActivityVO partake = new PartakeActivityVO();
            partake.setPartId(null);
            partake.setActId(NumberUtils.parseLong(request.getParameter("actId")));
            partake.setCookId(cook.getCookId());
            partake.setUserId(cook.getCookUserId());
            parService.partakeActivity(partake);
            ResultUtils.setSuccess(result, "参加成功", ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }
}
