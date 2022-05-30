package FileSystem;

import Entities.Player;

import java.io.*;

public class FileManager {

    private static String fileName="save_file";

    public static void save(Player myplayer){

        try{
            FileOutputStream f= new FileOutputStream(new File(fileName));
            ObjectOutputStream o=new ObjectOutputStream(f);
            o.writeObject(myplayer);
            o.close();
            f.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static Player read(){
        try {
            FileInputStream fi=new FileInputStream(new File(fileName));
            ObjectInputStream oi=new ObjectInputStream(fi);
            return (Player) oi.readObject();
        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
