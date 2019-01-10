package com.ytempest.service.impl;

import com.ytempest.common.LogUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.TopicInfoMapper;
import com.ytempest.service.TopicInfoService;
import com.ytempest.vo.BaseTopicInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service("TopicInfoService")
public class TopicInfoServiceImpl implements TopicInfoService {

    private static final String TAG = "TopicInfoServiceImpl";

    @Resource(name = "TopicInfoMapper")
    private TopicInfoMapper mapper;

    @Override
    public PageVO<TopicInfoVO> getTopicList(int pageNum, int pageSize) throws ServiceException, SQLException {
        // 3、获取PageVO数据模型所需要的相关参数
        // 获取用户的记录总数
        long total = mapper.countAll();
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
            throw new ServiceException(ServiceException.TOPIC_LIST_END, "已经到底");
        }

        // 4、封装PageVO数据
        PageVO<TopicInfoVO> pageVO = new PageVO<TopicInfoVO>(total, pageSize, pageNum,
                pageCount);
        pageVO.setList(mapper.selectTopicList((pageNum - 1) * pageSize, pageSize));

        return pageVO;
    }


    @Override
    public List<TopicCommentInfoVO> getCommentListById(long topicId) throws Exception {
        return mapper.selectCommentListById(String.valueOf(topicId));
    }

    @Override
    public List<TopicDetailCommentVO> getCommentInfo(Integer topicId, Integer commentId,
                                                     Integer replyToUser) throws Exception {
        return mapper.selectDetailComment(topicId, commentId, replyToUser);
    }

    @Override
    public void addTopic(BaseTopicInfoVO topic, HttpServletRequest request) throws ServiceException {

        LogUtils.e(TAG, "addTopic: topic=" + topic);

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data".
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iterator = multiRequest.getFileNames();
            int imageIdx = 0;
            while (iterator.hasNext()) {
                imageIdx++;
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iterator.next().toString());
                if (file != null) {
                    String filename = file.getOriginalFilename();
                    String newFileName = String.format("%s-%s.%s", imageIdx,
                            topic.getTopicId(), filename.substring(filename.lastIndexOf(".") + 1));
                    String imageDirPath = request.getSession().getServletContext().getRealPath("/topic");
                    File imageDir = new File(imageDirPath);
                    if (!imageDir.exists()) {
                        imageDir.mkdir();
                    }
                    File image = new File(imageDir, newFileName);

                    LogUtils.e(TAG, "addTopic: 第" + imageIdx + "张图片，名称：" + image);

                    //上传
                    try {
                        file.transferTo(image);
                    } catch (IOException e) {
                        throw new ServiceException("获取上传图片失败");
                    }
                }
            }
        }
    }
}