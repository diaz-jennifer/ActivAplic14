package PracticPaExam;

import java.sql.*;
import java.util.Arrays;

public class Pract14_12 {
    public static void main(String[] args) {
        Oficina[] listaOficinas = new Oficina[0];
        Oficina of;
        Connection con;
        Statement st;
        ResultSet rs;
        String sql, url;

        url= "jdbc:mysql://localhost:3306/empresa";
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "SELECT * FROM Oficinas";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                int clave = rs.getInt(1);
                String ciudad = rs.getString(2);
                int superficie = rs.getInt(3);
                double ventas = rs.getDouble("ventas");
                of = new Oficina(clave, ciudad, superficie, ventas);
                listaOficinas = Arrays.copyOf(listaOficinas, listaOficinas.length+1);
                listaOficinas[listaOficinas.length-1]=of;
            }
            System.out.println("Listado de Oficinas:");
            System.out.println(Arrays.deepToString(listaOficinas));
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }


    }
}
