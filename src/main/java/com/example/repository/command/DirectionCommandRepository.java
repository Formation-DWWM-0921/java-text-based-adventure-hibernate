package com.example.repository.command;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets DirectionCommand
 */
public class DirectionCommandRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public DirectionCommandRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @param name Le nom de la direction recherchée
     * @return La direction correspondant au nom spécifié
     */
    public DirectionCommand findByName(String name)
    {
        try {
            return entityManager.createQuery("SELECT direction FROM DirectionCommand direction WHERE name = :name", DirectionCommand.class)
                .setParameter("name", name)
                .getSingleResult();
        }
        // Si la requête ne renvoie aucun résultat, renvoie null au lieu d'arrêter l'application
        catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * @param fromRoom Le lieu de départ
     * @return La liste de toutes les directions que l'on peut emprunter en partant du lieu spécifié
     */
    public List<DirectionCommand> findByFromRoom(Room fromRoom)
    {
        return entityManager.createQuery("SELECT direction FROM DirectionCommand direction JOIN direction.connections AS connection WHERE connection.fromRoom = :fromRoom", DirectionCommand.class)
            .setParameter("fromRoom", fromRoom)
            .getResultList();
    }
}
