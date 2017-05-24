/**
* \file plateau.h
* \brief Fonctions pour la gestion du plateau de jeu
* \author Thomas Lyle
* \date 19 mai 2017
*/

#ifndef PLATEAU_H
#define PLATEAU_H

#include "../Groupe/groupe.h"

/***************************
 *          TYPES          *
 **************************/
/**
 *	@brief Definiton du type abstrait de donnée plateau
 */
typedef struct etPlateau * plateau;

/**
 *	@brief Definiton du type abstrait de donnée coup
 */
typedef struct etCoup * coup;


/***************************
 *          ACCESSEURS     *
 **************************/
/** 
 *  @brief Accesseur pour l'ensemble dans le plateau
 *
 *	@param plateau
 *  @return l'ensemble du plateau
 */
ensemble getEnsemble(plateau plat);

/** 
 *  @brief Accesseur pour la taille du plateau
 *
 *	@param plateau
 *  @return la taille du plateau
 */ 
int getTaille(plateau plat);

/** 
 *  @brief Accesseur pour une case du plateau
 *
 *	@param plateau
 *	@param deux entiers
 *  @return la case de coordonnées (x;y) du plateau
 */ 
char getCase(plateau plat, int x, int y);

/** 
 *  @brief Accesseur pour le nombre de coup du plateau
 *
 *	@param plateau
 *  @return le nombre de coup du plateau
 */ 
int getNbrCoup(plateau plat);

/** 
 *  @brief Accesseur pour una case du plateau
 *
 *	@param plateau
 *	@param entier pour le numero du coup
 *  @return une chaine de caractère avec le joueur et les coordonnées de la case
 */ 
char* getHistoriqueCoup(plateau plat, int numeroCoup);

/** 
 *  @brief Accesseur pour la grille du plateau
 *
 *	@param plateau
 *  @return la grille du plateau
 */ 
char * getGrille(plateau plat);

/** 
 *  @brief Ajoute une grille au plateau
 *
 *	@param plateau
 *	@param chaine de caractère
 *  @return un plateau avec une grille
 */ 
plateau setGrille(plateau plat, char *grille);

/** 
 *  @brief Ajoute d'une ligne dans l'historique des coup
 *
 *	@param plateau
 *	@param caractère pour la couleur du joueur
 *	@param deux entiers pour les coordonnées
 *  @return un plateau avec un historique des coups modifiés
 */ 
plateau ajouterHistoriqueCoups(plateau plat, char couleur, int ligne, int colonne);

/***************************
 *          Méthodes       *
 **************************/

/** 
 *  @brief Creer un plateau
 *
 *	@param plateau
 *	@param entier pour la largeur de la grille
 *  @return un plateau correctement créé
 */ 
plateau creerPlateau(int n);

/** 
 *  @brief Accesseur pour l'entier ATerminer du plateau
 *
 *	@param plateau
 *  @return l'entier pour savoir si la partie est terminée
 */ 
int vainqueur(plateau plat);

/** 
 *  @brief Joue un coup par le joueur couleur a la case de coordonnées (x;y) du plateau, dans l'ensemble et dans l'historique des coups
 *
 *	@param plateau
 *	@param character pour la couleur du joueur
 *	@param deux entier pour les coordonnées de la case
 */ 
void jouer(plateau plat, char couleur, int ligne, int colonne);

/** 
 *  @brief Joue un coup par le joueur couleur a la case de coordonnées (x;y) du plateau
 *
 *	@param plateau
 *	@param character pour la couleur du joueur
 *	@param deux entier pour les coordonnées de la case
 *	@return le plateau avec le pion correctement placé
 */ 
plateau placerPion(plateau plat, char couleur, int ligne, int colonne);

/** 
 *  @brief Initialise toutes les cases du plateau a vide (caractère '.')
 *
 *	@param plateau
 *	@return Un plateau correctement initialisé
 */ 
plateau initialiserPlateau(plateau plat);

/** 
 *  @brief Effetue un retour arrière sur le plateau et sur l'ensemble du plateau
 *
 *	@param plateau
 *	@return Un plateau a l'état précédant le dernier coup
 */ 
plateau retourArriere(plateau plat);

/** 
 *  @brief Libère les espaces mémoires utlisés par la plateau
 *
 *	@param plateau
 */ 
void supprimerPlateau(plateau plat);

/** 
 *  @brief Charge un plateau à partir d'un fichier
 *
 *	@param plateau
 *	@param une chaine de caractère pour le chemin du fichier à charger
 *	@return Un plateau correctement chargé
 */ 
plateau chargerPlateau(plateau plat, const char* fich);

/** 
 *  @brief Test si la case aux coordonnées (ligne;colonne) est vide
 *
 *	@param plateau
 *	@param deux entiers pour les coordonnées de la case
 *	@return 1 si la case est vide, 0 sinon
 */ 
int caseVide(plateau plat, int ligne, int colonne);

/** 
 *  @brief Fonction de test pour la saisie d'un coup dans le plateau
 *
 *	@param plateau
 *	@param un caractère pour la couleur du joueur 
 */ 
void saisie(plateau plat, char couleur);

/** 
 *  @brief Fonction de test pour l'affichage du plateau plateau
 *
 *	@param plateau
 */ 
void affichage(plateau plat);

#endif