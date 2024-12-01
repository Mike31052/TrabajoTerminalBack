package com.escom.impuestos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empresarial")
public class EmpresarialEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Long id_registro;

    @NotNull
    @Column(name = "id_usuario")
    private String idUsuario;

    private Double isr_final_a;

    private Double iva_final_a;

    private Double monto_deducir_m;

    private Double monto_deducir_a;

    private Double isr_final_m;

    private Double iva_final_m;

    private char tipo_dec;

    public Long getId_registro() {
        return id_registro;
    }

    public void setId_registro(Long id_registro) {
        this.id_registro = id_registro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getIsr_final_a() {
        return isr_final_a;
    }

    public void setIsr_final_a(Double isr_final_a) {
        this.isr_final_a = isr_final_a;
    }

    public Double getIva_final_a() {
        return iva_final_a;
    }

    public void setIva_final_a(Double iva_final_a) {
        this.iva_final_a = iva_final_a;
    }

    public Double getMonto_deducir_m() {
        return monto_deducir_m;
    }

    public void setMonto_deducir_m(Double monto_deducir_m) {
        this.monto_deducir_m = monto_deducir_m;
    }

    public Double getMonto_deducir_a() {
        return monto_deducir_a;
    }

    public void setMonto_deducir_a(Double monto_deducir_a) {
        this.monto_deducir_a = monto_deducir_a;
    }

    public Double getIsr_final_m() {
        return isr_final_m;
    }

    public void setIsr_final_m(Double isr_final_m) {
        this.isr_final_m = isr_final_m;
    }

    public Double getIva_final_m() {
        return iva_final_m;
    }

    public void setIva_final_m(Double iva_final_m) {
        this.iva_final_m = iva_final_m;
    }

    public char getTipo_dec() {
        return tipo_dec;
    }

    public void setTipo_dec(char tipo_dec) {
        this.tipo_dec = tipo_dec;
    }

}
