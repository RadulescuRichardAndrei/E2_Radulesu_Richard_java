package server.responses;

import Data.ClientData;
import Data.SocialNetwork;

public class ResponseLogin implements Response{
    private String status;
    @Override
    public void responseToRequest(ClientData clData) {
        status=SocialNetwork.authenticateSocialNetwork(clData);
    }

    public String getStatus() {
        return status;
    }
}
