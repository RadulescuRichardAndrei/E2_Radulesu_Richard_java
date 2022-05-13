package Data;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Messages {
    private static List<Message> msgList = new LinkedList<>();

    public static void deleteOldMessages() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable clearSeenMessages = () -> {
            for (int i = msgList.size() - 1; i >= 0; i--){
                    msgList.remove(msgList.get(i));
            }
        };
        executorService.scheduleAtFixedRate(clearSeenMessages, 0, 60, TimeUnit.SECONDS);
    }

    public static void addMessage(ClientData clientData, String msg) {
        msgList.add(new Message(clientData.getUsername(), msg));
    }

    public static List<String> getMessages(String receiver) {
        List<String> unreadMessages = new LinkedList<>();
        for (Message msg : msgList){

            System.out.println(SocialNetwork.listFriends(msg.getSender()));
            if (Objects.requireNonNull(SocialNetwork.listFriends(msg.getSender())).contains(receiver))
                unreadMessages.add("Mesaj de la: " + msg.getSender() + "\t Continut mesaj: \t" + msg.getMsg());

            }
        return unreadMessages;
    }



}
