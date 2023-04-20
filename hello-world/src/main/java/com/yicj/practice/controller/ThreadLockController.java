package com.yicj.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yicj
 * @date 2023年04月20日 13:38
 */
@RestController
@RequestMapping("/thread")
public class ThreadLockController {

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    @GetMapping("/lock")
    public String lock(){

        new Thread(() -> {
            synchronized (obj1){
                try {
                    TimeUnit.SECONDS.sleep(10);
                }catch (InterruptedException e){
                }
                synchronized (obj2){
                    System.out.println("thread1 执行到此处");
                }
            }
        }, "thread1").start();

        new Thread(() -> {
            synchronized (obj2){
                synchronized (obj1){
                    System.out.println("thread2 执行到此");
                }
            }
        }, "thread2").start();

        return "thread test" ;
    }
}
