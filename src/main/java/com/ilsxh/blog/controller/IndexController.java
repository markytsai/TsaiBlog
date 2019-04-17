package com.ilsxh.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test")
    public String returnIndex() {
        return "front/index";
    }

    @RequestMapping("/contact")
    public String contactMe() {
        return "front/contact";
    }

    @RequestMapping("/about")
    public String aboutMe() {
        return "front/about";
    }

    @RequestMapping("/tutorials/git")
    public String toTutorialGit() {
        return "front/tutorials/tutorial_git";
    }
}
