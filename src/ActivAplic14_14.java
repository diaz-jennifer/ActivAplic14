/*Utilizando la BD Empleados, crea un programa que muestre un listado con el nombre y
la edad de los empleados, cuya edad se encuentra comprendida entre unos valores mínimo
y máximo que introducirá el usuario.*/
import java.sql.*;
import java.util.Scanner;

public class ActivAplic14_14 {
    public static void main(String[] args) {
        Connection con;//variable que almacenará los datos de la conexión
        //Statement sentencia;//Objeto que permitirá procesar la sentencia SQL
        PreparedStatement sentencia;//Objeto que permite representar consulta parametrizada
        String sql;//Variable que almacenará la sentencia SQL
        ResultSet rs;//Objeto que almacenará los resultados de la sentencia SQL
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/Empresa";
/*        try{
        //Conectamos la aplicación con el SGBD
            con = DriverManager.getConnection(url, "Pepe", "12345");
        //Solicitamos las edades:
            System.out.println("Escriba el rango de edades:");
            System.out.print("Edad mínima: ");
            int edadMinima = sc.nextInt(); // variable que almacenará la edad mínima
            System.out.print("Edad máxima: ");
            int edadMaxima = sc.nextInt(); // variable que almacenará la edad máxima

            sentencia = con.createStatement();

        //Ejecutamos sentencia SQL que muestra los empleados y su edad, comprendidos entre las edades indicadas por el usuario
            sql = "SELECT nombre, edad FROM empleados WHERE edad BETWEEN "+edadMinima+" AND "+edadMaxima;
            rs = sentencia.executeQuery(sql);//obtenemos los resultado de la consulta

        //Ahora imprimimos los resultados:
            System.out.println("Lista de empleados entre "+edadMinima+" y "+ edadMaxima+":");
            while(rs.next()){//recorremos cada fila
                String nombre = rs.getString("nombre");//almacena el nombre del empleado
                int edad = rs.getInt("edad");//almacena la edad del empleado

                System.out.println("Nombre: "+nombre+"\tEdad: "+edad);
            }//cuando ya no haya filas saldrá del while
            con.close();//cerramos la conexión.

        }catch (SQLException ex) {//capturamos cualquier excepción SQL y mostramos mensaje
            System.out.println("Ha ocurrido algún error. No ha conectado con la SGBD");
        }*/

 //Otra forma de hacerlo:

        try {
            //Conectamos la aplicación con el SGBD
            con = DriverManager.getConnection(url, "Pepe", "12345");
            //Formamos la consulta:
            sql = "SELECT nombre, edad FROM empleados WHERE edad BETWEEN ? AND ?";
            sentencia = con.prepareStatement(sql);
            //Ahora pedimos las edades:
            System.out.println("Escriba el rango de edades:");
            System.out.print("Edad mínima: ");
            int edadMinima = sc.nextInt(); // variable que almacenará la edad mínima
            System.out.print("Edad máxima: ");
            int edadMaxima = sc.nextInt(); // variable que almacenará la edad máxima

            //Asignamos los parámetros
            sentencia.setInt(1, edadMinima);
            sentencia.setInt(2, edadMaxima);

            rs = sentencia.executeQuery();
            while(rs.next()){//recorremos cada fila
                String nombre = rs.getString("nombre");//almacena el nombre del empleado
                int edad = rs.getInt("edad");//almacena la edad del empleado
                //Imprimimos:
                System.out.println("Nombre: "+nombre+"\tEdad: "+edad);
            }//cuando ya no haya filas saldrá del while
            con.close();//cerramos la conexión.
        }catch (SQLException e){
            System.out.println("Ha ocurrido algún error.");

        }
    }
}
