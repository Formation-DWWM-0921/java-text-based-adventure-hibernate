package com.example.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Service spécialisé dans les opérations en base de données
 */
public abstract class Repository<T>
{
    /**
     * Le type d'entité que le service doit manipuler
     */
    protected Class<T> entityType;
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    protected EntityManager entityManager;

    /**
     * Crée un nouveau service
     * @param entityType Le type d'entité que le service doit manipuler
     */
    public Repository(Class<T> entityType)
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
        this.entityType = entityType;
    }

    /**
     * @return Tous les objets de la classe concernée
     */
    public List<T> findAll()
    {
        return entityManager.createQuery(String.format("SELECT entity FROM %s entity", entityType.getName()), entityType)
            .getResultList();
    }

    /**
     * @param id L'identifiant en base de données de l'objet recherché
     * @return L'objet correspondant à l'identifiant fourni
     */
    public T findById(int id)
    {
        return entityManager.find(entityType, id);        
    }
}
