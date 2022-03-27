package lab5;

public class IncorectFileNameException extends Exception{
    public IncorectFileNameException(String errMsg){
        super(errMsg);
    }
    public IncorectFileNameException(String errMsg,Throwable err){
        super(errMsg,err);
    }

}
