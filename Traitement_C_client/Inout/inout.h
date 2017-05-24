/**
* \file inout.h
* \brief Fonctions pour la sauvegarde et le chargement d'une partie
* \author Remi Meste
* \date 19 mai 2017
*/
#ifndef INOUT_H
#define INOUT_H
#include "../Plateau/plateau.h"

/** 
 *  @brief Sauvegarde des données d'un plateau dans un fichier
 *
 *	@param chaine de caractère pour le chemin vers le fichier de sauvegarde
 *	@param plateau
 *  @return un entier pour le code de retour, 0 en cas de réussite, 1 ou 2 sinon
 */
int ecrireDansFichier(const char *nomFichier, plateau plat);

/** 
 *  @brief Chargement des données d'un fichier dans un plateau
 *
 *	@param chaine de caractère pour le chemin du fichier à charger
 *  @return un plateau correctement chargé
 */
plateau lireFichier(const char* nomFichier);



#endif