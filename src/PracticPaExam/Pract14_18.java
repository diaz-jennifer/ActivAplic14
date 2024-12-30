package PracticPaExam;

import java.sql.*;
import java.util.Scanner;

public class Pract14_18 {
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
        Empleado empleadoYaRegistrado = null;
        //Determinamos si el empleado existe
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "SELECT numemp FROM Empleados WHERE numemp=?";
            ps = con.prepareStatement(sql);
            //asignamos
            ps.setInt(1,numemp);
            //ejecutamos
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                empleadoYaRegistrado = new Empleado(id);
            }
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
//Determinamos si oficina existe
        Oficina of = OficinaDAO.read(oficina);

        //Si empleadoYaRegistrado es null es que No existe y se puede registrar el nuevo empleado:
        //Si la oficina no es null, significa que existe
        if(empleadoYaRegistrado==null){
            if(of!=null) {
                EmpleadoDAO.create(e);
            }else{
                System.out.println("Oficina no existe");
            }
        }else{
            System.out.println("Empleado ya existe");
        }



    }
}
