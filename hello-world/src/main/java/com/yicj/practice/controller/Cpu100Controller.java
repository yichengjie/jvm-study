package com.yicj.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yicj
 * @date 2023年04月20日 13:40
 */
@RestController
@RequestMapping("/cpu100")
public class Cpu100Controller {

    // https://zhuanlan.zhihu.com/p/541937552
    @GetMapping("/hello")
    public String hello(){
        new Thread(() -> {
            while (true){
                String str = UUID.randomUUID().toString();
            }
        }, "cpu demo thread").start();

        new Thread(() -> {
            while (true){
                String str = UUID.randomUUID().toString();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cpu with sleep thread").start();
        return "hello world" ;
    }
}
