package ultramodern.activity.milkdiary;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class server {
    public static String message;
    public static void main(String[] args) {
        connectToServer();
    }
    public static void connectToServer(){
        //creation of a socket and specification of the port
        try(ServerSocket serversocket = new ServerSocket(7493)){
            //accepting ServerSocket connection
            Socket connectionsocket = serversocket.accept();

            //enabling to and fro communication with the server
            InputStream inputtoserver = connectionsocket.getInputStream();
            OutputStream outputfromserver = connectionsocket.getOutputStream();

            //converting input and output data into data understandable by the server
            Scanner scanner = new Scanner(inputtoserver, "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputfromserver,"UTF-8"),true);
            serverPrintOut.println("HELLO HARRY");

            //accepting client connection
            Socket client = serversocket.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
            //getting client message
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine();
            System.out.println(message);
            //inputStreamReader.close();
            //client.close();

            //boolean done = false;
            //while (!done && scanner.hasNextLine()){
            //    String line = scanner.nextLine();
            //    String inputdouble = scanner.toString();
            //    serverPrintOut.println(message);
            //}
        } catch (IOException ex) {
            System.out.println("Problem in message reading..");

        }
    }
}
