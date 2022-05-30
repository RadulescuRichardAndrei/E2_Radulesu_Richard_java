package server;

import Data.ClientData;
import Data.Messages;
import Data.SocialNetwork;
import org.jgrapht.Graph;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT=5002;
    private static boolean readyToAccept=true;

    public static void setReadyToAccept(boolean val) {
        readyToAccept = val;
    }

    public Server() throws IOException {
        ServerSocket serverSocket=null;
        SocialNetwork.initiate("D:\\facultate\\E2_java_scripts\\GitHub\\Lab10\\src\\main\\resources\\users.xml");
        Messages.deleteOldMessages();
        try{
            serverSocket=new ServerSocket(PORT);
            while (readyToAccept){

                Socket socket=serverSocket.accept();
                socket.setSoTimeout(2000000);
                System.out.println("Acceptat");
                new Thread(new ResponseThread(socket)).start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
        System.out.println("Inchidere server...");
    }

    public static void main(String[] args) throws IOException {
        Server server= new Server();
    }
}
