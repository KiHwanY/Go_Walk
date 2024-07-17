package com.cos.gowalk.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View를 리턴하겠다!!
@RequiredArgsConstructor
public class IndexController {


    //localhost:8080/
    //localhost:8080
    //머스테치 기본폴더 src/main/resources/
    //뷰리졸버 설정 : templates (prefix), .mustache(suffix) 생략가능
    @GetMapping({"","/"})
    public String index(){
        return "index";
    }
    @GetMapping("/user")
    public @ResponseBody String user(){
//        System.out.println("principalDetails = " + principalDetails.getUser());
        return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }
    @GetMapping("/login")
    public @ResponseBody String login(){
        return "login";
    }
    @GetMapping("/join")
    public @ResponseBody String join(){
        return "join";
    }
    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        return "회원가입 완료됨";
    }



}
