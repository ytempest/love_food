package com.ytempest.controller;

import com.ytempest.util.LogUtils;
import com.ytempest.util.ResultUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.service.CommentInfoService;
import com.ytempest.service.ReplyInfoService;
import com.ytempest.service.TopicInfoService;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.BaseTopicInfoVO;
import com.ytempest.vo.CommentInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.ReplyInfoVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private static final String TAG = "TopicController";

    @Resource(name = "TopicInfoService")
    private TopicInfoService topicService;

    @Resource(name = "CommentInfoService")
    private CommentInfoService commentService;

    @Resource(name = "ReplyInfoService")
    private ReplyInfoService replyService;

    /**
     * 获取话题列表
     */
    @GetMapping("/list")
    public BaseResult getTopicList(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {

        BaseResult result = ResultUtils.result();
        try {
            PageVO<TopicInfoVO> topicList = topicService.getTopicList(pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", topicList);
        } catch (ServiceException e) {
            if (e.getErrorCode() == ServiceException.TOPIC_LIST_END) {
                ResultUtils.setError(result, "已经到底", ResultUtils.NullList);
            } else {
                ResultUtils.setError(result, e.getMessage(), ResultUtils.NullList);
            }
        }
        return result;
    }


    /**
     * 获取话题的详细信息
     */
    @GetMapping("/info")
    public BaseResult getTopicInfo(@RequestParam("topicId") Integer topicId) {
        BaseResult result = ResultUtils.result();
        try {
            List<TopicCommentInfoVO> commentList = topicService.getCommentListById(topicId);
            ResultUtils.setSuccess(result, "获取成功", commentList);
        } catch (Exception e) {
            ResultUtils.setSuccess(result, "获取成功", ResultUtils.NullList);
        }
        return result;
    }

    /**
     * 获取评论的详细信息
     */
    @GetMapping("/getCommentInfo")
    public BaseResult getCommentInfo(
            @RequestParam("topicId") Integer topicId,
            @RequestParam("commentId") Integer commentId,
            @RequestParam("replyToUser") Integer replyToUser) {
        BaseResult result = ResultUtils.result();
        try {
            List<TopicDetailCommentVO> commentList = topicService.getCommentInfo(topicId, commentId, replyToUser);
            ResultUtils.setSuccess(result, "获取成功", commentList);
        } catch (Exception e) {
            ResultUtils.setSuccess(result, "获取成功", ResultUtils.NullList);
        }
        return result;
    }

    /**
     * 添加评论
     */
    @PostMapping("/addComment")
    public BaseResult addComment(
            @RequestParam("topicId") Long topicId,
            @RequestParam("content") String content,
            @RequestParam("time") Long time,
            @RequestParam("fromUser") Long fromUser,
            @RequestParam("toUser") Long toUser) {
        BaseResult result = ResultUtils.result();

        CommentInfoVO comment = new CommentInfoVO(
                null, topicId, content, new Date(time), fromUser, toUser);

        try {
            commentService.addComment(comment);
            ResultUtils.setSuccess(result, "评论成功", ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }

        return result;
    }


    /**
     * 添加回复
     */
    @PostMapping("/addReply")
    public BaseResult addReply(
            @RequestParam("commentId") Long commentId,
            @RequestParam("content") String content,
            @RequestParam("time") Long time,
            @RequestParam("fromUser") Long fromUser,
            @RequestParam("toUser") Long toUser) {
        BaseResult result = ResultUtils.result();

        ReplyInfoVO reply = new ReplyInfoVO(
                null, commentId, content, new Date(time), fromUser, toUser);
        try {
            replyService.addReply(reply);
            ResultUtils.setSuccess(result, "回复成功", ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, "回复失败", ResultUtils.NullObj);
        }

        return result;
    }

    /**
     * 添加话题
     */
    @RequestMapping(value = "/addTopic")
    public BaseResult addTopic(HttpServletRequest request) {
        BaseResult result = ResultUtils.result();
        try {
            BaseTopicInfoVO topic = new BaseTopicInfoVO();
            topic.setTopicId(null);
            topic.setUserId(Long.valueOf(request.getParameter("userId")));
            topic.setTopicTitle(request.getParameter("topicTitle"));
            topic.setTopicContent(request.getParameter("topicContent"));
            topic.setTopicPublishTime(
                    new Date(Long.valueOf(request.getParameter("publishTime"))));

            topicService.addTopic(topic, request);
            ResultUtils.setSuccess(result, "发布成功", ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, "发布失败", ResultUtils.NullObj);
        }
        return result;
    }
}
