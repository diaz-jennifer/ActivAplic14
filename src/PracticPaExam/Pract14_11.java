package PracticPaExam;

import java.sql.*;

public class Pract14_11 {
    public static void main(String[] args) {

        Connection con;
        Statement st;
        ResultSet rs;
        String url;
        String sql;

        url = "jdbc:mysql://localhost:3306/empresa";
        try {
            con =DriverManager.getConnection(url, "Pepe", "12345");
            sql = "SELECT * FROM Empleados";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Estos son los Empleados:");
            while(rs.next()){
                int numEmp = rs.getInt(1);
                String nombre = rs.getString(2);
                int edad = rs.getInt(3);
                int oficina = rs.getInt(4);
                String puesto = rs.getString(5);
                Date contrato = rs.getDate(6);
                System.out.println("Num.Emp.:"+numEmp+", Nombre: "+nombre+", edad: "+edad+
                        ", oficina: "+oficina+", puesto: "+puesto+", F.Contrato: "+contrato);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con el SGBD");
        }
    }
}
