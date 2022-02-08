package com.example.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Service spécialisé dans la création du gestionnaire d'entité
 */
public final class EntityManagerService
{
    /**
     * Le nom de l'unité de persistence à créer
     */
    private static final String unitName = "TextBasedAdventure";
    /**
     * L'unique instance du gestionnaire d'entité
     */
    private static EntityManager manager;

    /**
     * Constructeur privé, empêche la création d'instance de cette classe
     */
    private EntityManagerService() { }

    /**
     * Renvoie une unique instance du gestionnaire d'entité, en commençant par le créer si besoin.
     * @return L'unique instance du gestionnaire d'entité
     */
    public static EntityManager getManager()
    {
        if (manager == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(unitName);
            manager = factory.createEntityManager();
        }
        return manager;
    }
}
