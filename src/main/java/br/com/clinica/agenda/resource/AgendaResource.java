package br.com.clinica.agenda.resource;

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

import br.com.clinica.agenda.entity.Agenda;
import br.com.clinica.agenda.search.AgendaFilter;
import br.com.clinica.agenda.search.AgendaResponse;
import br.com.clinica.agenda.service.AgendaService;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/Agenda")
public class AgendaResource {

	@Autowired
	AgendaService service;

	@PostMapping(value = RestMapping.SALVAR)
	public ResponseEntity<Agenda> salvar(@RequestBody Agenda agenda) throws ServiceException {
		Agenda entity = service.salvar(agenda);
		return new ResponseEntity<Agenda>(entity, HttpStatus.OK);
	}

	@PostMapping(value = RestMapping.BUSCAR)
	public ResponseEntity<List<AgendaResponse>> consultar(@RequestBody AgendaFilter filter) throws ServiceException {
		List<AgendaResponse> resultado = service.consultarAgenda(filter);
		return new ResponseEntity<List<AgendaResponse>>(resultado, HttpStatus.OK);
	}

	@PostMapping(value = "/listarPorMedico")
	public ResponseEntity<List<AgendaResponse>> pesquisarPorMedico(@RequestBody AgendaFilter filter) {
		List<AgendaResponse> resultado = service.listarAgendaPorMedico(filter);
		return new ResponseEntity<List<AgendaResponse>>(resultado, HttpStatus.OK);
	}

	@PostMapping(value = "/listaHorariosOcupados")
	public ResponseEntity<List<Integer>> pesquisarHorariosOcupados(@RequestBody AgendaFilter filter) {
		List<Integer> resultado = service.listarHorariosOcupados(filter);
		return new ResponseEntity<List<Integer>>(resultado, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.DETALHAR + "/{id}")
	public ResponseEntity<Agenda> detalhar(@PathVariable("id") Long id) throws ServiceException {
		Agenda entity = service.detalhar(id);
		return new ResponseEntity<Agenda>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.LISTAR_TODOS)
	public ResponseEntity<List<Agenda>> listarAgendas() throws ServiceException {
		List<Agenda> entity = service.listarTodos();
		return new ResponseEntity<List<Agenda>>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.EXCLUIR + "/{id}")
	public ResponseEntity<String> excluirAgenda(@PathVariable("id") Long id) throws ServiceException {
		String msg = service.excluir(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
