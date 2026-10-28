package Assignment2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket s = null;
    PrintWriter out = null;
    BufferedReader in = null;
    String host = "";
    int port = 0;
  
    
    public Client(String h,int p){
      try {
        this.host = h;
        this.port = p;
        s = new Socket(h,p);
        System.out.println("Connectet: "+s.getInetAddress());
      } catch (IOException e) {
       System.out.println(e);
      }

       
    }
    public void sendRequest(){
    try {
         PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
         BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
          out.println("GET / HTTP/1.1");
          out.println("Host: "+host);
          out.println("");
          out.flush();
          System.out.println("Request gesendet");
          
          String answer = "";
          while ((answer = in.readLine()) != null) {
                System.out.println(answer);
          }

          out.close();
          in.close();
          s.close();
            
    } catch (IOException e) {
     System.out.println(e);
    } 
   
    }

    public static void main(String[] args) {
    String server1 = "krzesinski.de";
    String server2 = "localhost";
    Client client = new Client(server2, 80);
    client.sendRequest();
   
   } 

}
