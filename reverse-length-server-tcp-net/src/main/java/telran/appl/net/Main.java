package telran.appl.net;

import java.io.*;
import java.net.*;
import org.json.*;
import telran.net.TcpServer; 
 
public class Main {
    private static final int PORT = 5000;

    public static void main(String[] args) throws Exception {
        TcpServer server= new TcpServer(new ReverseLengthProtocol(), PORT);
        server.run();
    }
}