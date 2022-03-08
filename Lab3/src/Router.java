public class Router extends Node implements Identifiable{
    private String ip;

    public Router(String name, String macAddr, String location, String ip) {
        super(name, macAddr, location);
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Router{" +
                "name='" + name + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", location='" + location + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
