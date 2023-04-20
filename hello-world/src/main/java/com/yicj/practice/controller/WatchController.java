package com.yicj.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yicj
 * @date 2023年04月20日 13:42
 */
//https://zhuanlan.zhihu.com/p/541937552
@RestController
@RequestMapping("/watch")
public class WatchController {
    private static Random random = new Random();
    private int illegalArgumentCount = 0;

    @GetMapping("/test")
    public String test(){
        String res = null;
        try {
            int number = random.nextInt() / 10000;
            List<Integer> idStrs = this.getIdStr(number);
            res = printList(number, idStrs);
        }
        catch (Exception e) {
            System.out.println(String.format("illegalArgumentCount:%3d, ", this.illegalArgumentCount) + e.getMessage());
        }
        return res;
    }


    private List<Integer> getIdStr(int number) {
        if (number < 5) {
            ++this.illegalArgumentCount;
            throw new IllegalArgumentException("number is: " + number + ", need >= 5");
        }
        List<Integer> result = new ArrayList<Integer>();
        int count = 2;
        while (count <= number) {
            if (number % count == 0) {
                result.add(count);
                number /= count;
                count = 2;
                continue;
            }
            ++count;
        }
        return result;
    }


    private String printList(int number, List<Integer> primeFactors) {
        StringBuffer sb = new StringBuffer(number + "=");
        for (int factor : primeFactors) {
            sb.append(factor).append('*');
        }
        if (sb.charAt(sb.length() - 1) == '*') {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
        return sb.toString();
    }

}
