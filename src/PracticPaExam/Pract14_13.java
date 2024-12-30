package PracticPaExam;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Pract14_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Oficina[] listaOficinas = new Oficina[0];
        Oficina of;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql, url;
        String ciudad;

        System.out.println("Introduce la ciudad:");
        ciudad = sc.nextLine();
        url= "jdbc:mysql://localhost:3306/empresa";
        try {
            con = DriverManager.getConnection(url, "Pepe", "12345");
            sql = "SELECT oficina, superficie, ventas FROM Oficinas WHERE ciudad = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, ciudad);
            rs = ps.executeQuery();

            if(rs.next()){
                do{
                    int clave = rs.getInt(1);
                    int superficie = rs.getInt(2);
                    double ventas = rs.getDouble(3);
                    of = new Oficina(clave, ciudad, superficie, ventas);
                    listaOficinas = Arrays.copyOf(listaOficinas, listaOficinas.length + 1);
                    listaOficinas[listaOficinas.length - 1] = of;

                    }while(rs.next());
            }
            System.out.println("Listado de Oficinas en "+ciudad+":");
            System.out.println(Arrays.deepToString(listaOficinas));
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }


    }
}
