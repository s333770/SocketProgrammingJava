import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;

public class KlientThread implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter ut;
    private ArrayList<KlientThread> clients;

    public KlientThread(Socket clientSocket, ArrayList<KlientThread> clients)throws IOException {
        this.client=clientSocket;
        in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        ut=new PrintWriter(client.getOutputStream(),true);
        this.clients=clients;

    }

    @Override
    public void run() {

        try {
            while (true) {
                String request = in.readLine(); //Leser inn data som er sendt fra socket
                if(request.startsWith("fellesMelding")){
                    int firstSpace=request.indexOf(" ");
                    if(firstSpace!=-1){
                        outToAll(request.substring(firstSpace+1));
                    }

                }
                String returnString = Server.sokEmail(request);
                ut.println(returnString);
            }
        }
        catch(IOException e) {
            System.err.println("IO-exception");
        }
        finally {
            //Det er så viktig at vi lukker socketen til slutt
            System.out.println("Server har sendt data, nå lukkes den");
            try {
                in.close(); //Vi må så lukke clienten
            } catch (IOException e) {
                e.printStackTrace();
            }
            ut.close();
        }
    }

    private void outToAll(String msg) {
        for(KlientThread aclient: clients){
            aclient.ut.println(msg);
        }
    }


}
