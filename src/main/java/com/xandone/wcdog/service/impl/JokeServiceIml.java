package com.xandone.wcdog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.wcdog.mapper.JokeMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/1/15 22:30
 * description：
 */
@Service
public class JokeServiceIml implements JokeService {
    @Autowired
    JokeMapper jokeMapper;
    @Autowired
    UserMapper userMapper;


    public JokeBean addJoke(String title, String jokeUserId, String content, String contentHtml) throws Exception {
        JokeBean jokeBean = new JokeBean();

        jokeBean.setJokeId(IDUtils.RandomId());
        jokeBean.setJokeUserId(jokeUserId);
        jokeBean.setTitle(title);
        jokeBean.setContent(content);
        jokeBean.setContentHtml(contentHtml);
        jokeBean.setPostTime(new Date());
        jokeBean.setArticleCommentCount(0);
        jokeBean.setArticleLikeCount(0);
        jokeMapper.addJoke(jokeBean);

        UserBean userBean = userMapper.getUserById(jokeUserId);
        jokeBean.setJokeUserNick(userBean.getNickname());
        jokeBean.setJokeUserIcon(userBean.getUserIcon());
        return jokeBean;
    }


    @Override
    public BaseListResult getAllJoke(Integer page, Integer row) {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<JokeBean> list = jokeMapper.getJokeList();
        int total = (int) new PageInfo<>(list).getTotal();

        for (JokeBean bean : list) {
            UserBean user = userMapper.getUserById(bean.getJokeUserId());
            if (user != null) {
                bean.setJokeUserNick(user.getNickname());
                bean.setJokeUserIcon(user.getUserIcon());
            }
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public void deleteJokeById(String jokeId) throws Exception {
        jokeMapper.deleteJokeById(jokeId);
    }

    @Override
    public void deleteJokeByList(List<String> jokeIds) throws Exception {
        jokeMapper.deleteJokeByList(jokeIds);
    }

    @Override
    public void addComment(CommentBean bean) throws Exception {
        jokeMapper.addComment(bean);
    }

    @Override
    public BaseListResult getAllJokeCommentById(Integer page, Integer row, String jokeId) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<CommentBean> list = jokeMapper.getJokeCommentById(jokeId);
        int total = (int) new PageInfo<>(list).getTotal();

        for (CommentBean bean : list) {
            UserBean user = userMapper.getUserById(bean.getCommentUserId());
            if (user != null) {
                bean.setCommentNick(user.getNickname());
                bean.setCommentIcon(user.getUserIcon());
            }
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public void deleteCommentByList(List<String> commentsId) throws Exception {
        jokeMapper.deleteCommentByList(commentsId);
    }


}