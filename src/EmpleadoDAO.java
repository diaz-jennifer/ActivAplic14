/*Clase EmpleadoDAO*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoDAO {

    private Connection conexion;
    private final String USUARIO = "Pepe";
    private final String PASSWORD = "12345";
    private final String MAQUINA = "localhost:3306";
    private final String BD = "Empresa";

    //Constructor que crea la conexión, que se mantendrá abierta todo el tiempo
    public EmpleadoDAO() {
        conexion = conectar();
    }
    //Método para conectar con el SGBD
    private Connection conectar(){
        Connection con = null;
        String url = "jdbc:mysql://"+MAQUINA+"/"+BD;
        try{
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
        }catch (SQLException e){
            System.out.println("Error al conectar al SGBD");
        }
        return con;
    }


    //Método para Insertar un nuevo empleado en la BD
    public void create (Empleado empleado){
        if(empleado != null) {

            //Formamos la consulta parametrizada
            String sql = "INSERT INTO Empleados (numemp, nombre, edad, oficina, puesto, contrato) VALUES (?,?,?,?,?,?)";
            try {//Asignamos los parámetros:
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setInt(1, empleado.getNumEmpleado());
                ps.setString(2, empleado.getNombre());
                ps.setInt(3, empleado.getEdad());
                ps.setInt(4, empleado.getOficina());
                ps.setString(5, empleado.getPuesto());
                ps.setDate(6, new java.sql.Date(empleado.getContrato().getTime()));
                ps.executeUpdate();

                System.out.println("Se ha insertado el nuevo empleado correctamente.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void desconectar(){
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
