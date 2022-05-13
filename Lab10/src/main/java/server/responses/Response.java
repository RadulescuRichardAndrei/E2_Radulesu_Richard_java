package server.responses;

import Data.ClientData;

public interface Response {

    void responseToRequest(ClientData clData);
}
