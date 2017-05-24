/**
* \file plateau.h
* \brief Fonctions pour la gestion du plateau de jeu
* \author Thomas Lyle
* \date 19 mai 2017
*/

//Dépendences libraries standard
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

//Dépendences au projet
#include "plateau.h"
#include "../Groupe/groupe.h"
#include "../Inout/inout.h"

/***************************
 *          Types          *
 **************************/

typedef struct etCoup {
    char couleur;
    int ligne;
    int colonne;
} coupInt;

typedef struct etPlateau {
    ensemble ensPlat;
    int aTerminer; // 0 si la partie n'est pas terminée, 1 sinon
    coup tabCoup;
    int nbrCoup;
    char * grille;
    int taille; //nombre de case dans une ligne ou une colonne

} plateauInt;

/***************************
     Fonctions locales  
 **************************/

int caseVide(plateau plat, int ligne, int colonne){
    return (plat->grille[(plat->taille * ligne) + colonne] == '.');
}
/***************************
 *       ACCESSEURS        *
 **************************/
/*
 * GET
 */
 
 
ensemble getEnsemble(plateau plat){
    assert(plat != NULL);
    return(plat->ensPlat);
}
 
int getTaille(plateau plat) {
    assert(plat != NULL);
    return (plat->taille);
}

char getCase(plateau plat, int x, int y) {
    assert(plat != NULL);
    assert( x >= 0 && x <= getTaille(plat));
    assert( y >= 0 && y <= getTaille(plat));
    return (plat->grille[(getTaille(plat) * x) + y]);
}

int getNbrCoup(plateau plat){
    return(plat->nbrCoup);
}

char* getHistoriqueCoup(plateau plat, int numeroCoup){
    assert(numeroCoup < plat->nbrCoup);
    char *coup = (char*)malloc(sizeof(char)*10);
    //format "couleur ligne col"
    sprintf(coup,"%c %d %d",plat->tabCoup[numeroCoup].couleur, plat->tabCoup[numeroCoup].ligne, plat->tabCoup[numeroCoup].colonne); 
    return coup;
}

char * getGrille(plateau plat){
    return plat->grille;
}

/*
 * SET
 */
plateau setGrille(plateau plat, char *grille){
    plat->grille = grille;
    return plat;
}

plateau ajouterHistoriqueCoups(plateau plat, char couleur, int ligne, int colonne){
    if (caseVide(plat, ligne, colonne))
        plat = placerPion(plat, couleur, ligne, colonne);
    plat->tabCoup[plat->nbrCoup].ligne = ligne;
    plat->tabCoup[plat->nbrCoup].colonne = colonne;
    plat->tabCoup[plat->nbrCoup].couleur = couleur;
    plat->nbrCoup++;
    return plat;
}

/***************************
 *          Méthodes       *
 **************************/
plateau creerPlateau(int n) {
    //nombre de case total que le plateau contiendra
    int tailleTotale = n * n;
    char *taillePlat = (char *)malloc(sizeof(char) * tailleTotale);
    assert(taillePlat != NULL);
    plateau plat = (plateau)malloc(sizeof(plateauInt));
    assert(plat != NULL);
    plat->tabCoup = (coup)malloc(sizeof(coupInt) * tailleTotale);
    assert(plat->tabCoup != NULL);
    plat->nbrCoup = 0;
    plat->grille = taillePlat;
    plat->taille = n;
    plat->aTerminer = 0;
    plat->ensPlat=creerEnsemble(n);
    return (plat);
}

plateau placerPion(plateau plat, char couleur, int ligne, int colonne) {
    plat->grille[(plat->taille * ligne) + colonne] = couleur;
    return plat;
}

void jouer(plateau plat, char couleur, int ligne, int colonne) {
    int vainqueur;
    int caseJouer = (plat->taille * ligne) + colonne;
    plat =  ajouterHistoriqueCoups(plat, couleur, ligne, colonne);
    if(couleur == 'x')
        vainqueur = jouerCoup(plat->ensPlat,caseJouer,couleur,1);
    else
        vainqueur = jouerCoup(plat->ensPlat,caseJouer,couleur,0);
    plat->aTerminer = vainqueur;
}
 // 0 si pas terminer, 1 sinon
int vainqueur(plateau plat){
    return(plat->aTerminer);
}

plateau initialiserPlateau(plateau plat) {
    for (int i = 0; i < plat->taille; ++i)
        for (int j = 0; j < plat->taille; ++j)
            placerPion(plat, '.', i, j);
    return plat;
}

plateau retourArriere(plateau plat){
    if(plat->nbrCoup != 0){
        plat = placerPion(plat, '.', plat->tabCoup[plat->nbrCoup - 1].ligne, plat->tabCoup[plat->nbrCoup - 1].colonne);
        plat->ensPlat = chargerEnsemble(plat->taille,plat->grille, 'x','o',0);
        plat->nbrCoup --;
    }
    return plat;
}

void supprimerPlateau(plateau plat){
    suprimmerEnsemble(plat->ensPlat);
    free(plat->tabCoup);
    free(plat->grille);
    free(plat);
}

plateau chargerPlateau(plateau plat, const char* fich){
    plat = lireFichier(fich);
    plat->ensPlat = chargerEnsemble(plat->taille,plat->grille, 'x','o',0);
    return plat;
}
/***************************
 *          Tests          *
 **************************/
void saisie(plateau plat, char couleur) {
    int lig, col;
    printf("Saisissez les coordonnées de la case: ");
    scanf("%d", &lig);
    scanf("%d", &col);
    while ((lig > plat->taille) || (col > plat->taille) || (!caseVide(plat, lig - 1, col - 1))) {
        printf("\nSaisie incorrect, recommencez: ");
        scanf("%d", &lig);
        scanf("%d", &col);
    }
    jouer(plat, couleur, lig - 1, col - 1);
}

void affichage(plateau plat) {
    int cptEspace, cptGeneral, cptTab;
    cptEspace = 1;
    for (cptGeneral = 0; cptGeneral < plat->taille + 2; cptGeneral++) {
        if (cptGeneral > 1) {
            for (int j = 0; j < cptEspace; j++)
                printf(" ");
            cptEspace++;
        }
        if (cptGeneral == 0 || cptGeneral == plat->taille + 1) {
            for (int i = 0; i < plat->taille + 2; i++)
                printf("R ");
            printf("\n");
        } else {
            printf("B ");
            for (cptTab = 0; cptTab < plat->taille; cptTab++) {
                printf("%c ", plat->grille[(plat->taille * (cptGeneral - 1)) + cptTab]);
            }
            printf("B\n");
        }
    }
}
