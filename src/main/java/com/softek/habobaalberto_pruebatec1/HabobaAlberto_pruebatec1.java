package com.softek.habobaalberto_pruebatec1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import logica.Empleado;
import persistencia.ControladoraPersistencia;

public class HabobaAlberto_pruebatec1 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String cargo;
        boolean salir = false;
        int opcion = 0;
        int requestedId;
        List<Empleado> todos= new ArrayList<>();
        
        ControladoraPersistencia controlPersi = new ControladoraPersistencia();

        while (!salir) {
            System.out.println("\n+---------------------------------------------+");
            System.out.println("|   B I E N V E N I D O   A   N U E S T R O   |");
            System.out.println("|                                             |");
            System.out.println("|             S I S T E M A  D E              |");
            System.out.println("|                                             |   ### ##     ####   ### ###  ###  ##  ### ###  ### ###  ###  ##    ####   ### ##    ## ##    ## ##   ");
            System.out.println("|     G E S T I O N  D E  E M P L E A D O S   |    ##  ##     ##     ##  ##    ## ##   ##  ##   ##  ##    ## ##     ##     ##  ##  ##   ##  ##   ##  ");
            System.out.println("+---------------------------------------------+    ##  ##     ##     ##       # ## #   ##  ##   ##       # ## #     ##     ##  ##  ##   ##  ####     ");
            System.out.println("|      1. Alta Empleado                       |    ## ##      ##     ## ##    ## ##    ##  ##   ## ##    ## ##      ##     ##  ##  ##   ##   #####   ");
            System.out.println("|      2. Consultar Todos los Empleados       |    ##  ##     ##     ##       ##  ##   ### ##   ##       ##  ##     ##     ##  ##  ##   ##      ###  ");
            System.out.println("|      3. Consultar Empleado por Cargo        |   ### ##     ####   ### ###  ###  ##     ##    ### ###  ###  ##    ####   ### ##    ## ##    ## ##   ");
            System.out.println("|      4. Modificar Empleado por ID           |");
            System.out.println("|      5. Baja Empleado por ID                |");
            System.out.println("|      6. Salir                               |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Selecciona una opcion: ");

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (Exception e) {
                teclado.next();
            }

            switch (opcion) {
                case 1:
                    Empleado nuevoEmpleado = CapturaEmpleado.solicitarDatosEmpleado(); //se creo clase y metodo aparte ya que se usa un par de veces.
                    controlPersi.crearEmpleado(nuevoEmpleado);
                    System.out.println("\n                                  ****************** E M P L E A D O  A G R E G A D O  C O N  E X I T O  *******************");
                    opcion = 0;
                    break;

                case 2:
                    todos = controlPersi.traerTodos();
                    for (Empleado empleado : todos) {
                        System.out.println(empleado);
                    }
                    opcion = 0;
                    break;

                case 3:
                    todos = controlPersi.traerTodos();
                    boolean cargoValido = false;
                    while (!cargoValido) {
                        System.out.print("\nIngrese el cargo del empleado: ");
                        cargo = teclado.nextLine();

                        if (cargo.isEmpty()) {
                            System.out.println("\nEl cargo no puede estar vacio. Intente nuevamente.");
                        } else {
                            for (Empleado empleado : todos) {
                                if (empleado.getCargo().equalsIgnoreCase(cargo)) {
                                    System.out.println(empleado);
                                    cargoValido = true;
                                }
                            }
                            if (!cargoValido) {
                                System.out.println("\n                                  *********************** C A R G O  N O  E N C O N T R A D O ***********************");
                                cargoValido = true;
                            }
                        }
                    }
                    opcion = 0;
                    break;

                case 4:
                    todos = controlPersi.traerTodos();                    
                    System.out.print("\nID del Empleado a modificar: ");
                    try {
                        requestedId = teclado.nextInt();
                        teclado.nextLine(); 
                        boolean empleadoEncontrado = false;
                        for (Empleado empleado : todos) {
                            if (empleado.getId() == requestedId) {
                                empleadoEncontrado = true;
                                System.out.println(empleado);
                                System.out.print("\n¿Desea modificar este empleado? S/N: ");
                                String modificar = teclado.nextLine();
                                if (modificar.equalsIgnoreCase("S")) {
                                    Empleado empleadoModificado = CapturaEmpleado.solicitarDatosEmpleado();
                                    empleadoModificado.setId(requestedId);
                                    controlPersi.modificarEmpleado(empleadoModificado);
                                    System.out.println("\n                                  ****************** E M P L E A D O  M O D I F I C A D O  C O N  E X I T O *******************");
                                }else {
                                    System.out.println("\n                                  ****************** O P E R A C I O N  D E  M O D I F I C A C I O N  C A N C E L A D A *******************");
                                }                               
                            }
                        }
                        if (!empleadoEncontrado) {
                            System.out.println("\n                                  *********************** E M P L E A D O  C O N  I D  " + requestedId + "  N O  E N C O N T R A D O ***********************");
                        }
                    } catch (Exception e) {
                        System.out.println("\n                                  *********************** I D  I N C O R R E C T A ***********************");
                        teclado.nextLine();
                    }

                    opcion = 0;
                    break;
                case 5:
                    todos = controlPersi.traerTodos();
                    System.out.print("\nID del Empleado a ELIMINAR: ");
                    try {
                        requestedId = teclado.nextInt();
                        teclado.nextLine();
                        boolean empleadoEncontrado = false;
                        
                        for (Empleado empleado : todos) {
                            if (empleado.getId() == requestedId) {
                                empleadoEncontrado = true;
                                System.out.println(empleado);
                                System.out.print("\n¿ESTA SEGURO QUE DESEA ELIMINAR A ESTE EMPLEADO? YA QUE ESTA ACCION ES IRREVERSIBLE S/N: ");
                                String eliminar = teclado.nextLine();
                                if (eliminar.equalsIgnoreCase("S")) {
                                    controlPersi.borrarEmpleado(requestedId);
                                    System.out.println("\n                                  *********************** E M P L E A D O  E L I M I N A D O  C O N  E X I T O *******************");
                                } else {
                                    System.out.println("\n                                  ****************** O P E R A C I O N  D E  E L I M I N A C I O N  C A N C E L A D A *******************");
                                }
                            }
                        }
                        if (!empleadoEncontrado) {
                            System.out.println("\n                                  ****************** E M P L E A D O  I D  " + requestedId + "  N O   E N C O N T R A D O ***********************");
                        }
                    } catch (Exception e) {
                        System.out.println("\n                                  *********************** I D  I N C O R R E C T A ***********************");
                        teclado.nextLine();
                    }
                    opcion = 0;
                    break;

                case 6:
                    System.out.println("                                                                                                             ");
                    System.out.println("     ###  ##    ##      ## ##   #### ##    ##              ### ##   ### ##    ## ##   ###  ##  #### ##   ## ##   ");
                    System.out.println("      ##  ##     ##    ##   ##  # ## ##     ##              ##  ##   ##  ##  ##   ##    ## ##  # ## ##  ##   ##  ");
                    System.out.println("      ##  ##   ## ##   ####       ##      ## ##             ##  ##   ##  ##  ##   ##   # ## #    ##     ##   ##  ");
                    System.out.println("      ## ###   ##  ##   #####     ##      ##  ##            ##  ##   ## ##   ##   ##   ## ##     ##     ##   ##  ");
                    System.out.println("      ##  ##   ## ###      ###    ##      ## ###            ## ##    ## ##   ##   ##   ##  ##    ##     ##   ##  ");
                    System.out.println("      ##  ##   ##  ##  ##   ##    ##      ##  ##            ##       ##  ##  ##   ##   ##  ##    ##     ##   ##    ##  ##  ##");
                    System.out.println("     ###  ##  ###  ##   ## ##    ####    ###  ##           ####     #### ##   ## ##   ###  ##   ####     ## ##     ##  ##  ##");
                    System.out.println("                                                                                                                  ");
                    salir = true;
                    break;

                default:
                    System.out.println("\n                                  *****************************  O P C I O N  I N V A L I D A  *****************************                ");
            }
        }
    }
}
