package com.wangh.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.wangh.consumer.frame.MouseEventTest;
import com.wangh.consumer.frame.WanghDragFrame;
import javax.swing.SwingUtilities;
import org.jfree.ui.RefineryUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * DubboConsumerApplication
 * 消费者启动类
 * @author xiaoze
 * @date 2018/6/7
 */
@EnableDubbo
@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(DubboConsumerApplication.class)
            .headless(false)
            .run(args);
        SwingUtilities.invokeLater(() -> createAndShowGUI());
//        SpringApplication.run(DubboConsumerApplication.class, args);

    }

    private static void createAndShowGUI() {

        MouseEventTest test = new MouseEventTest();

//        WanghDragFrame mouselistenerdemo2 = new WanghDragFrame("曲线拖拽demo");
//        mouselistenerdemo2.pack();
//        RefineryUtilities.centerFrameOnScreen(mouselistenerdemo2);
//        mouselistenerdemo2.setVisible(true);
    }

}
