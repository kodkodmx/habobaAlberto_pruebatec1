package ui;

import java.math.BigDecimal; //Usado para no perder precicion en la BD
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import logica.Empleado;
import static ui.InterfazUsuario.mostrarOpcionInvalida;
import static ui.InterfazUsuario.mostrarOpcionVacia;

public class CapturaDatos {
    private static final Scanner teclado = new Scanner(System.in);

    public static Empleado capturarEmpleado() {
        String nombre = capturarDatos("nombre");
        String apellido = capturarDatos("apellido");
        String cargo = capturarDatos("cargo");
        BigDecimal salario = capturarSalario();
        LocalDate fechaIngreso = capturarFechaIngreso();

        return new Empleado(0, nombre, apellido, cargo, salario, fechaIngreso);
    }

    private static String capturarDatos(String campo) {
        String nombre;
        boolean nombreValido = false;
        do {
            System.out.print("\nIngrese el " + campo + " del empleado: ");
            nombre = teclado.nextLine().toUpperCase();
            if (nombre.isEmpty()) {
                InterfazUsuario.mostrarOpcionVacia();
                System.out.println("\nEl " + campo + " no puede estar vacío. Intente nuevamente.");
            } else if (nombre.matches("^[a-zA-Z]+( [a-zA-Z]+)*$")) {
                nombreValido = true;
                System.out.println("\n" + campo + " ingresado correctamente: " + nombre);
            } else {
                InterfazUsuario.mostrarOpcionInvalida();
                System.out.println("\nEl " + campo + " solo puede incluir letras y un espacio separador entre palabras. Intente nuevamente.");
            }
        } while (!nombreValido);
        return nombre;
    }

    private static BigDecimal capturarSalario() {
        BigDecimal salario = null;
        boolean salarioValido = false;
        do {
            System.out.print("\nIngrese el salario del empleado (Menor a 100,000,000.00 sin \"$\" ni \",\" y con un máximo de 2 decimales): ");
            String entrada = teclado.nextLine(); //se toma como String para poder evaluar que no sea vacio
            if (entrada.isEmpty()) {
                InterfazUsuario.mostrarOpcionVacia();
                System.out.println("\nEl salario no puede estar vacío. Intente nuevamente.");
            } else {
                try {
                    salario = new BigDecimal(entrada);
                    if (salario.scale() > 2) {
                        InterfazUsuario.mostrarOpcionInvalida();
                        System.out.println("\nEl salario no puede tener más de 2 decimales. Intente nuevamente.");
                    } else if (salario.compareTo(BigDecimal.ZERO) <= 0) {
                        InterfazUsuario.mostrarOpcionInvalida();
                        System.out.println("\nEl salario debe ser mayor a 0. Intente nuevamente.");
                    } else if (salario.compareTo(new BigDecimal("100000000.00")) >= 0) {
                        InterfazUsuario.mostrarOpcionInvalida();
                        System.out.println("\nEl salario debe ser menor a 100,000,000.00. Intente nuevamente.");
                    } else {
                        salarioValido = true;
                        NumberFormat formatoNumero = NumberFormat.getNumberInstance(Locale.US);
                        String salarioFormateado = formatoNumero.format(salario);
                        System.out.println("\nSalario ingresado correctamente: $" + salarioFormateado);
                    }
                } catch (NumberFormatException e) {
                    InterfazUsuario.mostrarOpcionInvalida();
                    System.out.println("\nEl salario debe ser un número válido. Intente nuevamente.");
                }
            }
        } while (!salarioValido);
        return salario;
    }

    private static LocalDate capturarFechaIngreso() {
        LocalDate fechaIngreso = null;
        boolean fechaValida = false;
        do {
            System.out.print("\nIngrese la fecha de ingreso del empleado (dd-MM-yyyy): ");
            String fechaTexto = teclado.nextLine();
            if (fechaTexto.isEmpty()) {
                InterfazUsuario.mostrarOpcionVacia();
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaIngreso = LocalDate.parse(fechaTexto, formatter);
                fechaValida = true;
                System.out.println("\nFecha de ingreso ingresada correctamente: " + fechaIngreso);
            } catch (DateTimeParseException e) {
                InterfazUsuario.mostrarOpcionInvalida();
                System.out.println("\nFormato de fecha incorrecto. Use dd-MM-yyyy. Intente nuevamente.");
            }
        } while (!fechaValida);
        return fechaIngreso;
    }

    public static int capturarId(String accion) {
        int id = -1;
        boolean idValido = false;

        while (!idValido) {
            System.out.print("\nID del Empleado a " + accion + ": ");
            String entrada = teclado.nextLine();

            if (entrada.isEmpty()) {
                mostrarOpcionVacia();
            } else {
                try {
                    id = Integer.parseInt(entrada);
                    idValido = true;
                } catch (NumberFormatException e) {
                    mostrarOpcionInvalida();
                }
            }
        }

        return id;
    }

    public static String capturarCargo() {
        String cargo = "";
        boolean cargoValido = false;

        while (!cargoValido) {
            System.out.print("\nIngrese el cargo del empleado: ");
            cargo = teclado.nextLine();

            try {
                if (cargo.isEmpty()) {
                    throw new IllegalArgumentException("El cargo no puede estar vacío.");
                } else {
                    cargoValido = true;
                }
            } catch (IllegalArgumentException e) {
                mostrarOpcionVacia();
                System.out.println(e.getMessage());
            }
        }

        return cargo;
    }
}
