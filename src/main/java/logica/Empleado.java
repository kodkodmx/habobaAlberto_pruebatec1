package logica;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    @Column(precision = 10, scale = 2) // Necesario especificar los parametros para crear la columna para evitar perdida de presicion.
    private BigDecimal salario;
    private LocalDate fechaIngreso;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellido, String cargo, BigDecimal salario, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getFechaInicio() {
        return fechaIngreso;
    }

    public void setFechaInicio(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        String salarioFormateado = String.format("%,.2f", salario.doubleValue()); //formato con ',' para separar miles y 2 decimales.
        StringBuilder sb = new StringBuilder();
        sb.append("\n * ");
        sb.append("El Empleado con el ID ").append(id);
        sb.append(" se llama ").append(nombre).append(" ").append(apellido);
        sb.append(" con un cargo de ").append(cargo);
        sb.append(" y percibe un salario de $").append(salarioFormateado);
        sb.append(" desde el ").append(fechaIngreso);
        return sb.toString();
    }
}
