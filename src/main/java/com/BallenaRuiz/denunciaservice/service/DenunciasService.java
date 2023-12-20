package com.BallenaRuiz.denunciaservice.service;

import org.springframework.data.domain.Pageable;

import com.BallenaRuiz.denunciaservice.entity.Denuncias;

import java.util.List;

public interface DenunciasService {
	//Metodos
	public List<Denuncias> findAll(Pageable page);
	public List<Denuncias> finByDni(String dni,Pageable page ); //Busqueda
	public Denuncias findById (int id);
	public Denuncias save (Denuncias denuncias);
	public Denuncias update (Denuncias denuncias);
	public void delete (int id);

}

