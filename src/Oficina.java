/*Clase Oficina*/
public class Oficina {
    private final int TAM_CIUDAD = 30;// tamaño máximo del nombre de la ciudad
    private final double NUMERO_MAX = 100000000.0;//tamaño máximo de las cifras significativas de ventas excluido
    private int claveOficina;//atributo clave de la oficina
    private String ciudad;//atributo nombre de la ciudad
    private int superficie;//atributo superficie
    private double ventas;//atributo ventas

    //Constructor para leer datos desde BD a partir de la clave
    public Oficina(int claveOficina){
        this.claveOficina = claveOficina;
    }
    //Constructor
    public Oficina(int claveOficina, String ciudad, int superficie, double ventas){
        this.claveOficina = claveOficina;
        setCiudad(ciudad);
        this.superficie = superficie;
        this.setVentas(ventas);
    }
    //Métodos para obtener y establecer los atributos
    public int getClaveOficina() {
        return claveOficina;
    }
    public void setClaveOficina(int claveOficina) {
        this.claveOficina = claveOficina;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {//Restringimos a un máximo de 30 caracteres
        this.ciudad = ciudad.substring(0, Math.min(ciudad.length(), TAM_CIUDAD));
    }
    public int getSuperficie() {
        return superficie;
    }
    public void setSuperficie(int superficie) {
        this.superficie=superficie;
    }
    public double getVentas() {
        return ventas;
    }
    public void setVentas(double ventas) {
        if(ventas < NUMERO_MAX)
            this.ventas = Math.round((ventas+0.005)*100)/100.0;//Redondeamos a 2 dígitos decimales.
        else{
            System.out.println("Error en las cifras. Las ventas no pueden exceder de 8 cifras");
        }

    }
    //Método para mostrar los datos del objeto Oficina
    public String toString(){
        return "Oficina {clave = "+claveOficina+", ciudad= "+ciudad+", superficie= "+superficie+
                ", ventas = "+ventas+"}";
    }
}
