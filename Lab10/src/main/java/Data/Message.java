package Data;

public class Message {
    private String sender;
    private String msg;
    private int counter;

    public Message(String sender, String msg) {
        this.sender = sender;
        this.msg = msg;
        this.counter = 0;
    }

    public void checkSeen(){
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", msg='" + msg + '\'' +
                ", counter=" + counter +
                '}';
    }
}
