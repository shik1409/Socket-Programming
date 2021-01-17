package connectionlessComm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Reciever {
    
    public static void main(String[] args) throws Exception{
        byte buf[] = new byte[1024];

        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        DatagramSocket socket = new DatagramSocket(4040);

        boolean flag = true;

        while(flag){

            socket.receive(dp);

            byte[] data = dp.getData();
            int size = dp.getLength();
            System.out.println ("Host : "+dp.getAddress() ) ;
            System.out.println ("Port : "+dp.getPort() ) ;
            System.out.println ("Data : "+new String(data,0, size) ) ;
            buf = new byte [ 1024 ] ;
            dp = new DatagramPacket ( buf , buf.length ) ;
        }

        socket.close();
    }

}