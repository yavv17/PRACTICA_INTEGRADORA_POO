package com.upiicsa.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import com.upiicsa.modelo.HistorialBatalla;

public class BatallaHibernateDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ArenaPOOPersistenceUnit");

    public void registrarBatalla(HistorialBatalla batalla) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO: Persistir el objeto 'batalla' usando em.persist(...)
            em.persist(batalla);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<HistorialBatalla> obtenerHistorial() {
        EntityManager em = emf.createEntityManager();
        try {
            // TODO: Escribir la consulta JPQL sobre la entidad HistorialBatalla ordenada por fechaRegistro DESC
            return em.createQuery("SELECT h FROM HistorialBatalla h ORDER BY h.fechaRegistro DESC", HistorialBatalla.class).getResultList();
        } finally {
            em.close();
        }
    }
}
