package com.qfit.board_boot_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //기본 페이지 요청 메서드
    @GetMapping("/")
    public String index() {
        return "index"; //=> templates 폴더의 index.html을 찾아간다.
    }

}
