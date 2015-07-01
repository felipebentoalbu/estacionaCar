package com.senai.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.senai.entidades.Estaciona;
import com.senai.util.JpaUtil;

public class EstacionaDAO {
	EntityManager em = JpaUtil.getEntityManager();

	public void insere(Estaciona estaciona) {
		em.getTransaction().begin();
		em.persist(estaciona);
		em.getTransaction().commit();
	}

	public void delete(Estaciona estaciona) {
		em.getTransaction().begin();
		em.remove(estaciona);
		em.getTransaction().commit();
	}

	public List<Estaciona> buscarTodos() {
		return em.createQuery("select e from Estaciona e").getResultList();
	}

	public Estaciona buscarPorId(int id) {
		return em.find(Estaciona.class, id);
	}

	public Estaciona buscarNome(String nome) {
		return (Estaciona) em
				.createQuery("select e from Estaciona p where p.nome = :nome")
				.setParameter("nome", nome).getSingleResult();
	}

	public void atualizar(Estaciona estaciona) {
		em.getTransaction().begin();
		em.merge(estaciona);
		em.getTransaction().commit();
	}
}
