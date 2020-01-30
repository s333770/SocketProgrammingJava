import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int PORT=5050;
    private static String [] emailArray={"tm2_93@hotmail.com","andreas_myhr_93@hotmail.com", "s333770@oslomet.no","andreastmtm208@gmail.com"};

    private static ArrayList<KlientThread> klient=new ArrayList<>();
    private static ExecutorService pool= Executors.newFixedThreadPool(4);


    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT); //Socket oppretter en listener på port 5050

        while(true){
            System.out.println("Venter på tilkobling");
            /*Dette må skje i en egen tråd */
            Socket client = listener.accept(); //Vi venter på en tilkobling
            System.out.println("Tilkoblet");
            KlientThread klientThread=new KlientThread(client,klient);
            klient.add(klientThread);
            pool.execute(klientThread);
        }


    }
    public static String sokEmail(String email){
        String tilbakemelding="";
        for(String x: emailArray){
            if(x.equals(email)){
                 return tilbakemelding= "Emailen er registrert i systemet";
            }
            else{
                return tilbakemelding= "Finner ingen email";
            }

        }
        return tilbakemelding;
    }

    }

