/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author ada
 */
public class TeamController {
    private EntityManagerFactory emf;
    public TeamController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void create(Team team) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();
        em.close();
    }
    public Team findByName(String teamName) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from Team t where t.name=:name");
        List teams = query.setParameter("name", teamName).getResultList();
        em.close();
        return teams.isEmpty() ? null :(Team) teams.get(0);
    }
}