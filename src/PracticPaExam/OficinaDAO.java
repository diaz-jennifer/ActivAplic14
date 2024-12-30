package PracticPaExam;

import java.sql.*;
import java.util.Arrays;

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

    public static Oficina read(int clave){
        Oficina oficina = null;
        Connection con = conectar();
        String sql = "SELECT * FROM Oficinas WHERE oficina = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clave);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String ciudad = rs.getString("ciudad");
                int superficie = rs.getInt("superficie");
                double ventas = rs.getDouble("ventas");
                oficina = new Oficina(clave,ciudad, superficie, ventas);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar Oficina");
        }
        return oficina;
    }

    //Método que devuelve una lista (tabla) de oficinas
    public static Oficina[] muestraTodasOficinas(){
        Oficina[] listaOficinas = new Oficina[0];
        Oficina of;
        try {
            Connection con = conectar();
            String sql = "SELECT * FROM Oficinas";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int clave = rs.getInt(1);
                String ciudad = rs.getString(2);
                int superficie = rs.getInt(3);
                double ventas = rs.getDouble("ventas");
                of = new Oficina(clave, ciudad, superficie, ventas);
                listaOficinas = Arrays.copyOf(listaOficinas, listaOficinas.length+1);
                listaOficinas[listaOficinas.length-1]=of;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }
        return listaOficinas;
    }

    //Método que devuelve una lista (tabla) de oficinas
    public static Oficina[] muestraTodasOficinas(int superficie){
        Oficina[] listaOficinas = new Oficina[0];
        Oficina of;
        try {
            Connection con = conectar();
            String sql = "SELECT * FROM Oficinas WHERE superficie > ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, superficie);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int clave = rs.getInt(1);
                String ciudad = rs.getString(2);
                int superf = rs.getInt(3);
                double ventas = rs.getDouble("ventas");
                of = new Oficina(clave, ciudad, superf, ventas);
                listaOficinas = Arrays.copyOf(listaOficinas, listaOficinas.length+1);
                listaOficinas[listaOficinas.length-1]=of;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }
        return listaOficinas;
    }
}
