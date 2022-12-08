package br.com.clinica.medico.resource;

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

import br.com.clinica.especialidade.entity.Especialidade;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.funcionario.entity.Funcionario;
import br.com.clinica.medico.entity.Medico;
import br.com.clinica.medico.service.MedicoService;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/medico")
public class MedicoResource {

	@Autowired
	MedicoService service;

	@PostMapping(value = RestMapping.SALVAR)
	public ResponseEntity<Funcionario> salvar(@RequestBody Medico Medico) throws ServiceException {
		Funcionario entity = service.salvar(Medico);
		return new ResponseEntity<Funcionario>(entity, HttpStatus.OK);
	}

	@PostMapping(value = "/listarPorEspecialidade")
	public ResponseEntity<List<Medico>> listarPorEspecialidade(@RequestBody Especialidade especialidade) throws ServiceException {
		List<Medico> lista = service.listarPorEspecialidade(especialidade);
		return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.DETALHAR + "/{id}")
	public ResponseEntity<Medico> detalhar(@PathVariable("id") Long id) throws ServiceException {
		Medico entity = service.detalhar(id);
		return new ResponseEntity<Medico>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.LISTAR_TODOS)
	public ResponseEntity<List<Medico>> listarMedicos() throws ServiceException {
		List<Medico> entity = service.listarTodos();
		return new ResponseEntity<List<Medico>>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.EXCLUIR + "/{id}")
	public ResponseEntity<String> excluirMedico(@PathVariable("id") Long id) throws ServiceException {
		String msg = service.excluir(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
