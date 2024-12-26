package logica;

import java.util.ArrayList;
import persistencia.ControladoraPersistencia;
import ui.CapturaDatos;
import java.util.List;
import java.util.Scanner;
import static ui.InterfazUsuario.mostrarOpcionInvalida;

public class GestorEmpleados {
    static Scanner teclado = new Scanner(System.in);
    
    public static void crearEmpleado(Empleado empleado, ControladoraPersistencia controlPersi) {
        List<Empleado> todos = controlPersi.traerTodos();
        boolean empleadoExiste = false;

        for (Empleado emp : todos) { //No se usa try and catch ya que verificar que un empleado ya exista no es en si una error o excepcion es mas bien parte del flujo normal.
            if (empleado.getNombre().equals(emp.getNombre()) &&
                empleado.getApellido().equals(emp.getApellido()) &&
                empleado.getCargo().equals(emp.getCargo()) &&
                empleado.getFechaInicio().equals(emp.getFechaInicio())) {
                empleadoExiste = true;
                break; // se justifica el uso de break para detener el ciclo for, una ves que se encuentra que el empleado ya existe.
            }
        }

        if (empleadoExiste) {
            mostrarOpcionInvalida();
            System.out.println("\n                                  *****************************  E M P L E A D O  Y A  E X I S T E  *****************************                ");
        } else {
            controlPersi.crearEmpleado(empleado);
            System.out.println("\n                                  *****************************  E M P L E A D O  A G R E G A D O  C O N  E X I T O  *****************************                ");
        }
    }


    public static List<Empleado> consultarTodos(ControladoraPersistencia controlPersi) {
        return controlPersi.traerTodos();
    }

    public static List<Empleado> consultarPorCargo(String cargo, ControladoraPersistencia controlPersi) {
        List<Empleado> todos = controlPersi.traerTodos();
        List<Empleado> empleadosPorCargo = new ArrayList<>();

        for (Empleado empleado : todos) {
            if (empleado.getCargo().equalsIgnoreCase(cargo)) {
                empleadosPorCargo.add(empleado);
            }
        }

        try { //Se implementa el try-catch para lanzar la excepción, porque así lo sugiere la consigna, aunque no es realmente necesario, ya que no se trata de una excepción en sí.
            if (empleadosPorCargo.isEmpty()) {
                throw new CargoNoEncontradoException("Ningun empleado actualmente tiene el cargo de " + cargo);
            }
        } catch (CargoNoEncontradoException e) {
            mostrarOpcionInvalida();
            System.out.println(e.getMessage());
        }

        return empleadosPorCargo;

    }

    public static void modificarEmpleado(int id, ControladoraPersistencia controlPersi) {
        List<Empleado> todos = controlPersi.traerTodos();
        boolean empleadoEncontrado = false;

        for (Empleado empleado : todos) {
            if (empleado.getId() == id) {
                empleadoEncontrado = true;
                System.out.println(empleado);
                System.out.print("\n¿Desea modificar este empleado? S/N: ");
                String modificar = teclado.nextLine();

                if (modificar.equalsIgnoreCase("S")) {
                    System.out.println("\nCapture los nuevos datos tal y como desea que queden guardados.");
                    Empleado empleadoModificado = CapturaDatos.capturarEmpleado();
                    empleadoModificado.setId(id);
                    controlPersi.modificarEmpleado(empleadoModificado);
                    System.out.println("\n                                  *****************************  E M P L E A D O  M O D I F I C A D O  C O N  E X I T O  *****************************                ");
                } else {
                    System.out.println("\n                                  *****************************  O P E R A C I O N  D E  M O D I F I C A C I O N  C A N C E L A D A  *****************************                ");
                }
                break; // Veo justificado su uso ya que el codigo sirve igual sin el break pero ahorra recursos al salir del ciclo una vez procesado el empleado
            }
        }

        if (!empleadoEncontrado) {
            try { //Se implementa el try-catch para lanzar la excepción, porque así lo sugiere la consigna, aunque no es realmente necesario, ya que no se trata de una excepción en sí.
                throw new IDNoEncontradoException("Ningún empleado actualmente tiene el ID " + id);
            } catch (IDNoEncontradoException e) {
                mostrarOpcionInvalida();
                System.out.println(e.getMessage());
            }
        }
    }




    public static void eliminarEmpleado(int id, ControladoraPersistencia controlPersi) {
        List<Empleado> todos = controlPersi.traerTodos();
        boolean empleadoEncontrado = false;
        for (Empleado empleado : todos) {
            if (empleado.getId() == id) {
                empleadoEncontrado = true;
                System.out.println(empleado);
                System.out.print("\n¿ESTA SEGURO QUE DESEA ELIMINAR A ESTE EMPLEADO? YA QUE ESTA ACCION ES IRREVERSIBLE S/N: ");
                String eliminar = teclado.nextLine();
                if (eliminar.equalsIgnoreCase("S")) {
                    controlPersi.borrarEmpleado(id);
                    System.out.println("\n                                  *****************************  E M P L E A D O  E L I M I N A D O  C O N  E X I T O  *****************************                ");
                } else {
                    System.out.println("\n                                  *****************************  O P E R A C I O N  D E  E L I M I N A C I O N  C A N C E L A D A  *****************************                ");
                }
            }
        }
        try{ //Se implementa el try-catch para lanzar la excepción, porque así lo sugiere la consigna, aunque no es realmente necesario, ya que no se trata de una excepción en sí.
            if(!empleadoEncontrado){
                throw new IDNoEncontradoException("Ningun empleado actualmente tiene el ID " + id);
            }
        }catch (IDNoEncontradoException e) { 
            mostrarOpcionInvalida();
            System.out.println(e.getMessage());
        }
    }

    public static class CargoNoEncontradoException extends RuntimeException {
        public CargoNoEncontradoException(String mensaje) {
            super(mensaje);
        }

    }
    public static class IDNoEncontradoException extends RuntimeException {
        public IDNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }
    
}
