package telran.appl.net;

import telran.view.*;
import telran.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.List;

public class Main {
    static TcpClient client;

    public static void main(String[] args) {
        Item[] items = {
            Item.of("Start session", Main::startSession),
            Item.of("Exit", Main::exit, true)
      };
      Menu menu = new Menu("Application", items);
      menu.perform(new StandardInputOutput());
    }

    public static void startSession(InputOutput io) {
        String host = io.readString("Enter hostname: ");
        Integer port = io.readNumberRange("Enter port: ", "Wrong port", 1, 65536).intValue();

  
        if(client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        client = new TcpClient(host, port);
        Menu menu = new Menu("Session is running", 
            Item.of("Proceed", Main::sessionProcessing), Item.ofExit());
        menu.perform(io);

    }

    static void sessionProcessing(InputOutput io) {
            HashSet<String> types = new HashSet<>(List.of("reverse", "length", "wrong_type_test"));
            String type = io.readStringOptions("Enter operation type " + types, "Wrong type", types);
            String string = io.readString("Enter any string:");
            String response = client.sendAndReceive(type, string);
            io.writeLine(response);
      };
    

    static void exit(InputOutput io) {
        if(client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}