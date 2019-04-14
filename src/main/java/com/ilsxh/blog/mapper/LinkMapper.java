package com.ilsxh.blog.mapper;

import com.ilsxh.blog.entity.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LinkMapper {

    List<Link> getLinks();

    void updateLink(Link link);

    void addLink(Link link);

    void deleteLinkById(int linkId);

    Long getTotalLinkNum();

    List<Link> selectLinkList();
}
