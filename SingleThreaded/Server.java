import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server{

    public void run() throws IOException{
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

        while(true){
            try{
                System.out.println("Server is listening on port"+ port);
                Socket acceptConn = serverSocket.accept();
                System.out.println("Connection accepted from client"+ acceptConn.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptConn.getOutputStream(), true); //PrintWriter converts the formatted text into bytes which can be send to client through socket
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptConn.getInputStream()));//
                toClient.println("Hello from Server");
                String line = fromClient.readLine();
                System.out.println("Response from Client:" + line);
                toClient.close();
                fromClient.close();
                acceptConn.close();
            
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {   
        try{
            Server server = new Server();
            server.run();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

