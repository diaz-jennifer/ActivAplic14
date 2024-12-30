package PracticPaExam;

import java.sql.*;
import java.util.Scanner;

public class Pract14_15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con;
        PreparedStatement ps;
        String url, sql;

        int numemp;
        String nombre;
        int edad;
        int oficina;
        String puesto;

        url = "jdbc:mysql://localhost:3306/empresa";
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "INSERT INTO Empleados (numemp, nombre, edad, oficina, puesto, contrato) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

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

            //asignamos:
            ps.setInt(1, numemp);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
            ps.setInt(4, oficina);
            ps.setString(5, puesto);
            ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));

            ps.executeUpdate();

            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }
    }
}
