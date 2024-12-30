package ActivAplic14_16;/*Clase Empleado */
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class Empleado {

    private final int TAMANYO=30;//especifica el tamaño máximo del nombre y del puesto,  que en la BD se ha definido como un VARCHAR (30)
    private int numEmpleado;
    private String nombre;
    private int edad;
    private int oficina;
    private String puesto;
    private Date contrato;

    //Constructor que crea un objeto Empleado únicamente con su identificador. Se utiliza
    //para leer los datos desde la BD a partir de la clave.
    public Empleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    //Constructor al que se le pasarán todos los datos menos la fecha de contrato
    public Empleado(int numEmpleado, String nombre, int edad, int oficina, String puesto) {
/*        if(setNumEmpleado(numEmpleado)!=0){
            this.numEmpleado = numEmpleado;
        }else{
            System.out.println("El número de empleado ya existe");
        }*/
        this.numEmpleado = numEmpleado;
        setNombre(nombre);
        this.edad = edad;
        if(setOficina(oficina)!=0) {
            this.oficina = oficina;
        }else{
            System.out.println("Error en la oficina");
        }
        setPuesto(puesto);
        setContrato();
    }
//Métodos para obtener las variables o para establecerlas
    public int getNumEmpleado() {
        return this.numEmpleado;
    }
   /*//Método para determinar el número de empleado a insertar
   public int setNumEmpleado(int num) {
      numEmpleado = 0;//Variable que almacenará la clave del empleado de la BD

        String url = "jdbc:mysql://localhost:3306/Empresa";
        try {//Conectamos
            Connection con = DriverManager.getConnection(url, "Pepe", "12345");
            Statement sent = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Primero vamos a realizar la consulta para determinar el último número de empleado
            String sql = "SELECT numemp FROM empleados ORDER BY numemp DESC";
            ResultSet rs = sent.executeQuery(sql);
            if (rs.first()) {
                numEmpleado = rs.getInt("numemp");
            }
            con.close();//cerramos la conexión
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (numEmpleado+1);//devuelve el siguiente número de empleado al crear un nuevo empleado


    }*/
 /*   public int setNumEmpleado(int numEmpleado) {

        String url = "jdbc:mysql://localhost:3306/Empresa";
        try {//Conectamos
            Connection con = DriverManager.getConnection(url, "Pepe", "12345");
            String sql = "SELECT * FROM empleados WHERE numemp = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, numEmpleado);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                this.numEmpleado = 0;
            }else{
                this.numEmpleado = numEmpleado;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.numEmpleado;
    }*/
    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {//nombre restringido a 30 caracteres
        this.nombre = nombre.substring(0,Math.min(TAMANYO,nombre.length()));
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getOficina() {
        return this.oficina;
    }
    public int setOficina(int oficina) {//establece la oficina, siempre que exista en la BD
        int ofic = 0;//Variable que almacenará la clave de la oficina de la BD

        String url = "jdbc:mysql://localhost:3306/Empresa";
        try {//Conectamos
            Connection con = DriverManager.getConnection(url, "Pepe", "12345");
        //Determinamos si la oficina existe
            /*  //una forma de hacerlo
            Statement sent = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM Oficinas WHERE oficina="+oficina;
            ResultSet rs = sent.executeQuery(sql);
            if (rs.next()) {
                ofic = rs.getInt("oficina");
                }*/
            //Otra forma de hacerlo con consulta parametrizada
            String sql = "SELECT * FROM Oficinas WHERE oficina=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,oficina);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ofic = rs.getInt("oficina");
            }
            con.close();//cerramos la conexión
        }catch (SQLException e){
            System.out.println("Esa oficina no existe.");
        }
        return ofic;

    }
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {//Método que establece el puesto con un máximo de 30 caracteres
        this.puesto = puesto.substring(0,Math.min(TAMANYO,puesto.length()));
    }
    public Date getContrato() {
        return contrato;
    }
    public void setContrato() {//Establece el contrato a fecha actual
        this.contrato = java.sql.Date.valueOf(LocalDate.now());
    }

    public String toString(){
        String resultado="Error en la oficina o en el número de empleado";
        if(this.oficina!=0 && this.numEmpleado!=0) {
            resultado = "Empleado { numEmp= " + this.numEmpleado + ", nombre = " + nombre + ", edad= " + edad + ", oficina= " + oficina + ", puesto= " + puesto + ", contrato= " + this.contrato + '}';
        }
        return resultado;
    }

    public boolean equals(Object otro){
        Empleado otroEmpleado = (Empleado) otro;
        return this.numEmpleado==otroEmpleado.numEmpleado;
    }
}
