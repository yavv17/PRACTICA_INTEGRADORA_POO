package com.upiicsa.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import com.upiicsa.modelo.HistorialBatalla;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BatallaHibernateDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(
                "ArenaPOOPersistenceUnit",
                obtenerConfiguracion()
            );

    private static Map<String, String> obtenerConfiguracion() {

        Properties propiedades = new Properties();

        try {
            FileInputStream archivo =
                    new FileInputStream("db.properties");

            propiedades.load(archivo);
            archivo.close();

            Map<String, String> configuracion = new HashMap<>();

            configuracion.put(
                    "jakarta.persistence.jdbc.driver",
                    propiedades.getProperty("db.driver")
            );

            configuracion.put(
                    "jakarta.persistence.jdbc.url",
                    propiedades.getProperty("db.url")
            );

            configuracion.put(
                    "jakarta.persistence.jdbc.user",
                    propiedades.getProperty("db.user")
            );

            configuracion.put(
                    "jakarta.persistence.jdbc.password",
                    propiedades.getProperty("db.password")
            );

            return configuracion;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
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
