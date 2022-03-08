public abstract class Node {
    public String name, macAddr, location;

    public Node(String name, String macAddr, String location) {
        this.name = name;
        this.macAddr = macAddr;
        this.location = location;
    }
}
