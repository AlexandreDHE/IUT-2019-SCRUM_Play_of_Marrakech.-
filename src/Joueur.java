/**
 * Représente un joueur
 *  
 * @author Faïza BOUMALLOUK et Rabah DJADI.
 */
public class Joueur
{
	/**
     * les différents types de pieces
     * PIECEUN   correspond aux pièces de 1 Dirham
     * PIECECINQ correspond aux pièces de 5 Dirhams
     */
	public final static int PIECEUN = 0;
	public final static int PIECECINQ = 1;

	protected int couleur;
	protected int[] argent;
	protected Tapis[] tapis;

	public Joueur(int couleur, int nombreTapis)
	{
		this.couleur = couleur;

		argent = new int[2];
		argent[PIECEUN] = 5;
		argent[PIECECINQ] = 5;

		this.tapis = new Tapis[nombreTapis];
		for(int i = 0; i < nombreTapis; i++)
		{
			this.tapis[i] = new Tapis(this.couleur, -1, -1);
		}
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

	public void payerDime(int cout, Joueur adversaire)
	{
		if(this.getArgentTotal() <= cout)
		{
			adversaire.ajouterPiece(this.argent[PIECEUN], this.argent[PIECECINQ]);
			this.argent[PIECEUN] = 0;
			this.argent[PIECECINQ] = 0;
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
			this.argent[PIECEUN] = this.argent[PIECEUN] - nombrePieceUn;
			this.argent[PIECECINQ] = this.argent[PIECECINQ] - nombrePieceCinq;
		}
	}

	public setTapisCoord(int tapisIndex, int x, int y)
	{
		this.tapis[tapisIndex].set(x);
		this.tapis[tapisIndex].set(y);
	}

	public Tapis[] getTapis()
	{
		return this.tapis;
	}
}