package com.siyi.bulls.sentinel.demo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-18 16:58
 * @Description:
 */
public class RtDegradeDemo {
    private static final String KEY = "abc";

    private static AtomicInteger pass = new AtomicInteger();
    private static AtomicInteger block = new AtomicInteger();
    private static AtomicInteger total = new AtomicInteger();

    private static volatile boolean stop = false;
    private static final int threadCount = 100;
    private static int seconds = 60 + 40;

    public static void main(String[] args) throws Exception {
        System.setProperty("csp.sentinel.dashboard.server", "127.0.0.1:8090");
        // 1. 创建监控线程
        tick();
        // 2. 初始化降级规则
        initDegradeRule();
        // 3. 模拟100个线程进行调用
        for (int i = 0; i < threadCount; i++) {
            Thread entryThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        Entry entry = null;
                        try {
                            TimeUnit.MILLISECONDS.sleep(5);
                            entry = SphU.entry(KEY);
                            // token acquired
                            pass.incrementAndGet();
                            // sleep 600 ms, as rt
                            TimeUnit.MILLISECONDS.sleep(600);
                        } catch (Exception e) {
                            block.incrementAndGet();
                        } finally {
                            total.incrementAndGet();
                            if (entry != null) {
                                entry.exit();
                            }
                        }
                    }
                }

            });
            entryThread.setName("working-thread");
            entryThread.start();
        }
    }

    /**
     * 初始化的降级规则
     */
    private static void initDegradeRule() {
        // 创建规则集合
        List<DegradeRule> rules = new ArrayList<DegradeRule>();
        // 创建一个降级规则
        DegradeRule rule = new DegradeRule();
        // 设定一个KEY标识
        rule.setResource(KEY);
        //  降级的规则， 平均响应时间设为100ms
        // set threshold rt, 10 ms
        rule.setCount(100);
        // 设置降级策略
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 设定时间窗大小， 默认单位是秒
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    // 监控统计线程， 监控统计线程的运行状况
    private static void tick() {
        Thread timer = new Thread(new TimerTask());
        timer.setName("sentinel-timer-task");
        timer.start();
    }

    static class TimerTask implements Runnable {

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("begin to statistic!!!");
            long oldTotal = 0;
            long oldPass = 0;
            long oldBlock = 0;

            while (!stop) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }

                long globalTotal = total.get();
                long oneSecondTotal = globalTotal - oldTotal;
                oldTotal = globalTotal;

                long globalPass = pass.get();
                long oneSecondPass = globalPass - oldPass;
                oldPass = globalPass;

                long globalBlock = block.get();
                long oneSecondBlock = globalBlock - oldBlock;
                oldBlock = globalBlock;

                System.out.println(TimeUtil.currentTimeMillis() + ", total:" + oneSecondTotal
                        + ", pass:" + oneSecondPass + ", block:" + oneSecondBlock);

                if (seconds-- <= 0) {
                    stop = true;
                }
            }

            long cost = System.currentTimeMillis() - start;
            System.out.println("time cost: " + cost + " ms");
            System.out.println("total:" + total.get() + ", pass:" + pass.get()
                    + ", block:" + block.get());
            System.exit(0);
        }
    }
}
