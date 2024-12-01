package com.escom.impuestos.repository;

import com.escom.impuestos.model.SueldosEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SueldosRepository extends JpaRepository<SueldosEntity, Long>{
    List<SueldosEntity> findByIdUsuario(String idUsuario);

}
