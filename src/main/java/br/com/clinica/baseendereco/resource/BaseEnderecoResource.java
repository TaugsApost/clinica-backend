package br.com.clinica.baseendereco.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clinica.baseendereco.entity.BaseEndereco;
import br.com.clinica.baseendereco.service.BaseEnderecoService;
import br.com.clinica.exception.ServiceException;
import br.com.clinica.utils.RestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/baseEndereco")
public class BaseEnderecoResource {

	@Autowired
	BaseEnderecoService service;

	@PostMapping(value = "/pesquisarPorCep")
	public ResponseEntity<BaseEndereco> pesquisarPorCep(@RequestBody String cep) {
		BaseEndereco entity = service.consultarPorCep(cep);
		return new ResponseEntity<BaseEndereco>(entity, HttpStatus.OK);
	}

	@PostMapping(value = RestMapping.SALVAR)
	public ResponseEntity<BaseEndereco> salvar(@RequestBody BaseEndereco entity) throws ServiceException {
		BaseEndereco response = service.salvar(entity);
		return new ResponseEntity<BaseEndereco>(response, HttpStatus.OK);
	}

}
