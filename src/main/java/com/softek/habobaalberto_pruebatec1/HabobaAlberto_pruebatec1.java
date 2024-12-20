package com.softek.habobaalberto_pruebatec1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        boolean salir = false;
        
        while (!salir) {
                System.out.println("+---------------------------------------------+");
                System.out.println("|   B I E N V E N I D O   A   N U E S T R O   |");
                System.out.println("|                                             |");
                System.out.println("|             S I S T E M A  D E              |");
                System.out.println("|                                             |   ### ##     ####   ### ###  ###  ##  ### ###  ### ###  ###  ##    ####   ### ##    ## ##    ## ##   ");
                System.out.println("|     G E S T I O N  D E  E M P L E A D O S   |    ##  ##     ##     ##  ##    ## ##   ##  ##   ##  ##    ## ##     ##     ##  ##  ##   ##  ##   ##  ");
                System.out.println("+---------------------------------------------+    ##  ##     ##     ##       # ## #   ##  ##   ##       # ## #     ##     ##  ##  ##   ##  ####     ");
                System.out.println("|         1. Alta Empleado                    |    ## ##      ##     ## ##    ## ##    ##  ##   ## ##    ## ##      ##     ##  ##  ##   ##   #####   ");
                System.out.println("|         2. Consultar Todos los Empleados    |    ##  ##     ##     ##       ##  ##   ### ##   ##       ##  ##     ##     ##  ##  ##   ##      ###  ");
                System.out.println("|         3. Consultar un Empleado            |   ### ##     ####   ### ###  ###  ##     ##    ### ###  ###  ##    ####   ### ##    ## ##    ## ##   ");
                System.out.println("|         4. Modificar Empleado               |");
                System.out.println("|         5. Baja Empleado                    |");
                System.out.println("|         6. Salir                            |");
                System.out.println("+---------------------------------------------+");
                System.out.print("Selecciona una opcion: ");
                int opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        //nombre = "";
                        boolean nombreValido = false;
                        while (!nombreValido) {
                            System.out.print("Ingrese el nombre del empleado: ");
                            nombre = teclado.nextLine();

                            if (nombre.isEmpty()) {
                                System.out.println("El nombre no puede estar vacío. Intente nuevamente.");
                            } 
                            else if (nombre.matches("^[a-zA-Z]+$")) {
                                nombreValido = true;
                                System.out.println("Nombre ingresado correctamente: " + nombre);
                            } else {
                                System.out.println("El nombre solo puede incluir letras. Intente nuevamente.");
                            }
                        }

                        //apellido = "";
                        boolean apellidoValido = false;
                        while (!apellidoValido) {
                            System.out.print("Ingrese el apellido del empleado: ");
                            apellido = teclado.nextLine();

                            if (apellido.isEmpty()) {
                                System.out.println("El apellido no puede estar vacío. Intente nuevamente.");
                            } 
                            else if (apellido.matches("^[a-zA-Z]+$")) {
                                apellidoValido = true;
                                System.out.println("Apellido ingresado correctamente: " + apellido);
                            } else {
                                System.out.println("El apellido solo puede incluir letras. Intente nuevamente.");
                            }
                        }

                        //cargo = "";
                        boolean cargoValido = false;
                        while (!cargoValido) {
                            System.out.print("Ingrese el cargo del empleado: ");
                            cargo = teclado.nextLine();

                            if (cargo.isEmpty()) {
                                System.out.println("El cargo no puede estar vacío. Intente nuevamente.");
                            } else {
                                cargoValido = true;
                                System.out.println("Cargo ingresado correctamente: " + cargo);
                            }
                        }
                        boolean salarioValido = false;
                        while (!salarioValido) {
                            System.out.print("Ingrese el salario del empleado: ");
                            if (teclado.hasNextFloat()) {
                                salario = teclado.nextBigDecimal();
                                if (salario.compareTo(BigDecimal.ZERO) <= 0) {
                                    System.out.println("El salario debe ser mayor a 0. Intente nuevamente.");
                                } else {
                                    salarioValido = true;
                                    teclado.nextLine();
                                    System.out.println("Salario ingresado correctamente: " + salario);
                                }
                            } else {
                                System.out.println("El salario debe ser un número válido. Intente nuevamente.");
                                teclado.nextLine();
                            }
                        }                                                
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            System.out.print("Ingrese la fecha de ingreso del empleado (dd-MM-yyyy): ");
                            String fechaTexto = teclado.nextLine();

                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                fechaIngreso = LocalDate.parse(fechaTexto, formatter);
                                fechaValida = true;
                                System.out.println("Fecha de ingreso ingresada correctamente: " + fechaIngreso);
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de fecha incorrecto. Use dd-MM-yyyy. Intente nuevamente.");
                            }
                        }
                        ControladoraPersistencia controlPersi = new ControladoraPersistencia();                      
                        Empleado emp = new Empleado(1, nombre, apellido, cargo, salario, fechaIngreso);
                        //System.out.println(emp);
                        controlPersi.crearPersona(emp);
                        System.out.println("\n                                  ****************** E M P L E A D O  A G R E G A D O  C O N  E X I T O  *******************");
                        break;
                
                    case 2:
                        System.out.println("case 2");
                        break;

                    case 3:
                        System.out.println("case 3");
                        break;

                    case 4:
                        System.out.println("case 4");
                        break;

                    case 5:
                        System.out.println("case 5");
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
                        System.out.println("                                    *****************************  O P C I O N  I N V A L I D A  *****************************                ");              }
            }
    }
}
