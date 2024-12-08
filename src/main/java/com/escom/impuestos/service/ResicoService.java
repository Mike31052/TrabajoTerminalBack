package com.escom.impuestos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.impuestos.model.ResicoEntity;
import com.escom.impuestos.repository.ResicoRepository;


@Service
public class ResicoService {

    private static final Logger logger = LoggerFactory.getLogger(ResicoService.class);

    @Autowired
    private ResicoRepository resicoRepository;

    public boolean addRegistro(ResicoEntity resicoEntity){
        try {
            resicoRepository.save(resicoEntity);
            logger.info("Registro insertado exitosamente: {}", resicoRepository);
            return true;
        } catch (Exception e) {
            logger.error("Error al insertar el registro: {}", e.getMessage(), e);
            return false;
        }
    }

    public List<ResicoEntity> getRegistrosPorUsuario(String idUsuario) {
        try {
            return resicoRepository.findByIdUsuario(idUsuario);
        } catch (Exception e) {
            logger.error("Error al obtener registros para id_usuario {}: {}", idUsuario, e.getMessage(), e);
            throw e; // Se propaga la excepción al controlador.
        }
    }
}
