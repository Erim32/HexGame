#include <stdio.h>

#include "plateau.h"

int main(){
	plateau p;
	int n, quitter, saisieChoix;
	char joueur = '*';
	quitter = 0;
	printf("Saisir la taille du plateau de jeu:");
	scanf("%d",&n);
	p = creerPlateau(n);
	p = initialiserPlateau(p);
	while(!quitter){
		printf("Entrez votre choix\n1) Jouer   2) Sauvegarder   3) Retour arriere   4) Quitter");
		scanf("%d",&saisieChoix);
		switch(saisieChoix){
			case 1: 
				saisie(p,joueur);
				affichage(p);
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
		if (joueur == '*')
			joueur = 'o';
		else
			joueur = '*';
	}
	for(int cpt = 0; cpt < getNbrCoup(p); cpt++)
		printf("%s\n",getHistoriqueCoup(p, cpt));
	return 0;
}