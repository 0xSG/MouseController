package com.sgkcreations;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {


    }



    public void moveMouse(int x,int y){
        try {
            // These coordinates are screen coordinates
            Thread.sleep(10);
            int xCoord = x;
            int yCoord = y;
            Point p= MouseInfo.getPointerInfo().getLocation();
            // Move the cursor
            System.out.println(x+":"+y);
            Robot robot = new Robot();
            robot.mouseMove(p.x+xCoord,p.y+ yCoord);
        } catch (AWTException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        int port=5241;
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
                String sentence = new String( receivePacket.getData(), 0,
                        receivePacket.getLength() );
                System.out.println("RECEIVED: " + sentence);


                
                // now send acknowledgement packet back to sender
//                InetAddress IPAddress = receivePacket.getAddress();
//                String sendString = "polo";
//                byte[] sendData = sendString.getBytes("UTF-8");
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
//                        IPAddress, receivePacket.getPort());
//                serverSocket.send(sendPacket);sendPacket
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // should close serverSocket in finally block
    }
}
