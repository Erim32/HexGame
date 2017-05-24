#include <stdio.h>

#include "./Plateau/plateau.h"
#include "./Groupe/groupe.h"
#include "./Inout/inout.h"
#include "./Groupe/IA.h"

int main(){
	plateau p;
	int n, quitter, saisieChoix, coup, vide;
	char joueur = 'x';
	quitter = 0;
	printf("Saisir la taille du plateau de jeu:");
	scanf("%d",&n);
	p = creerPlateau(n);
	p = initialiserPlateau(p);
	while(!vainqueur(p) && !quitter){
		printf("Entrez votre choix\n1) Jouer   2) Sauvegarder   3) Retour arriere   4) Quitter");
		scanf("%d",&saisieChoix);
		switch(saisieChoix){
			case 1: 
				vide = 0;
				if (joueur == 'o'){
					while (vide == 0){
						coup = IasimpleChemin(getEnsemble(p),'o','x',0);
						printf("Je veux jouer sur %d\n",coup);
						vide = caseVide(p,coup/getTaille(p), coup%getTaille(p));
					}
				} else {
					while (vide == 0){
						coup = IasimpleChemin(getEnsemble(p),'x','o',1);
						printf("Je veux jouer sur %d\n",coup);
						vide = caseVide(p,coup/getTaille(p), coup%getTaille(p));
					}
				}
				jouer(p, joueur, coup/getTaille(p), coup%getTaille(p));
				affichage(p);
				if (joueur == 'x')
					joueur = 'o';
				else
					joueur = 'x';
				break;
			case 2: 
				printf("404 not found\nRevenez plus tard\n");
				break;
			case 3: 
				p = retourArriere(p);
				break;
			case 4:
				quitter = 1;
				break;
			default: 
				printf("Saisie incorrect\n");
				break;
		}
	}
	if (vainqueur(p))
		printf("YES WE DID IT\n");
	for(int cpt = 0; cpt < getNbrCoup(p); cpt++)
		printf("%s\n",getHistoriqueCoup(p, cpt));
	return 0;
}