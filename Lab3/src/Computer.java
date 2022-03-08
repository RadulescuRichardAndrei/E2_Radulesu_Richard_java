public class Computer extends Node implements Storage, Identifiable{
    private String ip;
    private int storageCap;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setStorageCap(int storageCap) {
        this.storageCap = storageCap;
    }

    public String getIp() {
        return ip;
    }

    public int getStorageCap() {
        return storageCap;
    }

    public Computer(String name, String macAddr, String location, String ip, int stCap) {
        super(name, macAddr, location);
        this.ip = ip;
        this.storageCap = stCap;
    }

    public int converse(String conv){
        if(conv.equals("megabyte"))
            return storageCap*1024;
        if(conv.equals("kilobyte"))
            return storageCap*1024*1024;
        if(conv.equals("byte"))
            return storageCap*1024*1024*1024;
        return storageCap;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ip='" + ip + '\'' +
                ", storageCap=" + storageCap +
                ", name='" + name + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
