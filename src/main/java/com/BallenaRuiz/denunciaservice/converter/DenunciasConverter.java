package com.BallenaRuiz.denunciaservice.converter;

import org.springframework.stereotype.Component;

import com.BallenaRuiz.denunciaservice.dto.DenunciasDTO;
import com.BallenaRuiz.denunciaservice.entity.Denuncias;


@Component
public class DenunciasConverter extends AbstractConverter<Denuncias,DenunciasDTO>{

	//Convertir a un DTo
	@Override
	public DenunciasDTO fromEntity(Denuncias entity) {
		if(entity==null) return null;
		return DenunciasDTO.builder() //construirlo
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha(entity.getFecha())
				.titulo(entity.getTitulo())
				.direccion(entity.getDireccion())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Denuncias fromDTO(DenunciasDTO dto) {
		if(dto==null) return null;
		return Denuncias.builder() //construirlo
				.id(dto.getId())
				.dni(dto.getDni())
				.dni(dto.getDni())
				.fecha(dto.getFecha())
				.titulo(dto.getTitulo())
				.direccion(dto.getDireccion())
				.descripcion(dto.getDescripcion())
				.build();
	}

}
