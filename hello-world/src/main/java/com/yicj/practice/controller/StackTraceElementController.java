package com.yicj.practice.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yicj
 * @date 2023年04月18日 11:12
 */
@Slf4j
@RestController
public class StackTraceElementController {

    @Data
    class StackTraceInfo{
        private String threadName ;

        private String content ;
    }


    @GetMapping("/allStackTraces")
    public List<StackTraceInfo> allStackTraces(){

        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        Set<Map.Entry<Thread, StackTraceElement[]>> entries = allStackTraces.entrySet();
        List<StackTraceInfo> retList = new ArrayList<>() ;
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace: entries){
            Thread thread = stackTrace.getKey();
            StackTraceElement[] stack = stackTrace.getValue();
            if (thread.equals(Thread.currentThread())){
                continue;
            }
            StackTraceInfo traceInfo = new StackTraceInfo() ;
            traceInfo.setThreadName(thread.getName());
            StringBuilder builder = new StringBuilder() ;
            builder.append(thread.getName()) ;
            for (StackTraceElement element: stack){
                builder.append("\t") ;
                builder.append(element.toString()) ;
                builder.append("\r\n") ;
            }
            traceInfo.setContent(builder.toString());
            retList.add(traceInfo) ;
        }
        return retList ;
    }

}
