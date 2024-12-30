/*Utilizando la BD Empleados, crea un programa que permita modificar la ciudad e
 incrementar las ventas de distintas oficinas. Utiliza una clase DAO para la oficina.
 Muestra los datos antes y después de la modificación.*/

import java.util.Locale;
import java.util.Scanner;

public class ActivAplic14_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        String ciudad;//variable ciudad que introducirá el usuario
        double incremento;//variable incremento de ventas que introducirá el usuario
        double ventas;//variable ventas
        int opcion;//variable introducida por el usuario, que representa su elección entre las opciones mostradas.
        Oficina oficinaActual;//objeto tipo Oficina que representará la actual en la BD
        do {
            System.out.println();
            System.out.println("A continuación indica qué quieres hacer");
            System.out.println("1. Modificar ciudad");
            System.out.println("2. Incrementar ventas");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion){
                case 1->{
                    //Llamada a la función para obtener el objeto Oficina actual
                    oficinaActual = getOficina();

                    if(oficinaActual!=null){//Si la oficina existe, preguntará por el cambio de ciudad a realizar
                        System.out.println(oficinaActual);
                        System.out.print("Escribe el nuevo nombre de la ciudad: ");
                        sc.nextLine();
                        ciudad = sc.nextLine();
                        //Creamos un objeto con los mismos datos del objeto actual, excepto por la ciudad, que es el que el usuario ha introducido
                        Oficina of = new Oficina(oficinaActual.getClaveOficina(), ciudad, oficinaActual.getSuperficie(), oficinaActual.getVentas() );
                        //Llamamos al método para actualizar la oficina en la BD.
                        OficinaDAO.update(of);
                        System.out.println();
                        System.out.println("Esta es la oficina actualizada:");
                        System.out.println(OficinaDAO.read(oficinaActual.getClaveOficina()));
                    }else{
                        System.out.println("No existe esa oficina");
                    }

                }
                case 2->{
                    //Llamada a la función para obtener el objeto Oficina actual
                    oficinaActual = getOficina();

                    if(oficinaActual!=null){//Si la oficina existe, preguntará por el incremento en las ventas a realizar
                        System.out.println(oficinaActual);
                        System.out.print("Escribe el importe del incremento de las ventas: ");
                        incremento = sc.nextDouble();
                        ventas = oficinaActual.getVentas() + incremento;
                        //Creamos un objeto con los mismos datos del objeto actual, excepto por las ventas.
                        Oficina of = new Oficina(oficinaActual.getClaveOficina(), oficinaActual.getCiudad(), oficinaActual.getSuperficie(), ventas);
                        //Llamamos al método para actualizar la oficina en la BD.
                        OficinaDAO.update(of);//Si las ventas con el incremento incluído no pasan las 8 cifras significativas, se actualizarán
                        System.out.println();
                        if(ventas<100000000) {//Si es el caso, mostrará la oficina actualizada
                            System.out.println("Esta es la oficina actualizada:");
                            System.out.println(OficinaDAO.read(oficinaActual.getClaveOficina()));
                        }else {//En caso contrario volverá a mostrar la misma sin cambios
                            System.out.println("No se pueden incrementar tanto las ventas.");
                            System.out.println("La oficina sigue siendo la misma:");
                            System.out.println(OficinaDAO.read(oficinaActual.getClaveOficina()));
                        }
                    }else{
                        System.out.println("No existe esa oficina");
                    }
                }

            }

        }while(opcion!=3);
    }
    public static Oficina getOficina(){//Función para obtener la oficina actual
        Scanner sc = new Scanner(System.in);
        int clave;//variable clave de oficina que indicará el usuario
        Oficina oficinaActual;
        System.out.println("Escribe el código de la oficina a actualizar: ");
        clave = sc.nextInt();
        System.out.println();
        System.out.println("Esta es la oficina seleccionada:");
        oficinaActual = OficinaDAO.read(clave);
        return oficinaActual;//devuelve el objeto tipo Oficina que hay en la BD.
    }
}
