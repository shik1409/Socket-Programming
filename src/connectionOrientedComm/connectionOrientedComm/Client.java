package connectionOrientedComm;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws Exception{

        //create a socket with specified address and port number.
        Socket socket = new Socket("localhost",1122);


        //once socket is successfully created it impleas that connection is successful.
        //now create i/p and o/p streams to read and write data.
        InputStream ipStream = socket.getInputStream();
        OutputStream opStream = socket.getOutputStream();

        DataInputStream dis = new DataInputStream(ipStream);
        PrintStream ps = new PrintStream(opStream,true); 


        //now do reverse of server as client first read the message sent from server.
        String line = dis.readLine();
        System.out.println(line);


        //Now read a client statement from console and send it to server.
        String response = System.console().readLine();
        ps.println(response);

        ps.close();
        dis.close();

        ipStream.close();
        opStream.close();

        socket.close();

    }
}