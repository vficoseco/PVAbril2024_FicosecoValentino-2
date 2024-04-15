package ar.edu.unju.edm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Cancha {
	@Id
    @NotNull(message = "codigo es nescesario")
    private Integer codigo;

    @NotBlank(message = "Capacidad es nescesario")
    private String Capacidad;

    @NotBlank(message = "horario1 es nescesario")
    private String horario1;
    
    @NotBlank(message = "precio es nescesario")
    private String precio;
    
    @NotNull(message = "tipo es nescesario")
    private String horario2;
    
    @NotNull(message = "tipo es nescesario")
    private String horario3;

    public Cancha(Integer codigo, String Capacidad, String horario1, String horario2, String precio,String horario3) {
        super();
        this.codigo = codigo;
        this.Capacidad = Capacidad;
        this.horario1 = horario1;
        this.horario2 = horario2;
        this.horario3 = horario3;
        this.precio = precio;
    }

    public Cancha() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.codigo = Codigo;
    }
    
    public String getprecio() {
        return precio;
    }

    public void setprecio(String precio) {
        this.precio = precio;
    }


    public String getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(String Capacidad) {
        this.Capacidad = Capacidad;
    }

    public String gethorario1() {
        return horario1;
    }

    public void sethorario1(String horario1) {
        this.horario1 = horario1;
    }

    public String gethorario2() {
        return horario2;
    }

    public void sethorario2(String horario2) {
        this.horario2 = horario2;
    }
    public String gethorario3() {
        return horario3;
    }

    public void sethorario3(String horario3) {
        this.horario3 = horario3;
    }
    }