package com.escom.impuestos.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @NotNull
    private String id_usuario;

    @NotNull
    private String nombre;

    @NotNull
    private String correo;

    @NotNull
    private String contraseña;

    private Long edad;

    private Date fecha_creacion;

    private boolean enable;

    public String getId() {
        return id_usuario;
    }
    
    public void setId(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public Long getEdad() {
        return edad;
    }
    
    public void setEdad(Long edad) {
        this.edad = edad;
    }
    
    public Date getFecha_creacion() {
        return fecha_creacion;
    }
    
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public boolean isEnable() {
        return enable;
    }
    
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    
    
}
