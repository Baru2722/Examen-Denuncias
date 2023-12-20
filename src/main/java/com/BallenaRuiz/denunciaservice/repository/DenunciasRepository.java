package com.BallenaRuiz.denunciaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BallenaRuiz.denunciaservice.entity.Denuncias;

import java.util.List;
import java.util.Optional;

@Repository
public interface DenunciasRepository extends JpaRepository<Denuncias, Integer>{
	List<Denuncias> findByDniContaining(String dni, org.springframework.data.domain.Pageable page); // Metodo para buscar por el mombre
	Denuncias findByDni(String dni);
	Optional<Denuncias> findByDniContaining(String dni);

}