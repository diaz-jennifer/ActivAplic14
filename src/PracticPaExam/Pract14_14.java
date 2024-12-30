package PracticPaExam;

import java.sql.*;
import java.util.Scanner;

public class Pract14_14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int edadMax;
        int edadMin;
        int edad;
        String nombre;
        Connection con;
        String url, sql;
        PreparedStatement ps;

        System.out.println("Introduce la edad mínima:");
        edadMin = sc.nextInt();
        System.out.println("Introduce la edad máxima:");
        edadMax = sc.nextInt();

        url =  "jdbc:mysql://localhost:3306/empresa";
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");

            sql = "SELECT nombre, edad FROM Empleados WHERE edad>=? AND edad <=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, edadMin);
            ps.setInt(2, edadMax);
            ResultSet rs = ps.executeQuery();
            System.out.println("Empleados con edades entre "+edadMin+" y "+edadMax+":");
            while(rs.next()){
                nombre = rs.getString(1);
                edad = rs.getInt(2);
                System.out.println("Nombre: "+nombre+"\tEdad: "+edad);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }


    }
}
