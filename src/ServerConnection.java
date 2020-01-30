import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private Socket server;
    private BufferedReader in;
    private PrintWriter ut;


    public ServerConnection(Socket s) throws IOException {
        server=s;
        in=new BufferedReader(new InputStreamReader(server.getInputStream()));

    }


    @Override
    public void run() {

            try{
                while(true) {

                    String serverResponse = in.readLine();
                    if(serverResponse==null) {
                        break;
                    }
                    System.out.println("server says: " + serverResponse);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

