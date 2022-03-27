package comands;

import lab5.Catalog;
import lab5.Item;

import java.util.Scanner;

public class AddCommand extends Command<Catalog> {

    @Override
    public void implement(Catalog C) {
        Scanner sc=new Scanner(System.in);
        Item I=new Item();
        System.out.println("\t"+"Adding new comands.Item:");
        System.out.println("enter ID:");
        I.setId(sc.nextLine());
        System.out.println("enter Title");
        I.setTitle(sc.nextLine());
        System.out.println("enter Location");
        I.setLocation(sc.nextLine());
        System.out.println("enter Author");
        I.setAuthor(sc.nextLine());
        System.out.println("enter Type");
        I.setType(sc.nextLine());
        System.out.println("enter Year");
        I.setYear(sc.nextLine());
        C.getItemList().add(I);
    }
}
