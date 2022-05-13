package server.responses;

import Data.ClientData;
import Data.SocialNetwork;

import java.util.List;

public class ResponseFriend implements Response{
    List<String> friends;
    @Override
    public void responseToRequest(ClientData clData) {
        SocialNetwork.addFriends(clData,friends);
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
