package com.BallenaRuiz.denunciaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BallenaRuiz.denunciaservice.converter.DenunciasConverter;
import com.BallenaRuiz.denunciaservice.dto.DenunciasDTO;
import com.BallenaRuiz.denunciaservice.entity.Denuncias;
import com.BallenaRuiz.denunciaservice.service.DenunciasService;
import com.BallenaRuiz.denunciaservice.utils.WrapperResponse;


@RestController
@RequestMapping("/api/denuncias")
public class DenunciasController {
	
	@Autowired
	private DenunciasService service;
	
	@Autowired 
	private DenunciasConverter converter;
	
	//Metodos 
	@GetMapping()
	public ResponseEntity<List<DenunciasDTO>> findAll( // el Metodo findAll va a devolver los DTO
			//Parametros
			@RequestParam (value = "dni",required = false, defaultValue ="") String dni,
			@RequestParam (value = "offset",required = false, defaultValue ="0") int pageDni,
			@RequestParam (value = "limit",required = false, defaultValue ="5") int pageSize
			){
		Pageable page= PageRequest.of(pageDni,pageSize);
		List<Denuncias> denuncias;
		if(dni==null) {
			denuncias=service.findAll(page);	
		}else {
			denuncias=service.finByDni(dni, page);
		}
		
		List<DenunciasDTO> denunciasDTO=converter.fromEntity(denuncias); //convirte una lista de entidades a una lista de DTO
		return new WrapperResponse(true,"success",denunciasDTO).createResponse(HttpStatus.OK); // Devuelve la lista
	}
	
	@GetMapping(value="/{id}") //Notaciòn
	public ResponseEntity<WrapperResponse<DenunciasDTO>> findById(@PathVariable("id")int id){
		Denuncias denuncias = service.findById(id);
		DenunciasDTO denunciasDTO=converter.fromEntity(denuncias); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse<DenunciasDTO>(true,"success",denunciasDTO).createResponse(HttpStatus.OK); //retorna un DTO
	}
	
	@PostMapping()
	public ResponseEntity<DenunciasDTO> create(@RequestBody DenunciasDTO denunciasDTO){ //esperando un articulo DTO
		Denuncias registro=service.save(converter.fromDTO(denunciasDTO)); // convertir de un DTO a una entidad
		DenunciasDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED); //devolver el registro DTO
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<DenunciasDTO> update(@PathVariable("id")int id,@RequestBody DenunciasDTO denunciasDTO){
		Denuncias registro = service.update(converter.fromDTO(denunciasDTO));
		DenunciasDTO registroDTO=converter.fromEntity(registro); // se envia una entidad y lo convierte a un DTO
		return new WrapperResponse(true,"success",registro).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<DenunciasDTO> delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
