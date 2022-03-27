package comands;

import lab5.Item;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;

public class ViewCommand extends Command<Item> {
    @Override
    public void implement(Item I) {
    try {

        Desktop myD= Desktop.getDesktop();
        if(I.getType().equals("book")){
            File f=new File(I.getLocation());
            myD.open(f);
        }
        else{
            URI myUri= URI.create(I.getLocation());
            myD.browse(myUri);

        }
    }
    catch (Exception evt){
        JOptionPane.showMessageDialog(null,evt.getMessage());
    }


    }
}
