package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {
    private static final String serverAddress="127.0.0.1";
    private static final int PORT=5002;
    private static String[] commands={"login username password","register username password",
    "friend username_1 username_2 ... username_k","send messageContent","read","exit","stop"};

    public static void main(String[] args){

        try {
            Socket socket=new Socket(serverAddress,PORT);
            Scanner keyboardInput = new Scanner(System.in);
            for(String s:commands)
                System.out.println(s);
            while (true){
                PrintWriter out= new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println(keyboardInput.nextLine());

                String response= in.readLine();

                System.out.println(response);
                if(String.valueOf(response).equals("exit") || String.valueOf(response).equals("Serverul se va inchide in curand..."))
                    break;


            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
