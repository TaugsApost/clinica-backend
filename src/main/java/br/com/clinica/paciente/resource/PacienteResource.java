package br.com.clinica.paciente.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.paciente.entity.Paciente;
import br.com.clinica.paciente.search.PacienteFilter;
import br.com.clinica.paciente.search.PacienteResponse;
import br.com.clinica.paciente.service.PacienteService;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/paciente")
public class PacienteResource {

	@Autowired
	PacienteService service;

	@PostMapping(value = RestMapping.SALVAR)
	public ResponseEntity<Paciente> salvar(@RequestBody Paciente Paciente) throws ServiceException {
		Paciente entity = service.salvar(Paciente);
		return new ResponseEntity<Paciente>(entity, HttpStatus.OK);
	}

	@PostMapping(value = RestMapping.BUSCAR)
	public ResponseEntity<List<PacienteResponse>> consultar(@RequestBody PacienteFilter filter) throws ServiceException {
		List<PacienteResponse> lista = service.consultar(filter);
		return new ResponseEntity<List<PacienteResponse>>(lista, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.DETALHAR + "/{id}")
	public ResponseEntity<Paciente> detalhar(@PathVariable("id") Long id) throws ServiceException {
		Paciente entity = service.detalhar(id);
		return new ResponseEntity<Paciente>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.LISTAR_TODOS)
	public ResponseEntity<List<Paciente>> listarPacientes() throws ServiceException {
		List<Paciente> entity = service.listarTodos();
		return new ResponseEntity<List<Paciente>>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.EXCLUIR + "/{id}")
	public ResponseEntity<String> excluirPaciente(@PathVariable("id") Long id) throws ServiceException {
		String msg = service.excluir(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
