package connectionOrientedComm;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class CommunicationThread extends Thread {
    Socket socket;

    CommunicationThread(Socket socket) {
        this.socket = socket;
        start();
    }

    public void run() {

        try{
            // create streams for communication.
            InputStream ipStream = socket.getInputStream();
            OutputStream opStream = socket.getOutputStream();

            // High-level stream to be created for reading and writing data.
            DataInputStream dis = new DataInputStream(ipStream);
            PrintStream ps = new PrintStream(opStream,true); 
            // true parameter above siginifies that the stream will be auto-flushed 
            //as soon as new line character is encountered

            //send a line on connection with the client 
            ps.println("Welcome to my server.");

            //use dis created above to read from client.
            String line = dis.readLine();
            System.out.println(line);

            ps.close();
            dis.close();
            ipStream.close();
            opStream.close();
            socket.close();
        }
        catch(Exception e){
            System.out.println("Encountered exception in comm thread "+e.getMessage());
        }
    }
}