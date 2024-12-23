package logica;

import java.util.ArrayList;
import persistencia.ControladoraPersistencia;
import ui.CapturaDatos;
import java.util.List;
import java.util.Scanner;
import ui.InterfazUsuario;

public class GestorEmpleados {

    public static void crearEmpleado(Empleado empleado, ControladoraPersistencia controlPersi) {
        controlPersi.crearEmpleado(empleado);
        System.out.println("\n                                  ****************** E M P L E A D O  A G R E G A D O  C O N  E X I T O  *******************");
    }

    public static List<Empleado> consultarTodos(ControladoraPersistencia controlPersi) {
        return controlPersi.traerTodos();
    }

    public static List<Empleado> consultarPorCargo(String cargo, ControladoraPersistencia controlPersi) {
        List<Empleado> todos = controlPersi.traerTodos();
        List<Empleado> empleadosPorCargo = new ArrayList<>();
        boolean cargoValido = false;

        for (Empleado empleado : todos) {
            if (empleado.getCargo().equalsIgnoreCase(cargo)) {
                empleadosPorCargo.add(empleado);
                cargoValido = true;
            }
        }

        if (!cargoValido) {
            InterfazUsuario.mostrarOpcionInvalida();
            System.out.println("\n                                  *********************** C A R G O  N O  E N C O N T R A D O ***********************");
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
                Scanner teclado = new Scanner(System.in);
                String modificar = teclado.nextLine();
                if (modificar.equalsIgnoreCase("S")) {
                    System.out.println("\nCapture los nuevos datos tal y como como desea que queden guardados.");
                    Empleado empleadoModificado = CapturaDatos.capturarEmpleado();
                    empleadoModificado.setId(id);
                    controlPersi.modificarEmpleado(empleadoModificado);
                    System.out.println("\n                                  ****************** E M P L E A D O  M O D I F I C A D O  C O N  E X I T O *******************");
                } else {
                    System.out.println("\n                                  ****************** O P E R A C I O N  D E  M O D I F I C A C I O N  C A N C E L A D A *******************");
                }
            }
        }
        if (!empleadoEncontrado) {
            InterfazUsuario.mostrarOpcionInvalida();
            System.out.println("\n                                  *********************** E M P L E A D O  C O N  I D  " + id + "  N O  E N C O N T R A D O ***********************");
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
                Scanner teclado = new Scanner(System.in);
                String eliminar = teclado.nextLine();
                if (eliminar.equalsIgnoreCase("S")) {
                    controlPersi.borrarEmpleado(id);
                    System.out.println("\n                                  *********************** E M P L E A D O  E L I M I N A D O  C O N  E X I T O *******************");
                } else {
                    System.out.println("\n                                  ****************** O P E R A C I O N  D E  E L I M I N A C I O N  C A N C E L A D A *******************");
                }
            }
        }
        if (!empleadoEncontrado) {
            InterfazUsuario.mostrarOpcionInvalida();
            System.out.println("\n                                  ****************** E M P L E A D O  I D  " + id + "  N O   E N C O N T R A D O ***********************");
        }
    }
}