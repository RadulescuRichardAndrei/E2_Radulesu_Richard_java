package comands;

public abstract class Command <T>{
    private String name;
    public abstract void implement(T C);
}
