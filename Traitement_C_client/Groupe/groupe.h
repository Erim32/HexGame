/**
* \file groupe.h
* \brief Fonctions pour la mise sous forme  de groupements liés (structure de type graph) pour detectiongagnant et Ia
* \author Mathieu POQUILLON
* \date 20 mai 2017
*/

#ifndef GROUPE_H
#define GROUPE_H
//Dépendances
#include <stdbool.h>



/***************************
 *          TYPES          *
 **************************/
 /**
 *	@brief Definiton du type abstrait de donnée groupe
 */
typedef struct te_groupe* groupe;


/**
 *	@brief Definiton du type abstrait de donnée ensemble
 */
typedef struct te_ensemble* ensemble;


/**
 *	@brief Definiton du type abstrait de donnée passage
 */
typedef struct chemin* passage;




/***************************
 *          ACCESSEURS     *
 **************************/
 
 /** 
 *  @brief getter pour un groupe de l'ensemble
 *
 *	@param ensemble
 *  @param integer
 *  @return le groupe de num (integer) dans (ensemble)
 */
groupe getCaseGroupe(ensemble e, int n);

/** 
 *  @brief getter pour l'afiliation de la case de l'ensemble
 *
 *	@param ensemble
 *  @param integer
 *  @return le joueur possadant le groupe de num (integer) dans (ensemble)
 */
char getAfiliation(ensemble e, int n);

/** 
 *  @brief getter pour la taille de l'ensemble
 *	@param ensemble
 *  @return la taille de(ensemble)
 */
int getTailleEnsemble(ensemble e);

/** 
 *  @brief getter pour le numero de la case de l'ensemble
 *  @param integer
 *	@param ensemble
 *  @return (integer) 
 */
int getCaseNum(ensemble e, int i);//=i :)

/** 
 *  @brief getter pour la taille d'un passage
 *	@param passage
 *  @return (integer) 
 */
int getTaillePassage(passage p);

/**
 *  @brief setter pour la taille d'un passage
 *  @param passage
 */
void setTaillePassage(passage p,int i);


/**
 *  @brief getter pour la case du passage
 *  @param passage
 *  @return (unsigned short integer) 
 */
unsigned short int getNCasePassage(passage p);

/**
 *  @brief setter pour la case du passage
 *  @param passage
 *  @param unsigned short int 
 */
void setNCasePassage(passage p, unsigned short int i);

/**
 *  @brief getter pour la case du passage en profondeur
 *  @param passage
 *  @param int
 *  @return (unsigned short integer) 
 */
unsigned short int getProfond(passage p ,int n);

/**
 *  @brief getter pour le passage a l'origine du passage
 *  @param passage
 *  @return (passage) 
 */
passage getBeginPassage(passage p);

/**
 *  @brief setter pour le passage a l'origine du passage
 *  @param passage
 *  @param passage2
 */
void setBeginPassage(passage p,passage b);

/***************************
 *          Méthodes       *
 **************************/


/**
 *  @brief permet de jouer un coup dans l'ensemble 
 *  @param ensemble sur lequel jouer
 *  @param unsigned short int le numero de la case ou l'on joue
 *  @param unsigned  char la couleur du joueur
 *  @param unsigned  char le sens dans il doit gagner
 *  @return bool le coups est gagnant 
 */
bool jouerCoup(ensemble e, unsigned short int i, unsigned  char afiliation, unsigned  char sens );

/**
 *  @brief crée l'ensemble de largeur n vierge de tout coups
 *  @param int la largeur de l'ensemble
 *  @return ensemble
 */
ensemble creerEnsemble(int n);

//permet d'initialiser un ensemble à partir d'une grille de caracteres (tab) de largeur n , des caracteres representant j1 et j2 et du sens

/**
 *  @brief crée l'ensemble de largeur  n à partir d'un tableau de coups
 *  @param int la largeur de l'ensemble
 *  @param char * le tableau des coups
 *  @param char joueur1 couleur du joueur 1
 *  @param char joueur2 couleur du joueur 2
 *  @param unsigned char le sens dans lequel joue le joueur 1
 *  @return ensemble
 */
