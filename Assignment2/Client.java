package Assignment2;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket s = null;
    private BufferedReader in = null;
    private DataOutputStream out = null;
    
    public Client(String h,int p){
    try {
      s = new Socket(h,p);
      System.out.println("Connected to: "+s.getInetAddress());
       String request = "GET / HTTP/1.1 Host: stud.fh-wedel.de";
            // Send data to server
            out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(request);
            // Read answer from Server
                in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));
            String answer = in.readLine();
            System.out.println(answer);

    }
    catch(UnknownHostException e){
      System.out.println(e);
    }
     catch(IOException e){
      System.out.println(e);
    } finally {
      try{
                 if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }

                if (s != null) {
                    s.close();
                }
    } catch(IOException e){
      System.out.println(e);
    }
   }
   
   
   }
}
