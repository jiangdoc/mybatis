package org.apache.ibatis.example.dao;

import org.apache.ibatis.example.entity.Blog;

import java.util.List;

/**
 * @author jiangwenjie
 * @date 2021/5/30
 */
public interface BlogMapper {

  Blog selectBlog(Long id);

  List<Blog> selectAll();
}
