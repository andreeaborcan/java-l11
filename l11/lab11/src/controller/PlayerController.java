/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Player;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author ada
 */
public class PlayerController {
  
    private EntityManagerFactory emf;
    public PlayerController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void create(Player player) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
    }
    public Player findByName(String playerName) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from Player t where t.name=:name");
        List players = query.setParameter("name", playerName).getResultList();
        em.close();
        return players.isEmpty() ? null :(Player) players.get(0);
    }

}
