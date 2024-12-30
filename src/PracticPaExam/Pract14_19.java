package PracticPaExam;

import java.sql.*;
import java.util.Scanner;

public class Pract14_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
/*        Empleado[] listaEmpleadosActual= new Empleado[0];
        Empleado[] listaEmpleadosNueva= new Empleado[0];*/
        Connection con;
        String sql, url;
        PreparedStatement ps;
        ResultSet rs;
        System.out.println("Introduce la oficina que quieres cambiar:");
        int oficina = sc.nextInt();

        url = "jdbc:mysql://localhost:3306/empresa";
        muestraEmpleadosDeOficina(url, oficina);

        System.out.println("Ahora introduce la nueva oficina a la que se cambiará a los empleados");
        int oficinaNueva = sc.nextInt();
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "UPDATE Empleados SET oficina=? WHERE oficina = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, oficinaNueva);
            ps.setInt(2, oficina);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }

        muestraEmpleadosDeOficina(url, oficinaNueva);
/*        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "SELECT numemp, nombre, edad, puesto, contrato FROM Empleados WHERE oficina = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, oficinaNueva);
            rs = ps.executeQuery();
            System.out.println("Estos son los empleados que hay ahora en la nueva oficina: "+ oficinaNueva);
            while(rs.next()){
                int numemp = rs.getInt(1);
                String nombre=rs.getString(2);
                int edad = rs.getInt(3);
                String puesto = rs.getString(4);
                Date contrato = rs.getDate(5);
                System.out.println("N.Emp: "+numemp+ "\tNombre="+nombre+"\tEdad="+edad+
                        "\tPuesto="+puesto+"\tF.Contrato="+contrato);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }*/

    }

     static void muestraEmpleadosDeOficina(String url, int oficina) {
        ResultSet rs;
        Connection con;
        String sql;
        PreparedStatement ps;
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "SELECT numemp, nombre, edad, puesto, contrato FROM Empleados WHERE oficina = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, oficina);
            rs = ps.executeQuery();
            System.out.println("Estos son los empleados de la oficina: "+ oficina);
            while(rs.next()){
                int numemp = rs.getInt(1);
                String nombre=rs.getString(2);
                int edad = rs.getInt(3);
                String puesto = rs.getString(4);
                Date contrato = rs.getDate(5);
                System.out.println("N.Emp: "+numemp+ "\tNombre="+nombre+"\tEdad="+edad+
                        "\tPuesto="+puesto+"\tF.Contrato="+contrato);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }
    }
}
