package server.responses;

import Data.ClientData;
import Data.SocialNetwork;

public class ResponseRegister implements  Response{
    private String status;

    @Override
    public void responseToRequest(ClientData clData) {
        status= SocialNetwork.addClientToSocialNetwork(clData);
    }

    public String getStatus() {
        return status;
    }
}
