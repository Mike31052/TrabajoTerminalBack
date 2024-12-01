package com.escom.impuestos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.impuestos.model.SueldosEntity;
import com.escom.impuestos.repository.SueldosRepository;

@Service
public class SueldosService {

    private static final Logger logger = LoggerFactory.getLogger(SueldosService.class);

    @Autowired
    private SueldosRepository sueldosRepository;

    public boolean addRegistro(SueldosEntity sueldosEntity){
        try {
            sueldosRepository.save(sueldosEntity);
            logger.info("Registro insertado exitosamente: {}", sueldosEntity);
            return true;
        } catch (Exception e) {
            logger.error("Error al insertar el registro: {}", e.getMessage(), e);
            return false;
        }
    }

    public List<SueldosEntity> getRegistrosPorUsuario(String idUsuario) {
        try {
            return sueldosRepository.findByIdUsuario(idUsuario);
        } catch (Exception e) {
            logger.error("Error al obtener registros para id_usuario {}: {}", idUsuario, e.getMessage(), e);
            throw e; // Se propaga la excepción al controlador.
        }
    }

    
}
