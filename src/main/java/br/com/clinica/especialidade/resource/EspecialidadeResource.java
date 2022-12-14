package br.com.clinica.especialidade.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.especialidade.service.EspecialidadeService;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/especialidade")
public class EspecialidadeResource {

	@Autowired
	EspecialidadeService service;

	@GetMapping(value = RestMapping.LISTAR_TODOS)
	public ResponseEntity<List<Especialidade>> listarEspecialidades() throws ServiceException {
		List<Especialidade> entity = service.listarTodos();
		return new ResponseEntity<List<Especialidade>>(entity, HttpStatus.OK);
	}

}
