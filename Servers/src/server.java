import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

import java.io.Writer;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class server {

	static Scanner input = new Scanner(System.in);

	static int commandNum;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {

    	String s = "null";
    	String result = "";
        
    	System.out.println("Port: ");
    	
    	Scanner port = new Scanner(System.in);
    	int port1 = port.nextInt();
    	
    	try (ServerSocket serverSocket = new ServerSocket(port1))
    	{
        
        System.out.println("SERVER: waiting for client connection....");
        
        while (true) 
        {
        	Socket socket = serverSocket.accept();
        //Socket Client = socket.accept();
        System.out.println("SERVER: connected to client!");
        
        System.out.println("Available commands: 1.date 2.uptime 3.memory 4.netstat 5.users 6.ps 7.exit \nPlease choose a number.");       
       
		
        InputStream input = socket.getInputStream(); // get the raw input stream
        OutputStream output = socket.getOutputStream(); // get the raw output stream
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // where to receive input
        PrintWriter writer = new PrintWriter(output, true); // where to write output

        String command = reader.readLine();
        
        Process p = null;
        String cmd = null;
          System.out.println(command);

          if (command.equals("1")) 
          {

        	  cmd = "date";
                         /* p = Runtime.getRuntime().exec("date");

                          BufferedReader stdInput = new BufferedReader(new

                                                          InputStreamReader(p.getInputStream()));

                          while ((s = stdInput.readLine()) != null) {

                                          ((PrintWriter) writer).println("Date and Time: " + s);

                                          writer.flush();*/                                          
                                          //oos.writeObject("Date and Time: " + s);
        	  //}
          }

          

          if (command.equals("2")) {

        	  cmd = "uptime";
					/*
					 * p = Runtime.getRuntime().exec("uptime");
					 * 
					 * BufferedReader stdInput = new BufferedReader(new
					 * 
					 * InputStreamReader(p.getInputStream()));
					 * 
					 * while ((s = stdInput.readLine()) != null) {
					 * 
					 * ((PrintWriter) writer).println("Uptime: " + s);
					 * 
					 * writer.flush(); //oos.writeObject("Uptime: " + s); }
					 */

          }

          if (command.equals("3")) {

        	  cmd = "free";
					/*
					 * p = Runtime.getRuntime().exec("vmstat -s");
					 * 
					 * BufferedReader stdInput = new BufferedReader(new
					 * 
					 * InputStreamReader(p.getInputStream()));
					 * 
					 * while ((s = stdInput.readLine()) != null) {
					 * 
					 * ((PrintWriter) writer).println("Memory Usage: " + s);
					 * 
					 * writer.flush(); //oos.writeObject("Memory Usage: " + s); }
					 */

          }

          if (command.equals("4")) {

        	  cmd = "netstat";
					/*
					 * p = Runtime.getRuntime().exec("netstat");
					 * 
					 * BufferedReader stdInput = new BufferedReader(new
					 * 
					 * InputStreamReader(p.getInputStream()));
					 * 
					 * while ((s = stdInput.readLine()) != null) {
					 * 
					 * ((PrintWriter) writer).println("NetWork Statistics: " + s);
					 * 
					 * writer.flush(); //oos.writeObject("NetWork Statistics: " + s); }
					 */

          }

          if (command.equals("5")) {

        	  cmd = "users";
					/*
					 * p = Runtime.getRuntime().exec("who");
					 * 
					 * BufferedReader stdInput = new BufferedReader(new
					 * 
					 * InputStreamReader(p.getInputStream()));
					 * 
					 * while ((s = stdInput.readLine()) != null) {
					 * 
					 * ((PrintWriter) writer).println("Users: " + s);
					 * 
					 * writer.flush(); //oos.writeObject("Users: " + s); }
					 */

          }

          if (command.equals("6")) {

        	  cmd = "ps -a";
					/*
					 * p = Runtime.getRuntime().exec("ps");
					 * 
					 * BufferedReader stdInput = new BufferedReader(new
					 * 
					 * InputStreamReader(p.getInputStream()));
					 * 
					 * while ((s = stdInput.readLine()) != null) {
					 * 
					 * ((PrintWriter) writer).println("Processes: " + s);
					 * 
					 * writer.flush(); //oos.writeObject("Processes: " + s); }
					 */



          }
      
      p = Runtime.getRuntime().exec(cmd);
      BufferedReader stdInput = new BufferedReader(
              new InputStreamReader(
                  p.getInputStream()
              )
          );
          // append 
          s = stdInput.readLine();
          while (s != null) {
             if(!result.equals("")) { result += '\n';}
             result += s;
             s = stdInput.readLine();
          }
          System.out.println(command);
          System.out.println(result);
          ((PrintWriter) writer).println(result); // write line to output stream

     
      
      System.out.println();
      //Client.close();
      socket.close();
        	}
    	 } catch (IOException ex) {
             System.out.println("Server exception: " + ex.getMessage());
             ex.printStackTrace();
    	 }
	}

}