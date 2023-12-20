package com.BallenaRuiz.denunciaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BallenaRuiz.denunciaservice.entity.Denuncias;
import com.BallenaRuiz.denunciaservice.exceptions.GeneralServiceException;
import com.BallenaRuiz.denunciaservice.exceptions.NoDataFoundException;
import com.BallenaRuiz.denunciaservice.exceptions.ValidateServiceException;
import com.BallenaRuiz.denunciaservice.repository.DenunciasRepository;
import com.BallenaRuiz.denunciaservice.service.DenunciasService;
import com.BallenaRuiz.denunciaservice.validator.DenunciasValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DenunciasServiceImpl implements DenunciasService {
	@Autowired
	private DenunciasRepository repository;
	
	@Override
	@Transactional(readOnly=true) // Metodo de solo lectura 
	public List<Denuncias> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Denuncias> finByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Denuncias findById(int id) {
		try {
			Denuncias registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Denuncias finByDni(String dni) {
	    try {
	        return repository.findByDniContaining(dni).orElseThrow(() -> new NoDataFoundException("No existe el DNI"));
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        log.info(e.getMessage(), e);
	        throw e;
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        throw new GeneralServiceException(e.getMessage(), e);
	    }
	}

	@Override
	@Transactional
	public Denuncias save(Denuncias denuncias) {
		try {
			DenunciasValidator.save(denuncias);
			if(repository.findByDni(denuncias.getDni())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+denuncias.getDni());
			}
			denuncias.setActivo(true);
			Denuncias registro=repository.save(denuncias);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncias update(Denuncias denuncias) {
		try {
			DenunciasValidator.save(denuncias);
			Denuncias registro=repository.findById(denuncias.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			Denuncias registroD = repository.findByDni(denuncias.getDni());
			if(registroD!=null && registroD.getId()!=denuncias.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+denuncias.getDni());
			}
			registro.setDni(denuncias.getDni());
			registro.setFecha(denuncias.getFecha());
			registro.setTitulo(denuncias.getTitulo());
			registro.setDireccion(denuncias.getDireccion());
			registro.setDescripcion(denuncias.getDescripcion());
			
			//registro.setPrecio(alumno.getPrecio());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Denuncias registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e); //Mostrar
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

}