ensemble chargerEnsemble(int n,char* tab, char joueur1,char joueur2,unsigned char sensj1);

/**
 *  @brief copie l'ensemble en parametre 
 *  @param ensemble l'ensemble à copier
 *  @return ensemble copie
 */
ensemble copierEnsemble(ensemble e);

/**
 *  @brief supprime l'ensemble de maniere propre,bug noté
 *  @param ensemble l'ensemble à supprimer
 */
void suprimmerEnsemble(ensemble e);

/**
 *  @brief crée un chemin 
 *  @param unsigned short int la case du passage
 *  @return le passage créé
 */
passage creeCheminBase(unsigned short int num);


/**
 *  @brief ajoute un chemin menant au chemin 
 *  @param unsigned short int la case du nouveau passage
 *  @param passage origine
 *  @return le passage  créé
 */
passage ajouter(passage p, unsigned short int num);

/**
 *  @brief cherche la presence d'un pont en tre deux cases
 *  @param ensemble l'ensemble concerné 
 *  @param unsigned short int la case1
 *  @param unsigned short int la case2
 *  @param unsigned  char la couleur du joueur 
 *  @return si il y a un pont entre les deux cases 
 */
bool recherchePont(ensemble e,unsigned short int case1,unsigned short int case2,unsigned  char afiliation);

/**
 *  @brief cherche le chemin le plus cours
 *  @param ensemble l'ensemble concerné 
 *  @param unsigned  char la couleur du joueur 
 *  @param unsigned  char le sens du joueur pour gagner
 *  @return le chemin le plus cours 
 */
passage lePlusCourt(ensemble e , unsigned  char afiliation, unsigned  char sens );

/**
 *  @brief recherche de la case à jouer grace à l'intercection des chemins les plus cours des deux joueurs
 *  @param ensemble l'ensemble concerné 
 *  @param unsigned  char la couleur du joueur 
 *  @param unsigned  char la couleur de son adversaire 
 *  @param unsigned  char le sens du joueur pour gagner
 *  @return le numero de la case à jouer 
 */
int IasimpleChemin(ensemble e ,unsigned char afiliation,unsigned char afiautre  , unsigned char sens);

/**
 *  @brief recherche de la case à jouer grace à au plus cours chemin du joueur et un étude des ponts
 *  @param ensemble l'ensemble concerné 
 *  @param unsigned  char la couleur du joueur 
 *  @param unsigned  char le sens du joueur pour gagner
 *  @return le numero de la case à jouer 
 */
int IAdePont(ensemble e ,unsigned char afiliation, unsigned char sens);

/**
 *  @brief fonction d'evaluation de la valeur de l'ensemble pour la victoire d'un joueur 
 *  @param ensemble l'ensemble concerné 
 *  @param unsigned  char la couleur du joueur 
 *  @param unsigned  char la couleur de son adversaire 
 *  @param unsigned  char le sens du joueur pour gagner
 *  @return la valeur estimer du plateau 
 */
int evalEnsembleChemin(ensemble e ,unsigned char afiliation,unsigned char afiautre  , unsigned char sens);

/**
 *  @brief recherche de la case à jouer par le parcours de l'arbre des possibles
 *  @param ensemble l'ensemble concerné 
 *  @param unsigned  char la couleur du joueur 
 *  @param unsigned  char la couleur de son adversaire 
 *  @param unsigned  char le sens du joueur pour gagner
 *  @return le numero de la case à jouer  
 */
int JoueIAj1miniMax(ensemble e,unsigned char j,unsigned char jsec,unsigned char sens);


/**
 *  @brief affiche l'ensemble du chemin passé
 *  @param passage  
 */
void affichePassage(passage p);

/**
 *  @brief affiche les caracteristiques du groupe 
 *  @param groupee 
 */
void affichegroupe(groupe p);

/**
 *  @brief affiche les caracteristiques des groupes de l'ensemble
 *  @param ensemble  
 */
void afficherTot(ensemble e);



#endif
