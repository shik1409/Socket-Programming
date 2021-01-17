package connectionOrientedComm;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    //in-case there is address already in use exception thrown because a port is bound to some other program.
    //we can see the PID using below command.
    //windows:- netstat -ano | findStr "1122"
    // and kill the task using:
    //Windows: taskkill /F /PID <pid_number>
    
    public static void main(String[] args) throws Exception{

        //According to below code the serviersocket will create a communication socket with client.
        //send a amesg to the client and rad a string from client.
        //after which it will just close the sockets created.
        //SimpleOperation();

        //The above is obviously not good.
        //the below code will keep on polling for client requests
        //whenever a client request is recieved it will open a socket with client and will interact with client.
       //MultipleClientConn();

        //the baove implementation although allows multiple client interaction will be mutually
        //blocking for the client for communication.
        //to make communication enabled with multiple clients use below function
        ConcurrentComm();

       //FreeAPort.freeAPort(1122);

    }


    public static void SimpleOperation() throws Exception{
        //In Most organizations 0-1023 is bound for special purposes and hence require administrative privileges.
        //Hence to be safe using socket no. > 1023
        ServerSocket serverSocket = new ServerSocket(1122);
        Socket socket = serverSocket.accept();

        //create streams for communication.
        InputStream ipStream = socket.getInputStream();
        OutputStream opStream = socket.getOutputStream();

        //High-level stream to be created for reading and writing data.
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
        serverSocket.close();
    }

    public static void MultipleClientConn() throws Exception{
        //In Most organizations 0-1023 is bound for special purposes and hence require administrative privileges.
        //Hence to be safe using socket no. > 1023
        ServerSocket serverSocket = new ServerSocket(1122);

        // a flag needs yto be created which can control whether the serversockeyt needs to be closed or not.
        //as flag is true the serversocket will remain open for various clients to inetract.
        //whenever a client connection is established a socket object will be created.
        //using the created socket interaction will happen and then socket will be closed.
        boolean flag = true;
        while(flag){
            Socket socket = serverSocket.accept();
            //create streams for communication.
            InputStream ipStream = socket.getInputStream();
            OutputStream opStream = socket.getOutputStream();

            //High-level stream to be created for reading and writing data.
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
        serverSocket.close();

    }

    public static void ConcurrentComm() throws Exception{
        //In Most organizations 0-1023 is bound for special purposes and hence require administrative privileges.
        //Hence to be safe using socket no. > 1023
        ServerSocket serverSocket = new ServerSocket(1122);

        // a flag needs yto be created which can control whether the serversockeyt needs to be closed or not.
        //as flag is true the serversocket will remain open for various clients to inetract.
        //whenever a client connection is established a socket object will be created.
        //using the created socket interaction will happen and then socket will be closed.
        boolean flag = true;
        while(flag){
            Socket socket = serverSocket.accept();
            new CommunicationThread(socket);
        }
        serverSocket.close();

    }
}