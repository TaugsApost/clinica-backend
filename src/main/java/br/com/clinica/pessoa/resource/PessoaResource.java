package br.com.clinica.pessoa.resource;

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
import br.com.clinica.pessoa.entity.Pessoa;
import br.com.clinica.pessoa.service.PessoaService;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	PessoaService service;

	@PostMapping(value = RestMapping.SALVAR)
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) throws ServiceException {
		Pessoa entity = service.salvar(pessoa);
		return new ResponseEntity<Pessoa>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.DETALHAR + "/{id}")
	public ResponseEntity<Pessoa> detalhar(@PathVariable("id") Long id) throws ServiceException {
		Pessoa entity = service.detalhar(id);
		return new ResponseEntity<Pessoa>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.LISTAR_TODOS)
	public ResponseEntity<List<Pessoa>> listarPessoas() throws ServiceException {
		List<Pessoa> entity = service.listarTodos();
		return new ResponseEntity<List<Pessoa>>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.EXCLUIR + "/{id}")
	public ResponseEntity<String> excluirPessoa(@PathVariable("id") Long id) throws ServiceException {
		String msg = service.excluir(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
