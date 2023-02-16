package com.lishuai.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@RestController
public class HelloController {

    @PreAuthorize("hasAuthority('system:dept:list')")
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
