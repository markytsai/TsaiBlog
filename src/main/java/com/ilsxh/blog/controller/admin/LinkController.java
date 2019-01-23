package com.ilsxh.blog.controller.admin;

import com.ilsxh.blog.entity.Link;
import com.ilsxh.blog.service.admin.LinkService;
import com.ilsxh.blog.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping(value = "/link")
    public String links(Model model) {
        List<Link> linkList = linkService.getLinks();
        model.addAttribute("links", linkList);
        return "admin/links";
    }


    @PostMapping(value = "/link/save")
    @ResponseBody
    public Response addLink(@RequestParam(name = "title", required = true) String title,
                            @RequestParam(name = "url", required = true) String url,
                            @RequestParam(name = "logo", required = false) String logo,
                            @RequestParam(name = "linkId", required = false) Integer linkId) {
        try {
            Link link = new Link();
            link.setLinkName(title);
            link.setLinkUrl(url);
            link.setLinkLogo(logo);
            if (null != linkId) {
                link.setLinkId(linkId);
                linkService.updateLink(link);
            } else {
                linkService.addLink(link);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误");
        }

        return Response.success();
    }

    @PostMapping(value = "/link/delete")
    @ResponseBody
    public Response deleteLink(@RequestParam(name = "mid", required = true) int linkId) {
        try {
            linkService.deleteLinkById(linkId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误");
        }

        return Response.success();
    }

}
