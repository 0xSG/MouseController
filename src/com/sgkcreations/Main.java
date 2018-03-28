package com.sgkcreations;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Point pp=new Point(0,0);
//                while (true){
//
//                    Point p = MouseInfo.getPointerInfo().getLocation();
//                    if(p.x!=pp.x && p.y!=pp.y)
//                        System.out.println(p.x + ":" + p.y);
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    pp=new Point(p);
//                }
//            }
//        }).run();

       /* for (int i = 0; i < 100; i++) {
            moveMouse(i,(i));
        }*/

        int port = 5545;
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[8];

            System.out.printf("Listening on udp:%s:%d%n",
                    InetAddress.getLocalHost().getHostAddress(), port);

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);

            while (true) {


                serverSocket.receive(receivePacket);


                byte[] recBytes = receivePacket.getData();
                String string = new String(recBytes);
                try {
                    String[] strings = string.split(":");
                    int x, y;
                    x = Integer.valueOf(strings[0]);
                    y = Integer.valueOf(strings[1]);
                  
                    System.out.println("Point got " + string);
                    moveMouse(x,y);

                } catch (Exception e2) {

                }


            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public static void moveMouse(int x,int y) {
        try {
            Robot robot = new Robot();
            Point p = MouseInfo.getPointerInfo().getLocation();
            int xNew=p.x+x,yNew=p.y+y;

            if(p.x<xNew&&p.y<yNew){
                for (int xi = p.x, yi = p.y; (xi <= xNew) && (yi <= yNew); xi++, yi++) {
                    robot.mouseMove(xi, yi);
                }
            } else
            if(p.x>xNew&&p.y<yNew){
                for (int xi = p.x, yi = p.y; (xi >= xNew) && (yi <= yNew); xi--, yi++) {
                    robot.mouseMove(xi, yi);
                }
            } else
            if(p.x<xNew&&p.y>yNew){
                for (int xi = p.x, yi = p.y; (xi <= xNew) && (yi >= yNew); xi++, yi--) {
                    robot.mouseMove(xi, yi);
                }
            } else
            {
                for (int xi = p.x, yi = p.y; (xi >= xNew) && (yi >= yNew); xi--, yi--) {
                    robot.mouseMove(xi, yi);
                }
            }
//                robot.mouseMove(p.x+xCoord,p.y+ yCoord);
        } catch (AWTException e) {
        }
    }

    public void run() {

        // should close serverSocket in finally block
    }
}
