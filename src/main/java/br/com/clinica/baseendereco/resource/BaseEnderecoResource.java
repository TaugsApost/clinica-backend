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

}
