package com.sgkcreations;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    static Robot robot ;
    public static void main(String[] args) {

        try {
            robot = new Robot();
            robot.setAutoDelay(10);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            moveMouse(i,i);
        }

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
                String string = new String(recBytes).substring(0,3);
                int code;
                try{
                    code= Integer.valueOf(string);
                }
                catch (java.lang.NumberFormatException ex){
                    code= Integer.valueOf(string.substring(0,2));
                }
               // System.out.println("before:"+code);
                if(code>255){
                    code=code/10;
                   // System.out.println("after division754:"+code);
                }else {
                    code=Integer.valueOf(string);
                }

                type(""+Character.toString((char)code).charAt(0));


            }
        } catch (IOException e) {

        }
    }
    private static void type(String s)
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            if (code > 96 && code < 123) code = code - 32;
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }


    public static void moveMouse(int x,int y) {

        Point p = MouseInfo.getPointerInfo().getLocation();
        int xNew=p.x+x,yNew=p.y+y;

        if(p.x<xNew&&p.y<yNew){
            for (int xi = p.x, yi = p.y; (xi <= xNew) || (yi <= yNew); xi++, yi++) {
                robot.mouseMove(xi, yi);
            }
        } else
        if(p.x>xNew&&p.y<yNew){
            for (int xi = p.x, yi = p.y; (xi >= xNew) || (yi <= yNew); xi--, yi++) {
                robot.mouseMove(xi, yi);
            }
        } else
        if(p.x<xNew&&p.y>yNew){
            for (int xi = p.x, yi = p.y; (xi <= xNew) || (yi >= yNew); xi++, yi--) {
                robot.mouseMove(xi, yi);
            }
        } else
        {
            for (int xi = p.x, yi = p.y; (xi >= xNew) || (yi >= yNew); xi--, yi--) {
                robot.mouseMove(xi, yi);
            }
        }

    }


}
