package com.BallenaRuiz.denunciaservice.validator;

import com.BallenaRuiz.denunciaservice.entity.Denuncias;
import com.BallenaRuiz.denunciaservice.exceptions.ValidateServiceException;

public class DenunciasValidator {
	public static void save(Denuncias denuncias) {
        if (denuncias.getDni() == null || denuncias.getDni().isEmpty()) {
            throw new ValidateServiceException("El dni es requerido");
        }
        if (denuncias.getDni().length()>100) {
        	throw new ValidateServiceException("El dni es muy largo");
        }
        
        if (denuncias.getTitulo().length()>100) {
        	throw new ValidateServiceException("el titulo es muy largo");
        }
        if (denuncias.getDireccion().length()>100) {
        	throw new ValidateServiceException("La dirección es muy largo");
        }
        if (denuncias.getDescripcion().length()>100) {
        	throw new ValidateServiceException("La descripción es muy largo");
        }
    }
}

