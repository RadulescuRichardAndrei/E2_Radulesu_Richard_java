package comands;

import lab5.Catalog;

public class ListCommand extends Command<Catalog> {
    @Override
    public void implement(Catalog C) {
        System.out.println(C.getItemList());
    }
}
