package PracticPaExam;

import java.time.LocalDate;
import java.util.Date;

public class Empleado {
    private final int TAMANYO = 30;
    private int numemp;
    private String nombre;
    private int edad;
    private int oficina;
    private String puesto;
    private Date contrato;

    public Empleado(int numemp) {
        this.numemp = numemp;
    }

    public Empleado(int numemp, String nombre, int edad, int oficina, String puesto) {
        this.numemp = numemp;
        setNombre(nombre);
        this.edad = edad;
        this.oficina = oficina;
        setPuesto(puesto);
        setContrato();
    }

    public Empleado(int numemp, String nombre, int edad, int oficina, String puesto, Date contrato) {
        this.numemp = numemp;
        setNombre(nombre);
        this.edad = edad;
        this.oficina = oficina;
        setPuesto(puesto);
        this.contrato = contrato;
    }

    public int getNumemp() {
        return numemp;
    }

    public void setNumemp(int numemp) {
        this.numemp = numemp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.substring(0, Math.min(nombre.length(), TAMANYO));
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getOficina() {
        return oficina;
    }

    public void setOficina(int oficina) {
        this.oficina = oficina;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto.substring(0, Math.min(puesto.length(), TAMANYO));
    }

    public Date getContrato() {
        return contrato;
    }

    public void setContrato() {
        this.contrato = java.sql.Date.valueOf(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Empleado{ Num.Emp.= " + numemp + ", nombre= " + nombre +
                ", edad= " + edad +", oficina= " + oficina +", puesto= " + puesto +
                ", contrato= " + contrato + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        Empleado otroEmpleado = (Empleado) o;
        return this.numemp == otroEmpleado.numemp;
    }


}
