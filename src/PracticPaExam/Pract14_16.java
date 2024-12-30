package PracticPaExam;

import java.sql.*;
import java.util.Scanner;

public class Pract14_16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String url, sql;

        int numemp;
        String nombre;
        int edad;
        int oficina;
        String puesto;

        url = "jdbc:mysql://localhost:3306/empresa";

            System.out.println("Introduce los datos de un empleado:");
            System.out.println("Núm. empleado: ");
            numemp = sc.nextInt();
            System.out.println("Nombre: ");sc.nextLine();
            nombre = sc.nextLine();
            System.out.println("Edad: ");
            edad = sc.nextInt();
            System.out.println("Oficina: ");
            oficina = sc.nextInt();
            System.out.println("Puesto: ");sc.nextLine();
            puesto = sc.nextLine();

            //Creamos el empleado:
            Empleado e = new Empleado(numemp, nombre, edad, oficina, puesto);
            EmpleadoDAO.create(e);


    }
}
