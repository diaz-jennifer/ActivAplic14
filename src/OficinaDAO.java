/*Clase OficinaDAO con métodos estáticos para gestionar el objeto tipo Oficina*/
import java.sql.*;

public class OficinaDAO {

    //Crea una conexión con el SGBD y la devuelve.
    private static Connection conectar(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/Empresa";
        try{
            con = DriverManager.getConnection(url, "Pepe", "12345");
        }catch (SQLException e){
            System.out.println("Error al conectar con el SGBD");
        }
        return con;
    }
    //Método para leer un objeto oficina, y obtenerse sus datos, con la clave como parámetro
    public static Oficina read(int claveOficina){
        Oficina oficina = null;
        String sql = "SELECT * FROM Oficinas WHERE oficina = ?";
        try{
            Connection conexion = conectar();//Conectamos
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, claveOficina);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){//Si encuentra la clave, obtenemos los datos
                String ciudad = rs.getString("ciudad");
                int superficie = rs.getInt("superficie");
                double ventas = rs.getDouble("ventas");

                oficina = new Oficina(claveOficina, ciudad, superficie, ventas);//Formamos el objeto
            }
            conexion.close();//Cerramos la conexión
        }catch (SQLException e){
            System.out.println("Error al consultar oficina.");
        }
        return oficina;//si no encuentra la clave de la oficina devolverá null
    }

    //Método que actualiza los valores ciudad y ventas del objeto Oficina pasado como parámetro en la BD,
    //Supondremos que el objeto ya dispone de su registro. No se permite la modificación de la clave.
    public static void update(Oficina oficina){
        if(oficina != null){
            String sql = "UPDATE Oficinas SET ciudad =?, ventas=? WHERE oficina =?";
            try{
                Connection conexion = conectar();//Conectamos
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1, oficina.getCiudad());
                sentencia.setDouble(2, oficina.getVentas());
                sentencia.setInt(3,oficina.getClaveOficina());
                sentencia.executeUpdate();
                conexion.close();//Cerramos la conexión
                System.out.println("Se ha actualizado la oficina");
            }catch (SQLException e){
                System.out.println("Error al actualizar la oficina");
            }
        }
    }
}
