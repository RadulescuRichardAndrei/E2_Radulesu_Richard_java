public class Switch extends Node{

    public Switch(String name, String macAddr, String location) {
        super(name, macAddr, location);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "name='" + name + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
