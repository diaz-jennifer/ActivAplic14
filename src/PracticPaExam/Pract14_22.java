package PracticPaExam;

import java.util.Arrays;
import java.util.Scanner;

public class Pract14_22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la superficie");
        int superficie = sc.nextInt();
        System.out.println("Estas son las oficinas con superficie superior a "+superficie);
        Oficina[] lista = OficinaDAO.muestraTodasOficinas(superficie);
        System.out.println(Arrays.deepToString(lista));
    }
}
