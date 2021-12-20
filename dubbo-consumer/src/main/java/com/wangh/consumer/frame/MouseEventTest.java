package com.wangh.consumer.frame;

import com.wangh.consumer.application.ApplicationContextHelper;
import com.wangh.consumer.service.LocalService;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jfree.ui.RefineryUtilities;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 */
public class MouseEventTest extends JFrame {

    private LocalService localService = (LocalService) ApplicationContextHelper.getBean("localService");

    @Value("remote.ip")
    private String remoteUrl;

    public MouseEventTest() {
        // 设置close按钮的操作（关闭窗口并停止程序运行）
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口默认打开的位置和大小
        this.setBounds(30, 40, 800, 380);
        // 获取容器对象
        Container ctainer = this.getContentPane();
        // 设置布局方式为绝对布局
        ctainer.setLayout(null);


        // “曲线拖拽”的按钮
        JButton drag = new JButton("曲线拖拽");
        drag.setBounds(90, 120, 200, 30);
        drag.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                WanghDragFrame mouselistenerdemo2 = new WanghDragFrame("曲线拖拽");
                mouselistenerdemo2.pack();
                RefineryUtilities.centerFrameOnScreen(mouselistenerdemo2);
                mouselistenerdemo2.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        // 将文本域对象添加到滚动面板
        final JTextArea txtArea = new JTextArea();
        txtArea.setRows(20);
        txtArea.setEnabled(false);// 设置文本域不能够输入
        txtArea.setDisabledTextColor(Color.RED);// 设置文本域在不能够输入的情况下，显示的文本颜色为红色
        JScrollPane sp = new JScrollPane(txtArea);
        sp.setBounds(360, 0, 400, 300);

        System.out.println("test");

        // “发送dubbo请求”的按钮
        JButton report = new JButton("发送dubbo请求");
        report.setBounds(90, 165, 200, 30);
        report.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                String s = null;
                try {
                    s = localService.saySomething();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    txtArea.append("通信异常！远端服务未启动或网段不通。\n" + "异常信息：" + exception + "\n" + "远端ip：" + remoteUrl + "\n");
                }
                InetAddress ip4 = null;
                try {
                    ip4 = Inet4Address.getLocalHost();
                } catch (UnknownHostException unknownHostException) {
                    txtArea.append("获取本地ip异常" + "\n");
                }
                txtArea.append(s + "\n" + "本机ip：" + ip4.getHostAddress() + "\n");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        // “清空信息”的按钮
        JButton btn = new JButton("清空信息");
        btn.setBounds(424, 304, 100, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtArea.setText("");
            }
        });

        // 向容器中放入滚动面板组件、文本标签组件和按钮组件
        ctainer.add(sp);
        ctainer.add(drag);
        ctainer.add(btn);
        ctainer.add(report);

        this.setVisible(true);

    }

}
