package com.escom.impuestos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escom.impuestos.model.ResicoEntity;


@Repository
public interface ResicoRepository extends JpaRepository<ResicoEntity, Long>{
    List<ResicoEntity> findByIdUsuario(String idUsuario);
}
