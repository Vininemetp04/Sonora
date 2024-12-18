import dinamic.Lista;
import modelos.Modelos;
import saveman.Save;

import java.util.Scanner;

public class Test {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Lista<Modelos> a = Save.readSaveUser();
        System.out.println(a);
    }

}
