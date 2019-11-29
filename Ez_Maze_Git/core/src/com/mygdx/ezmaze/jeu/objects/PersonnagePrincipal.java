package com.mygdx.ezmaze.jeu.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.ezmaze.jeu.Assets;

public class PersonnagePrincipal extends AbstractGameObject {
	public static final String TAG = PersonnagePrincipal.class.getName();

	private static int POINTS_DE_VIE = 3;
	private static float DEGATS_ATTAQUE = 1f;

	public TextureRegion regPersonnage;

	/*
	 * Ici on pourra ajouter autant d'attributs que n�cessaire pour les bonus
	 * comme pour les malus.
	 * Dans le principe on aura envie de faire des 'enum' des diff�rents �tats
	 * possibles pour un m�me caract�re. 
	 * EXEMPLE :
	 */
	public enum ETAT_COMBAT{
		PAISIBLE,DEFENSE,ATTAQUE_SIMPLE,ATTAQUE_CHARGEE;
	}
	public float tempsChargeAttaque;
	public enum ARME_UTILISEE{
		NONE,EPEE,BOUCLIER,BALLON_DE_BASKET;
	}
	public ARME_UTILISEE armeUtilisee;
	public boolean attaqueChargee;
	public ETAT_COMBAT etatCombat;

	public PersonnagePrincipal() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		dimension.set(1,1);
		regPersonnage = Assets.instance.thesee.personnage;
		//Centrer l'origine de l'image
		origin.set(dimension.x/2, dimension.y/2);
		//Param�trage des fronti�res
		frontiere.set(0,0,dimension.x,dimension.y);
		//Valeurs de mobilite
		vitesseMax.set(2,2);
		frottement.set(10,10);
		/*
		 * On donne une grande valeur aux frottements pour �viter toute glissade
		 * incontr�l�e...
		 */
		acceleration.set(0,0);
		/*
		 * On met une acc�laration nulle parce qu'on n'a aucune raison d'en vouloir
		 * Mais on pourra introduire des champs de forces si l'on veut ! YOUPI
		 */
		
		//Mise � jour des attributs d'�tat du personnage
		armeUtilisee = ARME_UTILISEE.NONE;
		etatCombat = ETAT_COMBAT.PAISIBLE;
		tempsChargeAttaque = 0f;
	};

	//Les m�thodes pour d�finir les �tats du personnage
	public void setAttaque (boolean presseToucheAttaque) {
		switch (armeUtilisee) {
		case EPEE: //Le personnage porte une �p�e
			if(presseToucheAttaque) {
				//On compte le temps de charge de l'attaque (on ne s'en sert pas)
				tempsChargeAttaque = 0;
				etatCombat = ETAT_COMBAT.ATTAQUE_SIMPLE;
			}
			break;
		case BOUCLIER://le personnage porte le bouclier
			if(presseToucheAttaque) {
				//On compte le temps de charge de l'attaque
				tempsChargeAttaque = 0;
				etatCombat = ETAT_COMBAT.ATTAQUE_SIMPLE;
			}
			break;
		case BALLON_DE_BASKET://le personnage porte un ballon de basket
			/*
			 * NON IMPLEMENTE :p
			 */
			break;
		}
	}
	
	
	//FIN DES METHODE D'ETAT DU PERSONNAGE

	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		switch(armeUtilisee) {
		case EPEE: //Le personnage porte une �p�e
			tempsChargeAttaque += deltaTime;
			if (tempsChargeAttaque>3) {
				etatCombat = ETAT_COMBAT.ATTAQUE_CHARGEE;
				attaqueChargee = true;
			}
			break;
		case BOUCLIER://le personnage porte le bouclier
			break;
		case BALLON_DE_BASKET://le personnage porte un ballon de basket
			/*
			 * NON IMPLEMENTE :p
			 */
			break;
		}
	}
	
	@Override
	protected void updateMvtY(float deltaTime) {
		
		switch(armeUtilisee) {
		case EPEE: //Le personnage porte une �p�e
			break;
		case BOUCLIER://le personnage porte le bouclier
			vitesse.y = vitesse.y/1.5f;
			break;
		case BALLON_DE_BASKET://le personnage porte un ballon de basket
			/*
			 * NON IMPLEMENTE :p
			 */
			break;
		}
		super.updateMvtY(deltaTime);
	}
	@Override
	protected void updateMvtX(float deltaTime) {
		
		switch(armeUtilisee) {
		case EPEE: //Le personnage porte une �p�e
			break;
		case BOUCLIER://le personnage porte le bouclier
			vitesse.x = vitesse.x/1.5f;
			break;
		case BALLON_DE_BASKET://le personnage porte un ballon de basket
			/*
			 * NON IMPLEMENTE :p
			 */
			break;
		}
		super.updateMvtX(deltaTime);
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		TextureRegion reg = null;
		
		//On met une couleur rouge pour le personnage lorsque son attaque est charg�e
		if (attaqueChargee) {
			batch.setColor(1f,0f,0f,1f);
		}
		
		//On dessine le personnage
		reg = regPersonnage;
		//batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y);
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),reg.getRegionY(),reg.getRegionWidth(),reg.getRegionHeight(),false,false);
		//Dans le cas o� on aurrait modifi� la couleur du batch, on la r�initialise (blanc)
		batch.setColor(1,1,1,1);
	}

}