import java.util.Map;

public abstract class Node {
    private String name, macAddr, location;
    private Map<String, Integer> cost;

    public Map<String, Integer> getCost() {
        return cost;
    }

    public void setCost(Map<String, Integer> cost) {
        this.cost = cost;
    }

    public Node(String name, String macAddr, String location) {
        this.name = name;
        this.macAddr = macAddr;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
