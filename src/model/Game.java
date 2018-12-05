package model;

import javax.swing.event.EventListenerList;

import event.GameEvent;
import listener.GameListener;
import model.Joueur;

public class Game
{
	public static final int NOTSTARTED = 0;
	public static final int START = 1;
	
	protected int state;
	
	protected Assam assam;
	protected De de;
	protected Joueur[] joueurs;

	protected final EventListenerList listeners;

	public Game(int nombreJoueurs)
	{
		this.listeners = new EventListenerList();
		this.joueurs = new Joueur[nombreJoueurs];
		

		if (nombreJoueurs == 2)
		{
			PaquetTapis paquet = new PaquetTapis();
			for(int i = 0; i < nombreJoueurs; i++)
			{
				joueurs[i] = new Joueur(i, paquet);
				for(int j = 0; j < 24; j++)
				{
					paquet.addTapis(new Tapis(joueurs[i].getCouleur()));
				}
			}
			paquet.melanger();
		}

		else if (nombreJoueurs == 3)
		{
			for(int i = 0; i < nombreJoueurs; i++)
			{
				PaquetTapis paquet = new PaquetTapis();
				joueurs[i] = new Joueur(i, paquet);
				for(int j = 0; j < 15; j++)
				{
					paquet.addTapis(new Tapis(joueurs[i].getCouleur()));
				}
			}
		}

		else if (nombreJoueurs == 4)
		{
			for(int i = 0; i < nombreJoueurs; i++)
			{
				PaquetTapis paquet = new PaquetTapis();
				joueurs[i] = new Joueur(i, paquet);
				for(int j = 0; j < 12; j++)
				{
					paquet.addTapis(new Tapis(joueurs[i].getCouleur()));
				}
			}
		}

		assam = Assam.getAssam();
		de = De.getDe();
		this.state = NOTSTARTED;
	}
	
	public void start()
	{
		int oldState = this.state;
		this.state = START;
		this.fireGameStateChanged(new GameEvent(oldState, this.state));
	}

	public void addGameListener(GameListener listener)
	{
		this.listeners.add(GameListener.class, listener);
	}

	 public GameListener[] getGameListeners() 
	 {
        return listeners.getListeners(GameListener.class);
    }

	public void fireGameStateChanged(GameEvent event)
	{
		for(GameListener listener : this.getGameListeners()) 
		{
            listener.gameStarted(event);
        }
	}

	public Joueur[] getJoueurs()
	{
		return this.joueurs;
	}

	public De getDe()
	{
		return this.de;
	}
}