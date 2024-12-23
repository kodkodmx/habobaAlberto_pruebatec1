package com.softek.habobaalberto_pruebatec1;

import java.util.List;
import logica.Empleado;
import persistencia.ControladoraPersistencia;
import static logica.GestorEmpleados.*;
import static ui.CapturaDatos.*;
import static ui.InterfazUsuario.*;

public class HabobaAlberto_pruebatec1 {

    public static void main(String[] args) {
        ControladoraPersistencia controlPersi = new ControladoraPersistencia();

        boolean salir = false;
        while (!salir) {
            int opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    Empleado nuevoEmpleado = capturarEmpleado();
                    crearEmpleado(nuevoEmpleado, controlPersi);
                    break;
                case 2:
                    List<Empleado> todos = consultarTodos(controlPersi);
                    mostrarEmpleados(todos);
                    break;
                case 3:
                    String cargo = capturarCargo();
                    List<Empleado> empleadosPorCargo = consultarPorCargo(cargo, controlPersi);
                    mostrarEmpleados(empleadosPorCargo);
                    break;
                case 4:
                    int idModificar = capturarId("modificar");
                    modificarEmpleado(idModificar, controlPersi);
                    break;
                case 5:
                    int idEliminar = capturarId("eliminar");
                    eliminarEmpleado(idEliminar, controlPersi);
                    break;
                case 6:
                    mostrarDespedida();
                    salir = true;
                    break;
                default:
                    mostrarOpcionInvalida();
            }
        }
    }
}
