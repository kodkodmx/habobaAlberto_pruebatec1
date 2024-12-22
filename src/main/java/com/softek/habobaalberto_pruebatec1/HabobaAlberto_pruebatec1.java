package com.softek.habobaalberto_pruebatec1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import logica.Empleado;
import persistencia.ControladoraPersistencia;

public class HabobaAlberto_pruebatec1 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String nombre = null;
        String apellido = null;
        String cargo = null;
        BigDecimal salario = null;
        LocalDate fechaIngreso = null;
        String nuevoNombre = null;
        String nuevoApellido = null;
        String nuevoCargo = null;
        BigDecimal nuevoSalario = null;
        LocalDate nuevaFechaIngreso = null;
        boolean salir = false;
        boolean seleccionValida = false;
        int opcion = 0;

        List<Empleado> todos = new ArrayList<>();
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
                    boolean nombreValido = false;
                    while (!nombreValido) {
                        System.out.print("\nIngrese el nombre del empleado: ");
                        nombre = teclado.nextLine().toUpperCase();

                        if (nombre.isEmpty()) {
                            System.out.println("\nEl nombre no puede estar vacío. Intente nuevamente.");
                        } else if (nombre.matches("^[a-zA-Z]+$")) {
                            nombreValido = true;
                            System.out.println("\nNombre ingresado correctamente " + nombre);
                        } else {
                            System.out.println("\nEl nombre solo puede incluir letras. Intente nuevamente.");
                        }
                    }

                    boolean apellidoValido = false;
                    while (!apellidoValido) {
                        System.out.print("\nIngrese el apellido del empleado: ");
                        apellido = teclado.nextLine().toUpperCase();

                        if (apellido.isEmpty()) {
                            System.out.println("\nEl apellido no puede estar vacio. Intente nuevamente.");
                        } else if (apellido.matches("^[a-zA-Z]+$")) {
                            apellidoValido = true;
                            System.out.println("\nApellido ingresado correctamente " + apellido);
                        } else {
                            System.out.println("\nEl apellido solo puede incluir letras. Intente nuevamente.");
                        }
                    }
                    boolean cargoValido = false;
                    while (!cargoValido) {
                        System.out.print("\nIngrese el cargo del empleado: ");
                        cargo = teclado.nextLine().toUpperCase();

                        if (cargo.isEmpty()) {
                            System.out.println("\nEl cargo no puede estar vacio. Intente nuevamente.");
                        } else {
                            cargoValido = true;
                            System.out.println("\nCargo ingresado correctamente " + cargo);
                        }
                    }
                    boolean salarioValido = false;
                    while (!salarioValido) {
                        System.out.print("\nIngrese el salario del empleado (Sin \"$\" ni \",\" y maximo 2 decimales): ");
                        String entrada = teclado.nextLine();
                        if (entrada.isEmpty()) {
                            System.out.println("\nEl salario no puede estar vacio. Intente nuevamente.");
                        } else {
                            try {
                                salario = new BigDecimal(entrada);
                                if (salario.scale() > 2) {
                                    System.out.println("\nEl salario no puede tener mas de 2 decimales. Intente nuevamente.");
                                } else if (salario.compareTo(BigDecimal.ZERO) <= 0) {
                                    System.out.println("\nEl salario debe ser mayor a 0. Intente nuevamente.");
                                } else {
                                    salarioValido = true;
                                    NumberFormat formatoNumero = NumberFormat.getNumberInstance(Locale.US);
                                    String salarioFormateado = formatoNumero.format(salario);
                                    System.out.println("\nSalario ingresado correctamente: $" + salarioFormateado);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\nEl salario debe ser un numero valido. Intente nuevamente.");
                            }
                        }
                    }
                    boolean fechaValida = false;
                    while (!fechaValida) {
                        System.out.print("\nIngrese la fecha de ingreso del empleado (dd-MM-yyyy): ");
                        String fechaTexto = teclado.nextLine();

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            fechaIngreso = LocalDate.parse(fechaTexto, formatter);
                            fechaValida = true;
                            System.out.println("\nFecha de ingreso ingresada correctamente " + fechaIngreso);
                        } catch (DateTimeParseException e) {
                            System.out.println("\nFormato de fecha incorrecto. Use dd-MM-yyyy. Intente nuevamente.");
                        }
                    }

                    Empleado emp = new Empleado(1, nombre, apellido, cargo, salario, fechaIngreso);
                    controlPersi.crearEmpleado(emp);
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
                    cargoValido = false;
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
                    int requestedId = 0;
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
                                    boolean nuevoNombreValido = false;
                                    while (!nuevoNombreValido) {
                                        System.out.print("\nIngrese el nuevo nombre del empleado: ");
                                        nuevoNombre = teclado.nextLine().toUpperCase();
                                        if (nuevoNombre.isEmpty()) {
                                            System.out.println("El nombre no puede estar vacio. Intente nuevamente.");
                                        } else if (nuevoNombre.matches("^[a-zA-Z]+$")) {
                                            nuevoNombreValido = true;
                                            System.out.println("\nNuevo nombre: " + nuevoNombre);
                                        } else {
                                            System.out.println("\nEl nombre solo puede incluir letras. Intente nuevamente.");
                                        }
                                    }

                                    boolean nuevoApellidoValido = false;
                                    while (!nuevoApellidoValido) {
                                        System.out.print("\nIngrese el nuevo apellido del empleado: ");
                                        nuevoApellido = teclado.nextLine().toUpperCase();
                                        if (nuevoApellido.isEmpty()) {
                                            System.out.println("\nEl apellido no puede estar vacio. Intente nuevamente.");
                                        } else if (nuevoApellido.matches("^[a-zA-Z]+$")) {
                                            nuevoApellidoValido = true;
                                            System.out.println("\nNuevo apellido: " + nuevoApellido);
                                        } else {
                                            System.out.println("\nEl apellido solo puede incluir letras. Intente nuevamente.");
                                        }
                                    }

                                    boolean nuevoCargoValido = false;
                                    while (!nuevoCargoValido) {
                                        System.out.print("\nIngrese el nuevo cargo del empleado: ");
                                        nuevoCargo = teclado.nextLine().toUpperCase();
                                        if (nuevoCargo.isEmpty()) {
                                            System.out.println("\nEl cargo no puede estar vacio. Intente nuevamente.");
                                        } else {
                                            nuevoCargoValido = true;
                                            System.out.println("\nNuevo cargo: " + nuevoCargo);
                                        }
                                    }

                                    boolean nuevoSalarioValido = false;
                                    while (!nuevoSalarioValido) {
                                        System.out.print("\nIngrese el nuevo salario del empleado (Sin \"$\" ni \",\" y maximo 2 decimales): ");
                                        String entrada = teclado.nextLine();
                                        if (entrada.isEmpty()) {
                                            System.out.println("\nEl salario no puede estar vacio. Intente nuevamente.");
                                        } else {
                                            try {
                                                nuevoSalario = new BigDecimal(entrada);
                                                if (nuevoSalario.scale() > 2) {
                                                    System.out.println("\nEl salario no puede tener mas de 2 decimales. Intente nuevamente.");
                                                } else if (nuevoSalario.compareTo(BigDecimal.ZERO) <= 0) {
                                                    System.out.println("\nEl salario debe ser mayor a 0. Intente nuevamente.");
                                                } else {
                                                    nuevoSalarioValido = true;
                                                    NumberFormat nFormatoNumero = NumberFormat.getNumberInstance(Locale.US);
                                                    String nSalarioFormateado = nFormatoNumero.format(nuevoSalario);
                                                    System.out.println("\nSalario ingresado correctamente: $" + nSalarioFormateado);
                                                }
                                            } catch (NumberFormatException e) {
                                                System.out.println("\nEl salario debe ser un numero valido. Intente nuevamente.");
                                            }
                                        }
                                    }

                                    boolean nuevoFechaValida = false;
                                    while (!nuevoFechaValida) {
                                        System.out.print("\nIngrese la nueva fecha de ingreso del empleado (dd-MM-yyyy): ");
                                        String fechaTexto = teclado.nextLine();
                                        try {
                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                            fechaIngreso = LocalDate.parse(fechaTexto, formatter);
                                            nuevoFechaValida = true;
                                            System.out.println("\nNueva fecha de ingreso " + fechaIngreso);
                                        } catch (DateTimeParseException e) {
                                            System.out.println("\nFormato de fecha incorrecto. Use dd-MM-yyyy. Intente nuevamente.");
                                        }
                                    }

                                    Empleado empN = new Empleado(requestedId, nuevoNombre, nuevoApellido, nuevoCargo, nuevoSalario, fechaIngreso);
                                    controlPersi.modificarEmpleado(empN);
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
                    requestedId = 0;
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
                            System.out.println("\nEmpleado con ID " + requestedId + " no encontrado.");
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
