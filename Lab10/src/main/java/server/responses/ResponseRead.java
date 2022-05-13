package server.responses;

import Data.ClientData;
import Data.Messages;

import java.util.List;

public class ResponseRead implements Response {
    List<String> unreadMessages;
    @Override
    public void responseToRequest(ClientData clData) {
         unreadMessages=Messages.getMessages(clData.getUsername());
    }

    public List<String> getUnreadMessages() {
        return unreadMessages;
    }
}
