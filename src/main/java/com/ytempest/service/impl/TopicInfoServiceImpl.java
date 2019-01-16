package com.ytempest.service.impl;

import com.ytempest.common.Configure;
import com.ytempest.common.LogUtils;
import com.ytempest.common.Utils;
import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.TopicImageInfoMapper;
import com.ytempest.mapper.TopicInfoMapper;
import com.ytempest.service.TopicInfoService;
import com.ytempest.vo.BaseTopicInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicImageVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service("TopicInfoService")
public class TopicInfoServiceImpl implements TopicInfoService {

    private static final String TAG = "TopicInfoServiceImpl";

    @Resource(name = "TopicInfoMapper")
    private TopicInfoMapper topicMapper;

    @Resource(name = "TopicImageInfoMapper")
    private TopicImageInfoMapper imageMapper;

    public static void main(String[] arg) {
        String filename = "1234.jpg";
        String s = filename.substring(filename.lastIndexOf("."));
        LogUtils.e(TAG, "main: " + s);

    }

    @Override
    public PageVO<TopicInfoVO> getTopicList(int pageNum, int pageSize) throws ServiceException, SQLException {
        // 获取用户的记录总数
        long total = topicMapper.countAll();
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
        pageVO.setList(topicMapper.selectTopicList((pageNum - 1) * pageSize, pageSize));

        return pageVO;
    }


    @Override
    public List<TopicCommentInfoVO> getCommentListById(long topicId) throws Exception {
        return topicMapper.selectCommentListById(String.valueOf(topicId));
    }

    @Override
    public List<TopicDetailCommentVO> getCommentInfo(Integer topicId, Integer commentId,
                                                     Integer replyToUser) throws Exception {
        return topicMapper.selectDetailComment(topicId, commentId, replyToUser);
    }

    @Override
    public void addTopic(BaseTopicInfoVO topic, HttpServletRequest request) throws ServiceException {

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        try {
            topicMapper.insert(topic);
            LogUtils.e(TAG, "addTopic: topicId=" + topic.getTopicId());
        } catch (SQLException e) {
            throw new ServiceException("插入失败");
        }

        //检查form中是否有enctype="multipart/form-data".
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iterator = multiRequest.getFileNames();

            List<TopicImageVO> imageList = new ArrayList<>();
            while (iterator.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iterator.next().toString());
                if (file != null) {
                    String imageName = file.getOriginalFilename();
                    String newImageName = generateImageName(imageName);
                    File image = new File(Utils.getTopicImageDir(request), newImageName);

                    //上传
                    try {
                        file.transferTo(image);
                        TopicImageVO imageVO = new TopicImageVO();
                        imageVO.setImageId(null);
                        imageVO.setTopicId(topic.getTopicId());
                        imageVO.setImageUrl(String.format("/%s/%s", Configure.TOPIC_IMAGE_DIR, newImageName));
                        imageList.add(imageVO);
                    } catch (IOException e) {
                        throw new ServiceException("获取上传图片失败");
                    }
                }
            }

            try {
                imageMapper.insertList(imageList);
            } catch (SQLException e) {
                throw new ServiceException("插入失败");
            }
        }
    }


    private String generateImageName(String filename) {
        return UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
    }
}