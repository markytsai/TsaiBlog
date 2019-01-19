package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void test() {
        Integer blogId = 1;
        Blog blog = blogMapper.selectBlogByBlogId(blogId);
        System.out.println(blog.getBlogTitle());

    }

}