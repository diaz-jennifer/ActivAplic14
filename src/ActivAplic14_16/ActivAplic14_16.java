package ActivAplic14_16;/*Utilizando la BD Empleados, modifica la actividad 14.15 para que el programa
utilice mapeo objeto-relacional, para insertar los datos de un empleado, creando
previamente un objeto de tipo Empleado. Utiliza un objeto tipo DAO para la inserción,
no hace falta implementar otros métodos DAO. El programa controlará si el empleado a
insertar ya existe en la BD, (campo a controlar "numemp")*/


import java.sql.*;
import java.util.Scanner;

public class ActivAplic14_16 {
    public static void main(String[] args) {
/*    //  Para probar cuando el usuario no introduce el número de empleado
 Empleado empleado = new Empleado("Pepe Pepito Pepón Pompom", 30, 12, "Auxiliar administrativo");
        EmpleadoDAO.create(empleado);
        System.out.println(empleado);
//Para probar que el límite máximo del nombre funciona
        Empleado empleado2 = new Empleado("Mamimu Mamimu Mamumi Moomooo Muuuuuuuuuuu", 40, 13, "Gerente");
        EmpleadoDAO.create(empleado2);
        System.out.println(empleado2);*/
/*
//Para probar que el empleado no existe pero que hay un error en la oficina
        System.out.println("Empleado3:");
        Empleado empleado3 = new Empleado(123, "Roma Romero", 40, 15, "Gerente");
        EmpleadoDAO.create(empleado3);
//Para probar que el empleado ya existe, sin error en la oficina
        System.out.println("Empleado4:");
        Empleado empleado4 = new Empleado(120, "Roma Romero", 40, 13, "Gerente");
        EmpleadoDAO.create(empleado4);
//Para probar que el límite máximo del nombre funciona, empleado no existe, oficina existe
        System.out.println("Empleado5:");
        Empleado empleado5 = new Empleado(124,"Tantan Tintin Tonton Tuntun Tetetetetetetetete", 40, 13, "Gerente");
        EmpleadoDAO.create(empleado5);
*/
        Scanner sc = new Scanner(System.in);
        int num;//Variable que almacena el número de empleado
        String nom;//Variable que almacena el nombre del empleado
        int edad;//Variable que almacena la edad del empleado
        int oficina;//Variable que almacena la oficina del empleado
        String puesto;//Variable que almacena el puesto del empleado


        System.out.println("Escribe los datos del empleado a continuación:");
        System.out.print("Número de empleado: ");
        num = sc.nextInt();
        System.out.print("Nombre: ");sc.nextLine();
        nom = sc.nextLine();
        System.out.print("Edad: ");
        edad = sc.nextInt();
        System.out.print("Oficina: ");
        oficina = sc.nextInt();
        System.out.print("Puesto: ");sc.nextLine();
        puesto = sc.nextLine();
        Empleado empleado =new Empleado(num, nom, edad, oficina, puesto);//genera el objeto empleado

        //Vamos a determinar si el número de empleado ya existe
        Empleado empledoYaRegistrado =null;//Objeto empleado que ya se encuentra registrado

            int numEmpleado;
            String url = "jdbc:mysql://localhost:3306/Empresa";
            try {//Conectamos
                Connection con = DriverManager.getConnection(url, "Pepe", "12345");
                //Consultamos
                String sql = "SELECT * FROM empleados WHERE numemp = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, num);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {//Si existe
                    numEmpleado = rs.getInt("numemp");//no se requiere obtener los demás datos
                    empledoYaRegistrado = new Empleado(numEmpleado);
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            if (empledoYaRegistrado==null){//Para que se pueda añadir el empleado, el número no debe existir, y por tanto el objeto debe ser null
               // EmpleadoDAO.create(empleado);//Método estático
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();//Conectamos con la BD
                empleadoDAO.create(empleado);//Insertamos nuevo empleado
                empleadoDAO.desconectar();//desconectamos
            } else {
                System.out.println("No se puede insertar ese empleado. El número de empleado ya se encuentra registrado.");

            }


    }

}




