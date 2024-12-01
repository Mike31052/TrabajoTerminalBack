package com.escom.impuestos.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.escom.impuestos.model.EmpresarialEntity;


@Repository
public interface EmpresarialRepository extends JpaRepository<EmpresarialEntity, Long>{
    List<EmpresarialEntity> findByIdUsuario(String idUsuario);
}
