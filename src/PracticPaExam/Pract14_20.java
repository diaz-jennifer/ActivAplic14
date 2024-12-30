package PracticPaExam;

import java.util.Scanner;

public class Pract14_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el número del empleado a eliminar:");
        int numemp = sc.nextInt();
        Empleado e = EmpleadoDAO.read(numemp);
        if(e!=null){
            EmpleadoDAO.delete(numemp);
            System.out.println("Empleado eliminado");
        }else{
            System.out.println("No existe ese empleado");
        }
    }
}
