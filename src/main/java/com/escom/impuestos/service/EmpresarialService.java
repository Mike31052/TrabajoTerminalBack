package com.escom.impuestos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.impuestos.model.EmpresarialEntity;
import com.escom.impuestos.repository.EmpresarialRepository;

@Service
public class EmpresarialService {

    private static final Logger logger = LoggerFactory.getLogger(EmpresarialService.class);

    @Autowired
    private EmpresarialRepository empresarialRepository;

    public boolean addRegistro(EmpresarialEntity empresarialEntity){
        try {
            empresarialRepository.save(empresarialEntity);
            logger.info("Registro insertado exitosamente: {}", empresarialEntity);
            return true;
        } catch (Exception e) {
            logger.error("Error al insertar el registro: {}", e.getMessage(), e);
            return false;
        }
    }

    public List<EmpresarialEntity> getRegistrosPorUsuario(String idUsuario) {
        try {
            return empresarialRepository.findByIdUsuario(idUsuario);
        } catch (Exception e) {
            logger.error("Error al obtener registros para id_usuario {}: {}", idUsuario, e.getMessage(), e);
            throw e; // Se propaga la excepción al controlador.
        }
    }

}
