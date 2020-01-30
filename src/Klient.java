import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Klient {
    private static String SERVER_ID="127.0.0.1";
    private static int SERVER_PORT=5050;



    public static void main(String[] args) throws IOException {
        Socket socket =new Socket(SERVER_ID,SERVER_PORT); //Lager tilbkobling til server
        ServerConnection serverConn=new ServerConnection(socket);
        //BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream())); //Får data fra socket
        BufferedReader tastatur=new BufferedReader(new InputStreamReader(System.in)); //Tar inn data fra tastatur
        PrintWriter ut=new PrintWriter(socket.getOutputStream(),true); //Data som skal skrives ut
        System.out.println("Søk etter en email, skriv inn 'quit' for å avslutte");
        new Thread(serverConn).start();


        while(true){
            String kommando=tastatur.readLine(); //Her leser vi inn kommando fra tastatur
            if(kommando.equals("quit"))break; //Avslutter med quit
            ut.println(kommando);
            

        }
        socket.close();
        System.exit(0);


    }
}
