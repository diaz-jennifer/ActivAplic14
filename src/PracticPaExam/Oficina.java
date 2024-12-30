package PracticPaExam;

public class Oficina {
    private final double MAX_VENTAS = 100000000;
    private final int TAMANYO = 30;
    private int claveOficina;
    private String ciudad;
    private int superficie;
    private double ventas;

    public Oficina(int claveOficina) {
        this.claveOficina = claveOficina;
    }

    public Oficina(int claveOficina, String ciudad, int superficie, double ventas) {
        this.claveOficina = claveOficina;
        setCiudad(ciudad);
        this.superficie = superficie;
        setVentas(ventas);
    }

    public int getClaveOficina() {
        return claveOficina;
    }

    public void setClaveOficina(int claveOficina) {
        this.claveOficina = claveOficina;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad.substring(0, Math.min(ciudad.length(), TAMANYO));
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        if(ventas < MAX_VENTAS)
            this.ventas = Math.round(ventas*100)/100.0;
        else{
            System.out.println("Error. Valor de ventas no puede superar 8 cifras");
        }
    }

    @Override
    public String toString() {
        return "Oficina{ claveOficina= " + claveOficina +", ciudad= " + ciudad +
                ", superficie= " + superficie +", ventas= " + ventas + "}\n";
    }
}
