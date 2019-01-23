package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkMapperTest {

    @Autowired
    private LinkMapper linkMapper;

    @Test
    public void testLinkMapper() {
        Link link = new Link();
        link.setLinkLogo("222");
        link.setLinkUrl("aaaa");
        link.setLinkName("caizhenya");
        link.setLinkUsername("aaaa");


        linkMapper.addLink(link);
        System.out.println("link Id = " + link.getLinkId());
    }

}