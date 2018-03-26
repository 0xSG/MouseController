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

        int port=5545;
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[8];

            System.out.printf("Listening on udp:%s:%d%n",
                    InetAddress.getLocalHost().getHostAddress(), port);

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);

            while(true)
            {


                serverSocket.receive(receivePacket);


                byte[] recBytes=receivePacket.getData();
                String string =new String(recBytes);
                try {
                    String[] strings=string.split(":");
                    int x,y;
                    x=Integer.valueOf(strings[0]);
                    y=Integer.valueOf(strings[1]);
                    Point pointGot=new Point(x,y);
                    System.out.println("Point got "+string);
                    moveMouse(pointGot);

                }catch (Exception e2){

                }
              


            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }



    public static void moveMouse(Point point){
        try {
            // These coordinates are screen coordinates
            Thread.sleep(10);
            int xCoord = point.x;
            int yCoord = point.y;
            Point p= MouseInfo.getPointerInfo().getLocation();
            // Move the cursor
            System.out.println(p.x+":"+p.y);
            Robot robot = new Robot();
            robot.mouseMove(p.x+xCoord,p.y+ yCoord);
        } catch (AWTException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {

        // should close serverSocket in finally block
    }
}
