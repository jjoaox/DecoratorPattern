package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Planeta;
import util.JPAUtil;

public class PlanetaDao {
	
	public static void salvar(Planeta p) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	
	/*
	 * public static void atualizar(Planeta p) { EntityManager em =
	 * JPAUtil.criarEntityManager(); em.getTransaction().begin(); em.merge(p);
	 * em.getTransaction().commit(); em.close(); }
	 */
	
	public static List<Planeta> listarPlanetas(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select p from Planeta p");
		List<Planeta> lista = q.getResultList();
		em.close();
		return lista;
	}
	
	public static void deletar(Planeta p) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		p = em.find(Planeta.class, p.getId());
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}
	
	/*
	 * public static Planeta buscarPorId(Integer id) { EntityManager em =
	 * JPAUtil.criarEntityManager(); Planeta p1 = em.find(Planeta.class, id);
	 * em.close(); return p1; }
	 */

}
