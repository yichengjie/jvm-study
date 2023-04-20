package com.yicj.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yicj
 * @date 2023年04月20日 13:39
 */
@RestController
@RequestMapping("/hot")
public class HotController {

    @GetMapping("/test")
    public String test(){
        boolean flag = true;
        if (flag) {
            System.out.println("开始处理逻辑");
            throw new RuntimeException("出异常了");
        }
        System.out.println("结束流程");
        return "hot test";
    }
}
