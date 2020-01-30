import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private static int PORT=5050;
    private static String [] emailArray={"tm2_93@hotmail.com","andreas_myhr_93@hotmail.com", "s333770@oslomet.no","andreastmtm208@gmail.com"};


    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT); //Socket oppretter en listener på port 5050

        System.out.println("Venter på tilkobling");
        Socket client = listener.accept(); //Vi venter på en tilkobling
        System.out.println("Tilkoblet klient");

        PrintWriter ut = new PrintWriter(client.getOutputStream(), true);// Outputstream betyr at vi skriver date til en destination
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));


        try {
            while (true) {
                String request = in.readLine(); //Leser inn data som er sendt fra socket
                String returnString = sokEmail(request);
                ut.println(returnString);
            }
        } finally {
            //Det er så viktig at vi lukker socketen til slutt
            System.out.println("Server har sendt data, nå lukkes den");
            client.close(); //Vi må så lukke clienten
            listener.close();
        }
    }


    private static String sokEmail(String email){
        String tilbakemelding="";
            for(String x: emailArray){
                if(x.equals(email)){
                    tilbakemelding= "Emailen er registrert i systemet";
                }
                else{
                    tilbakemelding= "Finner ingen email";
                }

            }
        return tilbakemelding;
        }

    }

