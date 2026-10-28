package Assignment2;

import java.net.*;
import java.io.*;

public class Server {
  
    
     Socket s = null;
     ServerSocket ss = null;
   

    // Constructor with port
    public Server(int port) {
      
        try
        {
            ss = new ServerSocket(port,50,InetAddress.getByName("0.0.0.0"));
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            s = ss.accept();
            System.out.println("Client accepted");

           Thread r = new requestThread(s);
            r.start();
            r.join();

        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        catch(InterruptedException e){
            System.err.println();
        } finally{
            try {
                s.close();
                System.out.println("Closing connection");
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[])
    {
        Server s = new Server(80);
    }

  
}
class requestThread extends Thread{
     Socket s = null;
     PrintWriter out = null;
     BufferedReader in = null;
    
    public requestThread(Socket s){
        this.s = s;
        try {
           this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
           this.out = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
        } catch (IOException e) {
           System.err.println(e);
        }
       
    }

    public void run(){
        try {
                      
            String m = in.readLine();
            System.out.println(m);
            if (m.contains("GET")) {
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println();
                
                out.println("<html><body>You have reached stud104540 Server. Congrats!</body></html>");

                out.flush();

              
            } 
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
