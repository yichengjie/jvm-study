package com.yicj.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yicj
 * @date 2023年04月20日 13:39
 */
@RestController
@RequestMapping("/memory")
public class MemoryController {

    private static Map<String, String> map = new HashMap<>();

    @GetMapping("/size/{size}")
    public Integer memory(@PathVariable("size") int size) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ;
        for (int i = 0 ; i < size ; i++){
            int value = 1024 * 1024 ;
            byte[] bytes = new byte[value] ;
            outputStream.write(bytes);
            Thread.sleep(100);
        }
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();
        return bytes.length;
    }


    @RequestMapping("/oom")
    public String oom() throws Exception {
        for (int i = 0; i < 100000; i++) {
            map.put("key" + i, "value" + i);
        }
        return "oom";
    }

    @RequestMapping("/exit")
    public String exit() throws Exception {
        System.exit(0);
        return "oom";
    }
}
