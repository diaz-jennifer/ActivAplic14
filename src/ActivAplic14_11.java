/*Crea un programa que muestre todos los empleados de la BD Empresa.*/
import java.sql.*;

public class ActivAplic14_11 {
    public static void main(String[] args) {
        Connection con;//variable que almacenará los datos de la conexión
        Statement sentencia;//Objeto que permitirá procesar la sentencia SQL
        String sql;//Variable que almacenará la sentencia SQL
        ResultSet rs;//Objeto que almacenará los resultados de la sentencia SQL

        String url = "jdbc:mysql://localhost:3306/Empresa";
        try{
        //Conectamos la aplicación con el SGBD
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sentencia = con.createStatement();

        //Ejecutamos sentencia SQL que muestra todos los empleados
            sql = "SELECT * FROM Empleados";
            rs = sentencia.executeQuery(sql);//obtenemos los resultado de la consulta

        //Ahora imprimimos los resultados:
            System.out.println("Lista de empleados:");
            while(rs.next()){//recorremos cada fila
                int numemp = rs.getInt("numemp");//almacena el número identificativo del empleado
                String nombre = rs.getString("nombre");//almacena el nombre del empleado
                int edad = rs.getInt("edad");//almacena la edad del empleado
                int oficina = rs.getInt("oficina");//almacena la clave de oficina del empleado
                String puesto = rs.getString("puesto");//almacena el puesto del empleado
                Date contrato = rs.getDate("contrato");//almacena la fecha en la que se contrató al empleado
                System.out.println("Num. empleado: "+ numemp+"\tNombre: "+nombre+
                        "\tEdad: "+edad+"\tOficina: "+oficina+"\tPuesto: "+puesto+
                        "\tContrato: "+contrato);
            }//cuando ya no haya filas saldrá del while
            con.close();//cerramos la conexión.

        }catch (SQLException ex) {//capturamos cualquier excepción SQL y mostramos mensaje
            System.out.println("Ha ocurrido algún error. No ha conectado con la SGBD");
        }
    }
}
