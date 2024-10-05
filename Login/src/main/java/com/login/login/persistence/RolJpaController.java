/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.login.login.persistence;

import com.login.login.logic.Rol;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.login.login.logic.User;
import com.login.login.persistence.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gaston
 */
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RolJpaController() {
        emf = Persistence.createEntityManagerFactory("loginPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getListUser() == null) {
            rol.setListUser(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<User> attachedListUser = new ArrayList<User>();
            for (User listUserUserToAttach : rol.getListUser()) {
                listUserUserToAttach = em.getReference(listUserUserToAttach.getClass(), listUserUserToAttach.getId());
                attachedListUser.add(listUserUserToAttach);
            }
            rol.setListUser(attachedListUser);
            em.persist(rol);
            for (User listUserUser : rol.getListUser()) {
                Rol oldOneRolOfListUserUser = listUserUser.getOneRol();
                listUserUser.setOneRol(rol);
                listUserUser = em.merge(listUserUser);
                if (oldOneRolOfListUserUser != null) {
                    oldOneRolOfListUserUser.getListUser().remove(listUserUser);
                    oldOneRolOfListUserUser = em.merge(oldOneRolOfListUserUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getId());
            List<User> listUserOld = persistentRol.getListUser();
            List<User> listUserNew = rol.getListUser();
            List<User> attachedListUserNew = new ArrayList<User>();
            for (User listUserNewUserToAttach : listUserNew) {
                listUserNewUserToAttach = em.getReference(listUserNewUserToAttach.getClass(), listUserNewUserToAttach.getId());
                attachedListUserNew.add(listUserNewUserToAttach);
            }
            listUserNew = attachedListUserNew;
            rol.setListUser(listUserNew);
            rol = em.merge(rol);
            for (User listUserOldUser : listUserOld) {
                if (!listUserNew.contains(listUserOldUser)) {
                    listUserOldUser.setOneRol(null);
                    listUserOldUser = em.merge(listUserOldUser);
                }
            }
            for (User listUserNewUser : listUserNew) {
                if (!listUserOld.contains(listUserNewUser)) {
                    Rol oldOneRolOfListUserNewUser = listUserNewUser.getOneRol();
                    listUserNewUser.setOneRol(rol);
                    listUserNewUser = em.merge(listUserNewUser);
                    if (oldOneRolOfListUserNewUser != null && !oldOneRolOfListUserNewUser.equals(rol)) {
                        oldOneRolOfListUserNewUser.getListUser().remove(listUserNewUser);
                        oldOneRolOfListUserNewUser = em.merge(oldOneRolOfListUserNewUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = rol.getId();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<User> listUser = rol.getListUser();
            for (User listUserUser : listUser) {
                listUserUser.setOneRol(null);
                listUserUser = em.merge(listUserUser);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rol findRol(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
