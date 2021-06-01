package org.apache.ibatis.example.dao;

import org.apache.ibatis.example.entity.Blog;

/**
 * @author jiangwenjie
 * @date 2021/5/30
 */
public interface BlogMapper {

  Blog selectBlog(Long id);
}
