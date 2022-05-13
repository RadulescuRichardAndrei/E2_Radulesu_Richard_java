package server;

import Data.ClientData;
import client.Client;
import server.responses.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ResponseThread implements Runnable {
    private final Socket socket;
    private ClientData myClient;
    private boolean status = false;

    public ResponseThread(Socket socket) {
        this.socket = socket;
    }

    private void sendToClient(String msg) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                System.out.println(request);
                if (request.equals("exit")) {
                    sendToClient("exit");
                    break;
                }
                if (request.equals("stop")) {
                    Server.setReadyToAccept(false);
                    sendToClient("Serverul se va inchide in curand...");
                    break;
                }

                StringTokenizer st = new StringTokenizer(request, " ");
                if (st.hasMoreTokens()) {
                    switch (st.nextToken()) {
                        case "login":
                            String user = null, parola = null;
                            if (st.hasMoreTokens()) user = st.nextToken();
                            if (st.hasMoreTokens()) parola = st.nextToken();

                            if (user == null) {
                                sendToClient("Lipseste user si parola");
                                break;
                            }
                            if (parola == null) {
                                sendToClient("Lipseste parola");
                                break;
                            }

                            ClientData connClient = new ClientData(user, parola);
                            ResponseLogin responseLogin = new ResponseLogin();
                            responseLogin.responseToRequest(connClient);
                            if (responseLogin.getStatus().equals("Autentificare reusita")) {
                                myClient = new ClientData(connClient);
                                status = true;
                            }
                            sendToClient(responseLogin.getStatus());

                            break;
                        case "register":
                            user = null;
                            parola = null;
                            if (st.hasMoreTokens()) user = st.nextToken();
                            if (st.hasMoreTokens()) parola = st.nextToken();

                            if (user == null) {
                                sendToClient("Lipseste user si parola");
                                break;
                            }
                            if (parola == null) {
                                sendToClient("Lipseste parola");
                                break;
                            }

                            connClient = new ClientData(user, parola);
                            ResponseRegister responseRegister = new ResponseRegister();
                            responseRegister.responseToRequest(connClient);
                            if (responseRegister.getStatus().equals("Inregistrare reusita")) {
                                myClient = new ClientData(connClient);
                                status = true;
                            }
                            sendToClient(responseRegister.getStatus());

                            break;
                        case "friend":
                            if (status) {
                                List<String> friends = new LinkedList<>();
                                while (st.hasMoreTokens()) {
                                    friends.add(st.nextToken());
                                }
                                ResponseFriend responseFriend = new ResponseFriend();
                                responseFriend.setFriends(friends);
                                responseFriend.responseToRequest(myClient);

                                StringBuilder sb = new StringBuilder("Imprietenit cu:");
                                for (String fr : friends) {
                                    sb.append(" ");
                                    sb.append(fr);
                                }
                                sendToClient(sb.toString());

                            } else
                                sendToClient("User nelogat.");

                            break;
                        case "send":
                            if (status) {
                                ResponseSend responseSend = new ResponseSend();
                                responseSend.setMsg(st.nextToken("\n"));
                                responseSend.responseToRequest(myClient);
                                sendToClient("Mesaj trimis");
                            } else
                                sendToClient("User nelogat.");
                            break;
                        case "read":
                            if (status) {
                                ResponseRead responseRead = new ResponseRead();
                                responseRead.responseToRequest(myClient);
                                if(responseRead.getUnreadMessages().size()==0)
                                    sendToClient("Nu sunt mesaje noi");
                                for (String msg : responseRead.getUnreadMessages())
                                    sendToClient(msg);

                            } else
                                sendToClient("User nelogat.");
                            break;
                        case "logout":
                            status = false;
                            sendToClient("User delogat");
                            break;
                        default:
                            sendToClient("Nu e comanda");
                            break;
                    }
                }


            }

        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
