package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    
  
    EmpleadoJpaController empJPA = new EmpleadoJpaController();
    
    public void crearEmpleado(Empleado emp) {
      empJPA.create(emp);
  }

    public List<Empleado> traerTodos(){
        return empJPA.findEmpleadoEntities();
    }
   
    public void modificarEmpleado(Empleado emp){
        try {
            empJPA.edit(emp);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarEmpleado(int id){
        try {
            empJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
