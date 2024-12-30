/*Utilizando la BD Empleados, crea un programa que pida al usuario los datos de un
 empleado, excepto la fecha de contratación que la generará el sistema (con la fecha de
 introducción de datos) y los inserte en la BD*/

import java.sql.*;
//import java.time.LocalDate;
import java.util.Scanner;
//import java.util.Date;

public class ActivAplic14_15 {
    public static void main(String[] args) {
        Connection con;//variable que almacenará los datos de la conexión
        PreparedStatement sentencia;//Objeto que permite representar consulta parametrizada
        Statement sent;//Objeto que permitirá procesar la sentencia SQL
        String sql;//Variable que almacenará la sentencia SQL
        Scanner sc = new Scanner(System.in);
        ResultSet rs;
        int numemp = 0;//Variable que almacenará la clave del último empleado de la BD
        int oficina;//Variable que almacenará la oficina

        String url = "jdbc:mysql://localhost:3306/Empresa";
        try {//Conectamos
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sent = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Primero vamos a realizar la consulta para determinar el último número de empleado
            sql = "SELECT numemp FROM empleados ORDER BY numemp DESC";
            rs = sent.executeQuery(sql);
            if (rs.first()) {
                numemp = rs.getInt("numemp");
            }
            con.close();//cerramos la conexión
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try{//Conectamos
                con = DriverManager.getConnection(url, "Pepe", "12345");

          //Formamos la consulta parametrizada
            sql = "INSERT INTO empleados (numemp, nombre, edad, oficina, puesto, contrato) "+
                    "VALUES (?, ?, ?, ?, ?, ?)";
            sentencia = con.prepareStatement(sql);
          //Vamos a solicitar todos los datos del nuevo empleado:
            /*System.out.println("Numero de empleado");
            int numemp = sc.nextInt();*/
            System.out.println("Nombre:");
            String nombre = sc.nextLine();
            System.out.println("Edad:");
            int edad = sc.nextInt();
            do{
                System.out.println("Oficina:");
                 oficina = sc.nextInt();
            }while (oficina!=11 && oficina!=12 && oficina!=13 && oficina!=21 && oficina!=22 &&
                    oficina!=23 && oficina!=24 && oficina!=31 && oficina!=32);

            System.out.println("Puesto:");sc.nextLine();
            String puesto = sc.nextLine();
           // Date contrato = Date.valueOf(LocalDate.now());//una forma de hacerlo
            //Date contrato = new Date();//otra forma de hacerlo

         //Asignamos los parámetros:
            sentencia.setInt(1, (numemp+1));
            sentencia.setString(2, nombre);
            sentencia.setInt(3, edad);
            sentencia.setInt(4, oficina);
            sentencia.setString(5, puesto);
          // sentencia.setDate(6, new java.sql.Date (contrato.getTime()));//si se usa otro objeto tipo java.util.Date
            sentencia.setDate(6, new java.sql.Date (System.currentTimeMillis()));//forma directa con sql Date
            sentencia.executeUpdate();
            con.close();//cerramos la conexión
            System.out.println("Se ha insertado un nuevo empleado.");
        }catch (SQLException e){
            System.out.println("Ha ocurrido algún error.");
        }
    }
}
