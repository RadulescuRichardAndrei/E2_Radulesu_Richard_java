package lab5;

import comands.*;
import org.apache.log4j.BasicConfigurator;

public class Main {

    public static void main(String[] args){
        BasicConfigurator.configure();

        Catalog c=new Catalog();

        ListCommand lc=new ListCommand();

        LoadCommand loadc=new LoadCommand();
        loadc.implement(c);
        lc.implement(c);

        ViewCommand vc=new ViewCommand();
        vc.implement(c.getItemList().get(2));
        vc.implement(c.getItemList().get(3));

        AddCommand ad=new AddCommand();
        ad.implement(c);

        ReportCommand rc=new ReportCommand();
        rc.implement(c);

        SaveCommand sv=new SaveCommand();
        sv.implement(c);

    }
}
