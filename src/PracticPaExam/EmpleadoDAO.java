package PracticPaExam;

import java.sql.*;
import java.util.Arrays;

public class EmpleadoDAO {
    //Método que crea la conexión y la devuelve
    private static Connection conectar(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/empresa";
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }
        return con;
    }

    //Métodos CRUD
    //Método para insertar un objeto Empleado en la BD
    public static void create(Empleado empleado){
        if(empleado !=null){
            Connection con = conectar();
            String sql = "INSERT INTO Empleados (numemp, nombre, edad, oficina, puesto, contrato) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                //asignamos valores
                ps.setInt(1,empleado.getNumemp());
                ps.setString(2, empleado.getNombre());
                ps.setInt(3, empleado.getEdad());
                ps.setInt(4, empleado.getOficina());
                ps.setString(5, empleado.getPuesto());
                ps.setDate(6, new java.sql.Date(empleado.getContrato().getTime()));
                ps.executeUpdate();//ejecutamos
                con.close();//cerramos
                System.out.println("Se ha creado un nuevo empleado en la BD");
            } catch (SQLException e) {
                System.out.println("Error al insertar nuevo empleado en la BD");
            }
        }

    }
//Método que permite consultar un empleado
    public static Empleado read(int numemp){
        Empleado empleado = null;
        try {
            Connection con = conectar();
           String sql = "SELECT * FROM Empleados WHERE numemp=?";
            PreparedStatement ps = con.prepareStatement(sql);
            //asignamos
            ps.setInt(1,numemp);
            //ejecutamos
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               // int numemp = rs.getInt("numemp");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int oficina = rs.getInt("oficina");
                String puesto = rs.getString("puesto");
                Date contrato = rs.getDate("contrato");
                empleado = new Empleado(numemp, nombre, edad, oficina, puesto, contrato);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error al consultar empleado");
        }
        return empleado;
    }
//Método que borra un empleado
    public static void delete(int numemp){
        String sql ="DELETE FROM Empleados WHERE numemp=?";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, numemp);
            ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            System.out.println("Error al eliminar empleado");
        }
    }

}
