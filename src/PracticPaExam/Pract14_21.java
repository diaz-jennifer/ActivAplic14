package PracticPaExam;

import java.util.Arrays;

public class Pract14_21 {
    public static void main(String[] args) {
        Oficina[] lista = OficinaDAO.muestraTodasOficinas();
        System.out.println(Arrays.deepToString(lista));
    }
}
