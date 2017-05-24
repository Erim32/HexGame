#include <stdio.h>
#include <stdlib.h>
#include "inout.h"
#include "../Plateau/plateau.h"
int main(){
	plateau p;
	p = creerPlateau(5);
	p = initialiserPlateau(p);
	jouer(p, '*', 0, 0);
	jouer(p, 'o', 1, 1);
    jouer(p, '*', 1, 0);
    printf("\tProgramme de test du module Inout\t\n");
    printf("#Sauvegarder\n");
    ecrireDansFichier("test.out", p );
    affichage(p);
    printf("#Charger:\n");
    p = lireFichier("test.out");
    affichage(p);
    return 0;
}
