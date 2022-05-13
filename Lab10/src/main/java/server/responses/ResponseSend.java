package server.responses;

import Data.ClientData;
import Data.Messages;

public class ResponseSend implements Response{
    private String msg;


    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void responseToRequest(ClientData clData) {
        Messages.addMessage(clData,msg);
    }
}
