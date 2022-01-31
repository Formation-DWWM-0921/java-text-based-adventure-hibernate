package com.example.entity.command;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.game.CommandInterpreter;

/**
 * Représente une commande permettant de sortir du jeu
 */
@Entity
@Table(name = "exit_command")
public class ExitCommand extends Command
{
    /**
     * Délègue l'exécution de la commande à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à la commande
     */
    public void delegateTo(CommandInterpreter interpreter)
    {
        interpreter.execute(this);
    }
}
