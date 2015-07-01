package com.senai.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.senai.entidades.Veiculo;
import com.senai.util.JpaUtil;

public class VeiculoDAO {
	EntityManager em = JpaUtil.getEntityManager();

	public void insere(Veiculo veiculo) {
		em.getTransaction().begin();
		em.persist(veiculo);
		em.getTransaction().commit();
	}

	public void delete(Veiculo veiculo) {
		em.getTransaction().begin();
		em.remove(veiculo);
		em.getTransaction().commit();
	}

	public List<Veiculo> buscarTodos() {
		return em.createQuery("select v from Veiculo v").getResultList();
	}

	public Veiculo buscarPorId(int id) {
		return em.find(Veiculo.class, id);
	}

	public Veiculo buscarNome(String nome) {
		return (Veiculo) em
				.createQuery("select p from Veiculo p where p.nome = :nome")
				.setParameter("nome", nome).getSingleResult();
	}

	public void atualizar(Veiculo veiculo) {
		em.getTransaction().begin();
		em.merge(veiculo);
		em.getTransaction().commit();
	}
}
