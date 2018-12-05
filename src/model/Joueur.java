package model;

public class Joueur
{
	public final static int PIECEUN = 0;
	public final static int PIECECINQ = 1;

	protected int couleur;
	protected int[] argent;
	protected PaquetTapis paquet;

	public Joueur(int couleur, PaquetTapis paquet)
	{
		this.couleur = couleur;
		this.paquet = paquet;

		argent = new int[2];
		argent[PIECEUN] = 5;
		argent[PIECECINQ] = 5;
	}

	public int getCouleur()
	{
		return this.couleur;
	}

	public int getArgent(int typePiece)
	{
		return this.argent[typePiece];
	}

	public int lancerDe(De de)
	{
		return de.getValeur();
	}

	public int getArgentTotal()
	{
		return argent[PIECEUN] * 1 + argent[PIECECINQ] * 5;
	}

	public void ajouterPiece(int nombrePieceUn, int nombrePieceCinq)
	{
		this.argent[PIECEUN] = this.argent[PIECEUN] + nombrePieceUn;
		this.argent[PIECECINQ] = this.argent[PIECECINQ] + nombrePieceCinq;
	}

	public void retirerPiece(int nombrePieceUn, int nombrePieceCinq)
	{
		this.argent[PIECEUN] = this.argent[PIECEUN] - nombrePieceUn;
		this.argent[PIECECINQ] = this.argent[PIECECINQ] - nombrePieceCinq;
	}

	public void payerDime(int cout, Joueur adversaire)
	{
		if(this.getArgentTotal() <= cout)
		{
			adversaire.ajouterPiece(this.argent[PIECEUN], this.argent[PIECECINQ]);
			this.retirerPiece(this.argent[PIECEUN], this.argent[PIECECINQ]);
		}
		else
		{
			int nombrePieceUn = 0;
			int nombrePieceCinq = 0;

			do
			{
				nombrePieceCinq++;
			} while((nombrePieceCinq * 5) < cout);

			nombrePieceCinq--;

			do
			{
				nombrePieceUn++;
			} while(((nombrePieceCinq * 5) + (nombrePieceUn)) != cout);

			adversaire.ajouterPiece(nombrePieceUn, nombrePieceCinq);
			this.retirerPiece(nombrePieceUn, nombrePieceCinq);
		}
	}

	/*public void setTapisCoord(int tapisIndex, Coord x, Coord y)
	{
		this.tapis[tapisIndex].nouvelPosition(x,y);
	}*/

	public Tapis getTapis()
	{
		return this.paquet.getTapis();
	}

	public int getNombreTapis()
	{
		return this.paquet.getSize();
	}
}