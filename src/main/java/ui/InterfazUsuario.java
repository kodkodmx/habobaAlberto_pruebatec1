package ui;

import logica.Empleado;
import java.util.List;
import java.util.Scanner;

public class InterfazUsuario {

    public static int mostrarMenu() {
        int opcion = 0;
        boolean opcionValida = true;
        Scanner teclado = new Scanner(System.in);

        while (opcionValida) {
            System.out.println("\n");
            System.out.println("+---------------------------------------------+");
            System.out.println("|   B I E N V E N I D O   A   N U E S T R O   |");
            System.out.println("|                                             |");
            System.out.println("|             S I S T E M A  D E              |");
            System.out.println("|                                             | ### ##     ####   ### ###  ###  ##  ### ###  ### ###  ###  ##    ####   ### ##    ## ##   ");
            System.out.println("|     G E S T I O N  D E  E M P L E A D O S   |  ##  ##     ##     ##  ##    ## ##   ##  ##   ##  ##    ## ##     ##     ##  ##  ##   ##  ");
            System.out.println("+---------------------------------------------+  ##  ##     ##     ##       # ## #   ##  ##   ##       # ## #     ##     ##  ##  ##   ##  ");
            System.out.println("|      1. Alta Empleado                       |  ## ##      ##     ## ##    ## ##    ##  ##   ## ##    ## ##      ##     ##  ##  ##   ##  ");
            System.out.println("|      2. Consultar Todos los Empleados       |  ##  ##     ##     ##       ##  ##   ### ##   ##       ##  ##     ##     ##  ##  ##   ##  ");
            System.out.println("|      3. Consultar Empleado por Cargo        | ### ##     ####   ### ###  ###  ##     ##    ### ###  ###  ##    ####   ### ##    ## ##   ");
            System.out.println("|      4. Modificar Empleado por ID           |");
            System.out.println("|      5. Baja Empleado por ID                |");
            System.out.println("|      6. Salir                               |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Selecciona una opcion: ");

            String entrada = teclado.nextLine();

            if (entrada.isEmpty()) {
                mostrarOpcionVacia();
            } else {
                try {
                    opcion = Integer.parseInt(entrada);
                    if (opcion >= 1 && opcion <= 6) {
                        opcionValida = false;
                    } else {
                        mostrarOpcionInvalida();
                    }
                } catch (NumberFormatException e) {
                    mostrarOpcionInvalida();
                }
            }
        }
        return opcion;
    }

    public static void mostrarEmpleados(List<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

    public static void mostrarDespedida() {
        System.out.println("                                                                                                             ");
        System.out.println("     ###  ##    ##      ## ##   #### ##    ##              ### ##   ### ##    ## ##   ###  ##  #### ##   ## ##   ");
        System.out.println("      ##  ##     ##    ##   ##  # ## ##     ##              ##  ##   ##  ##  ##   ##    ## ##  # ## ##  ##   ##  ");
        System.out.println("      ##  ##   ## ##   ####       ##      ## ##             ##  ##   ##  ##  ##   ##   # ## #    ##     ##   ##  ");
        System.out.println("      ## ###   ##  ##   #####     ##      ##  ##            ##  ##   ## ##   ##   ##   ## ##     ##     ##   ##  ");
        System.out.println("      ##  ##   ## ###      ###    ##      ## ###            ## ##    ## ##   ##   ##   ##  ##    ##     ##   ##  ");
        System.out.println("      ##  ##   ##  ##  ##   ##    ##      ##  ##            ##       ##  ##  ##   ##   ##  ##    ##     ##   ##    ##  ##  ##");
        System.out.println("     ###  ##  ###  ##   ## ##    ####    ###  ##           ####     #### ##   ## ##   ###  ##   ####     ## ##     ##  ##  ##");
        System.out.println("                                                                                                                  ");
    }

    public static void mostrarOpcionInvalida() {
        System.out.println("\n                                  *****************************  O P C I O N  I N V A L I D A  *****************************                ");
    }

    public static void mostrarOpcionVacia() {
        System.out.println("\n                                  *****************************  N O  P U E D E  E S T A R  V A C I O  *****************************                ");
    }
}
