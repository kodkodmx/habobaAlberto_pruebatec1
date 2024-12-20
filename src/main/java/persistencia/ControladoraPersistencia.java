package persistencia;

import logica.Empleado;

public class ControladoraPersistencia {
    
  
    EmpleadoJpaController empJPA = new EmpleadoJpaController();
    
    public void crearPersona(Empleado emp) {
      empJPA.create(emp);
  }

    
}
