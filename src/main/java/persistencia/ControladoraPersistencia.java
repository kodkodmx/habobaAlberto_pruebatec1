package persistencia;

import java.util.ArrayList;
import java.util.List;
import logica.Empleado;

public class ControladoraPersistencia {
    
  
    EmpleadoJpaController empJPA = new EmpleadoJpaController();
    
    public void crearEmpleado(Empleado emp) {
      empJPA.create(emp);
  }

    public List<Empleado> traerTodos(){
        return empJPA.findEmpleadoEntities();
    }
}
