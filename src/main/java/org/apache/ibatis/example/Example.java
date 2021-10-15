package org.apache.ibatis.example;

import org.apache.ibatis.example.dao.BlogMapper;
import org.apache.ibatis.example.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangwenjie
 * @date 2021/5/30
 */
public class Example {

  public static void main(String[] args) throws IOException {
    example1();
  }

  private static void example1() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    /**
     * 1.加载配置文件为Configuration对象
     * 2.通过Configuration创建SqlSessionFactory
     */
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    // 这里会获取执行器
    /**
     * 根据配置创建一个Executor
     * 然后new一个DefaultSqlSession,参数就是上面的Executor
     */
    try (SqlSession session = sqlSessionFactory.openSession()) {
      /**
       * @see org.apache.ibatis.binding.MapperProxy#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
       */
      BlogMapper mapper = session.getMapper(BlogMapper.class);
      Blog blog = mapper.selectBlog(1L);

      if (Objects.nonNull(blog)) {
        System.out.println(String.format("blog:{id:%s,title:%s}", blog.getId(), blog.getTitle()));
      }
      List<Blog> blogs = mapper.selectAll();
      for (Blog blog1 : blogs){
        System.out.println(String.format("blog:{id:%s,title:%s}", blog1.getId(), blog1.getTitle()));
      }

      //System.in.read();
    }
  }
}
