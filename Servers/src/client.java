import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;
import java.net.*;
import java.io.*;


class Client extends Thread
{
	String hostname;
    int port;
    String command;
    long start = 0;
    long end = 0;
                
	Client(String name, String hname, int portNumber, String c, long time){
        super(name);
        hostname = hname;
        port = portNumber;
        command = c;
        start = time;  // Record task's start time on thread creation
        this.start();  // Start thread immediately upon creation
}
	public long getRuntime() {
        if(end != 0) // Double-check thread has finished
                return end - start; // Report task duration in ns
        else
                return -1;
    }
	public void run() {
        try (Socket socket = new Socket(hostname, port)) {
            // Open input stream and reader
        	InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // Open output stream and writer
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(command); // send the command

            // Record output
            String s = reader.readLine();
            String result = "";
            while (s != null) {
               result = result + "\n" + s;
               s = reader.readLine();
            }
            System.out.println(this.getName() + " result: " + result );
            
            // Record task's ending time
            end = System.nanoTime();
        }
        catch(UnknownHostException ex){
                System.out.println("Server not found:" + ex.getMessage());
        }
        catch(IOException ex) {
                System.out.println("IOException in thread " + this.getName() + ": " + ex.getMessage());
        }
    }
}
public class client
{
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        
    	
    	String text;
    	String command;
        Scanner input = new Scanner(System.in);
        System.out.println("enter Host IP: ");
        String userIP  = input.nextLine();
        System.out.println("enter port: ");
        int userPort = input.nextInt();
        
		
		/*
		 * Socket socket = new Socket(userIP, userPort);
		 * 
		 * BufferedReader in = new BufferedReader(new InputStreamReader(
		 * socket.getInputStream()));
		 * 
		 * 
		 * int x = 0;
		 */

		 //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); 
  
        //String hostname = "139.62.210.153";

        //int port = userPort;
        String response = "";
        
		/*
		 * do { String s = (String) ois.readObject();
		 * 
		 * for (int i =0; i < num; i++) {
		 * 
		 * System.out.println(s);
		 * 
		 * 
		 * 
		 * }
		 * 
		 * //System.out.
		 * println("Available commands 1.date 2.uptime 3.memory 4.netstat 5.users " // +
		 * "6.ps 7.exit nPlease choose a number.");
		 * 
		 * //String response = "";
		 * 
		 * //num =
		 * Integer.parseInt(console.readLine("\n Please enter number of clients: "));
		 * 
		 * //char[] totalTime = null; //System.out.println(totalTime);
		 * 
		 * } while (!response.equals("7"));
		 */
        @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
        while(!response.equals("7")) {
        	System.out.println("Available commands 1.date 2.uptime 3.memory 4.netstat 5.users "
        			+ "6.ps 7.exit nPlease choose a number: ");

            response = keyboard.nextLine();

            if(!response.equals("7")) {
                    // Solicit num clients
                    System.out.print("Number of clients: ");
                    
                    int clients = Integer.parseInt(keyboard.nextLine());
                    
                    ArrayList<Client> client = new ArrayList<Client>();
                    long runtime = 0;

                    for(int i = 0; i < clients; i++){
                        // Create a new client
                    	client.add(new Client("Client " + (i+1), userIP, userPort, response, System.nanoTime()));
                    }

                    for(int i = 0; i < client.size(); i++){
                        // wait until this client thread is finished
                        while(client.get(i).isAlive()) {}

                        // get and print runtime
                        long duration = client.get(i).getRuntime();
                        System.out.println(client.get(i).getName() + " runtime: " + (duration/1000000) + " ms");
                        runtime += (duration); // add runtime to running total
                    }
                    // Print totals and average
                    System.out.println("Total runtime for " + clients + " clients: " + runtime/1000000 + " ms");
                    System.out.println("Average runtime: " + (runtime/1000000)/clients + " ms");
            }
        }
    }
}