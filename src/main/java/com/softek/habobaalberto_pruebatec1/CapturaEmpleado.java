package com.softek.habobaalberto_pruebatec1;
//Necesario para no perde precicion en la BD
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import logica.Empleado;

public class CapturaEmpleado {
// private static final evitan errores ya que no se usa fuera de la clase, no se tiene que instanciar, y no se puede asignar a otra cosa.
    private static final Scanner teclado = new Scanner(System.in); 

    public static Empleado solicitarDatosEmpleado() {
        String nombre = solicitarNombre("nombre");
        String apellido = solicitarNombre("apellido");
        String cargo = solicitarNombre("cargo");
        BigDecimal salario = solicitarSalario();
        LocalDate fechaIngreso = solicitarFechaIngreso();

        return new Empleado(0, nombre, apellido, cargo, salario, fechaIngreso);
    }

    private static String solicitarNombre(String campo) {
        String nombre;
        boolean nombreValido = false;
        do {
            System.out.print("\nIngrese el " + campo + " del empleado: ");
            nombre = teclado.nextLine().toUpperCase();
            if (nombre.isEmpty()) {
                System.out.println("\nEl " + campo + " no puede estar vacío. Intente nuevamente.");
            } else if (nombre.matches("^[a-zA-Z]+$")) {
                nombreValido = true;
                System.out.println("\n" + campo + " ingresado correctamente: " + nombre);
            } else {
                System.out.println("\nEl " + campo + " solo puede incluir letras. Intente nuevamente.");
            }
        } while (!nombreValido);
        return nombre;
    }

    private static BigDecimal solicitarSalario() {
        BigDecimal salario = null;
        boolean salarioValido = false;
        do {
            System.out.print("\nIngrese el salario del empleado (Menor a 100,000,000.00 sin \"$\" ni \",\" y con un máximo de 2 decimales): ");
            String entrada = teclado.nextLine(); //se toma como String para poder evaluar que no sea vacio
            if (entrada.isEmpty()) {
                System.out.println("\nEl salario no puede estar vacío. Intente nuevamente.");
            } else {
                try {
                    salario = new BigDecimal(entrada);
                    if (salario.scale() > 2) {
                        System.out.println("\nEl salario no puede tener más de 2 decimales. Intente nuevamente.");
                    } else if (salario.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("\nEl salario debe ser mayor a 0. Intente nuevamente.");
                    } else if (salario.compareTo(new BigDecimal("100000000.00")) >= 0) {
                        System.out.println("\nEl salario debe ser menor a 100,000,000.00. Intente nuevamente.");
                    }else {
                        salarioValido = true;
                        NumberFormat formatoNumero = NumberFormat.getNumberInstance(Locale.US);
                        String salarioFormateado = formatoNumero.format(salario);
                        System.out.println("\nSalario ingresado correctamente: $" + salarioFormateado);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEl salario debe ser un número válido. Intente nuevamente.");
                }
            }
        } while (!salarioValido);
        return salario;
    }

    private static LocalDate solicitarFechaIngreso() {
        LocalDate fechaIngreso = null;
        boolean fechaValida = false;
        do {
            System.out.print("\nIngrese la fecha de ingreso del empleado (dd-MM-yyyy): ");
            String fechaTexto = teclado.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaIngreso = LocalDate.parse(fechaTexto, formatter);
                fechaValida = true;
                System.out.println("\nFecha de ingreso ingresada correctamente: " + fechaIngreso);
            } catch (DateTimeParseException e) {
                System.out.println("\nFormato de fecha incorrecto. Use dd-MM-yyyy. Intente nuevamente.");
            }
        } while (!fechaValida);
        return fechaIngreso;
    }
}