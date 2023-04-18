package com.yicj.practice.oom;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author yicj
 * @date 2023年04月18日 10:34
 */
public class HelloOOMTest {

    @Test
    public void hello() throws InterruptedException {
        // -Xms100m -Xmx100m -XX:+UseSerialGC
        CountDownLatch latch = new CountDownLatch(1) ;
        HelloOOM.fillHeap(1000);
        System.out.println("-------------准备执行gc()-------------------");
        // System.gc(); 执行gc内存会进行一次释放，否则一直占着不释放
        latch.await();
    }

}
