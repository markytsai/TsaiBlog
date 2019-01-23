package com.ilsxh.blog.service.admin;

import com.ilsxh.blog.entity.Link;
import com.ilsxh.blog.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    private LinkMapper linkMapper;

    public List<Link> getLinks() {
        return linkMapper.getLinks();
    }

    public void updateLink(Link link) {
        linkMapper.updateLink(link);
    }

    public void addLink(Link link) {
        linkMapper.addLink(link);
    }

    public void deleteLinkById(int linkId) {
        linkMapper.deleteLinkById(linkId);
    }
}
