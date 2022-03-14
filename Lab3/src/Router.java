public class Router extends Node implements Identifiable{
    private String ip;

    public Router(String name, String macAddr, String location, String ip) {
        super(name, macAddr, location);
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ip='" + getIp() + '\'' +
                ", name='" + getName() + '\'' +
                ", macAddr='" + getMacAddr() + '\'' +
                ", location='" + getLocation() + '\'' +
                '}';
    }
}
