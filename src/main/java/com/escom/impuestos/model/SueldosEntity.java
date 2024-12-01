package com.escom.impuestos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sueldos")
public class SueldosEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Long id_registro;

    @NotNull
    @Column(name = "id_usuario")
    private String idUsuario;

    private Double ingresos_acumulables;

    private Double deducciones_personales;

    private Double base_gravable;

    private Double isr_retenido;

    private Double isr_resultante;

    public Long getId_registro() {
        return id_registro;
    }

    public void setId_registro(Long id_registro) {
        this.id_registro = id_registro;
    }

    public String getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(String id_usuario) {
        this.idUsuario = id_usuario;
    }

    public Double getIngresos_acumulables() {
        return ingresos_acumulables;
    }

    public void setIngresos_acumulables(Double ingresos_acumulables) {
        this.ingresos_acumulables = ingresos_acumulables;
    }

    public Double getDeducciones_personales() {
        return deducciones_personales;
    }

    public void setDeducciones_personales(Double deducciones_personales) {
        this.deducciones_personales = deducciones_personales;
    }

    public Double getBase_gravable() {
        return base_gravable;
    }

    public void setBase_gravable(Double base_gravable) {
        this.base_gravable = base_gravable;
    }

    public Double getIsr_retenido() {
        return isr_retenido;
    }

    public void setIsr_retenido(Double isr_retenido) {
        this.isr_retenido = isr_retenido;
    }

    public Double getIsr_resultante() {
        return isr_resultante;
    }

    public void setIsr_resultante(Double isr_resultante) {
        this.isr_resultante = isr_resultante;
    }
}
