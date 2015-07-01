package com.senai.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.senai.entidades.Cliente;
import com.senai.util.JpaUtil;

public class ClienteDAO {
	EntityManager em = JpaUtil.getEntityManager();

	public void insere(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}

	public void delete(Cliente cliente) {
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
	}

	public List<Cliente> buscarTodos() {
		return em.createQuery("select c from Cliente c").getResultList();
	}

	public Cliente buscarPorId(int id) {
		return em.find(Cliente.class, id);
	}

	public Cliente buscarNome(String nome) {
		return (Cliente) em
				.createQuery("select p from Cliente p where p.nomeCliente = :nomeCliente")
				.setParameter("nome", nome).getSingleResult();
	}

	public void atualizar(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}
}
