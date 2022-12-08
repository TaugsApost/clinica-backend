package br.com.clinica.funcionario.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.clinica.exception.ServiceException;
import br.com.clinica.funcionario.entity.Funcionario;
import br.com.clinica.funcionario.entity.Login;
import br.com.clinica.funcionario.funcao.Funcao;
import br.com.clinica.funcionario.search.FuncionarioFilter;
import br.com.clinica.funcionario.search.FuncionarioMapper;
import br.com.clinica.funcionario.search.FuncionarioResponse;
import br.com.clinica.medico.entity.Medico;
import br.com.clinica.utils.AbstractServiceBean;

@Transactional
@Service
public class FuncionarioServiceBean extends AbstractServiceBean<Funcionario, Long> implements FuncionarioService {

	@Autowired
	private PasswordEncoder encoder;

	public FuncionarioServiceBean(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Funcionario> listarTodos() throws ServiceException {
		return listarTodosEntity();
	}

	@Override
	public Funcionario salvar(Funcionario entity) throws ServiceException {
		return salvarEntity(entity);
	}

	@Override
	protected void beforeSave(Funcionario entity) throws ServiceException {
		entity.setSenhaHash(encoder.encode(entity.getSenhaHash()));
		super.beforeSave(entity);
	}

	@Override
	public String excluir(Long id) throws ServiceException {
		return excluirEntity(id);
	}

	@Override
	public Funcionario detalhar(Long id) throws ServiceException {
		return detalharEntity(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuncionarioResponse> consultar(FuncionarioFilter filter) throws ServiceException {
		List<FuncionarioResponse> resultado = new ArrayList<FuncionarioResponse>();
		Query query = this.getEntityManager().createQuery(Funcionario.CONSULTAR_QUERY);
		query.setParameter("nome", filter.getNome());
		query.setParameter("cidade", filter.getCidade());
		query.setParameter("estado", filter.getEstado());
		query.setParameter("bairro", filter.getBairro());
		query.setParameter("salario", filter.getSalario());
		query.setParameter("dataContrato", filter.getDataContrato());
		query.setParameter("operadorSalario", filter.getOperadorSalario());
		query.setParameter("operadorData", filter.getOperadorData());

		resultado = FuncionarioMapper.toResponse(query.getResultList());

		return resultado;
	}

	@Override
	public FuncionarioResponse logar(Login login) {
		try {
			Funcionario funcionario = this.getEntityManager().createQuery(Funcionario.PESQUISAR_POR_EMAIL, Funcionario.class)//
			        .setParameter("email", login.getEmail())//
			        .getSingleResult();
			if (encoder.matches(login.getSenha(), funcionario.getSenhaHash())) {
				FuncionarioResponse response = new FuncionarioResponse(funcionario.getId(), funcionario.getNome());
				try {
					Medico medico = this.getEntityManager().createQuery(Medico.BUSCAR_POR_ID, Medico.class)//
					        .setParameter("id", response.getId())//
					        .getSingleResult();
					response.setFuncao(Funcao.MEDICO);
				} catch (NoResultException e) {
					response.setFuncao(Funcao.FUNCIONARIO);
				}

				return response;
			} else {
				return new FuncionarioResponse();
			}
		} catch (NoResultException e) {
			return new FuncionarioResponse();
		}
	}

}
