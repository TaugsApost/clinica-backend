package br.com.clinica.funcionario.resource;

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
import br.com.clinica.funcionario.entity.Funcionario;
import br.com.clinica.funcionario.entity.Login;
import br.com.clinica.funcionario.search.FuncionarioFilter;
import br.com.clinica.funcionario.search.FuncionarioResponse;
import br.com.clinica.funcionario.service.FuncionarioService;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioResource {

	@Autowired
	FuncionarioService service;

	@PostMapping(value = RestMapping.SALVAR)
	public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario Funcionario) throws ServiceException {
		Funcionario entity = service.salvar(Funcionario);
		return new ResponseEntity<Funcionario>(entity, HttpStatus.OK);
	}

	@PostMapping(value = RestMapping.BUSCAR)
	public ResponseEntity<List<FuncionarioResponse>> consultar(@RequestBody FuncionarioFilter filter) throws ServiceException {
		List<FuncionarioResponse> lista = service.consultar(filter);
		return new ResponseEntity<List<FuncionarioResponse>>(lista, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.DETALHAR + "/{id}")
	public ResponseEntity<Funcionario> detalhar(@PathVariable("id") Long id) throws ServiceException {
		Funcionario entity = service.detalhar(id);
		return new ResponseEntity<Funcionario>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.LISTAR_TODOS)
	public ResponseEntity<List<Funcionario>> listarFuncionarios() throws ServiceException {
		List<Funcionario> entity = service.listarTodos();
		return new ResponseEntity<List<Funcionario>>(entity, HttpStatus.OK);
	}

	@GetMapping(value = RestMapping.EXCLUIR + "/{id}")
	public ResponseEntity<String> excluirFuncionario(@PathVariable("id") Long id) throws ServiceException {
		String msg = service.excluir(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PostMapping(value = "/logar")
	public ResponseEntity<FuncionarioResponse> logar(@RequestBody Login login) {
		FuncionarioResponse response = this.service.logar(login);
		if (response.getId() != null) {
			return new ResponseEntity<FuncionarioResponse>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<FuncionarioResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
