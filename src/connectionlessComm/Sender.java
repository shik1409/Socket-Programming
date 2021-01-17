package connectionlessComm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

    public static void main(String[] args) throws Exception{
        InetAddress destHost = InetAddress.getLocalHost();

        int destPort = 4040;

        DatagramSocket ds = new DatagramSocket();
        String line = System.console().readLine();

        boolean flag = true;
        while(flag){

            byte[] data = line.getBytes();

            ds.send(new DatagramPacket(data, data.length,destHost,destPort));

            line = System.console().readLine();

        }

        ds.close();
    }
    
}