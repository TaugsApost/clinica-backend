package br.com.clinica.utils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.clinica.exception.ServiceException;

public abstract class AbstractServiceBean<E extends AbstractEntity<T>, T extends Object> implements IService<E, T> {

	private final Class<E> entityClass;

	private final EntityManager em;

	private final Logger log;

	@SuppressWarnings("unchecked")
	public AbstractServiceBean(EntityManager em) {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.em = em;
		log = Logger.getLogger(entityClass.getName());
	}

	protected EntityManager getEntityManager() {
		return this.em;
	}

	public E detalharEntity(T id) throws ServiceException {
		E entity = this.em.find(entityClass, id);
		if (entity == null) {
			log.log(Level.WARNING, "Não foi possivel encontrar a entidade de " + entityClass.getName() + " com o id " + id);
			throw new ServiceException("Não foi possivel encontrar a entidade de " + entityClass.getName() + " com o id " + id);
		}
		return entity;
	}

	protected void afterSave(E entity) {

	}

	protected void beforeSave(E entity) throws ServiceException {

	}

	public E salvarEntity(E entity) throws ServiceException {
		if (entity.getId() == null) {
			save(entity);
			return entity;
		} else
			return update(entity);
	}

	private E update(E entity) throws ServiceException {
		this.beforeSave(entity);
		E updatedEntity = this.em.merge(entity);
		this.afterSave(updatedEntity);
		return updatedEntity;
	}

	private void save(E entity) throws ServiceException {
		this.beforeSave(entity);
		this.em.persist(entity);
		this.afterSave(entity);
	}

	public String excluirEntity(T id) throws ServiceException {
		E entity = detalhar(id);
		if (entity == null) {
			log.log(Level.WARNING, "Não foi possivel encontrar a entidade de " + entityClass.getName() + " com o id " + id);
			throw new ServiceException("Não foi possivel encontrar a entidade de " + entityClass.getName() + " com o id " + id);
		}
		this.em.remove(entity);
		return "Entidade com id " + id + " de " + entityClass.getName() + " excluida!";
	}

	public List<E> listarTodosEntity() {
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		TypedQuery<E> query = this.em.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
