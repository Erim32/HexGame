#include <stdbool.h>
#include <assert.h>

#include "interfaceJavaC.h"
#include "../Plateau/plateau.h"
#include "../Inout/inout.h"
#include "../Groupe/groupe.h"


//TEMPORAIRE POUR L ALEATOIRE
#include <stdlib.h>
#include <time.h>
#include <stdio.h>  
//TEMPORAIRE POUR L ALEATOIRE

plateau plat;

JNIEXPORT void JNICALL Java_interfaces_InterfaceJavaC_debutPartie
  (JNIEnv * env, jclass nomClasse, jint taille){
    plat = creerPlateau(taille);
    plat = initialiserPlateau(plat);
    
  }

JNIEXPORT jchar JNICALL Java_interfaces_InterfaceJavaC_getCase 
  (JNIEnv * env, jclass nomClasse, jint x, jint y){ 
    assert(plat != NULL); 
    return getCase(plat, x,y); 
  } 
 
JNIEXPORT jboolean JNICALL Java_interfaces_InterfaceJavaC_jouer 
  (JNIEnv * env, jclass nomClasse, jint x, jint y, jchar couleurJoueur){ 
    if( (x-1 < getTaille(plat)) && 
        (y-1 < getTaille(plat)) && (getCase(plat, (x-1), y-1) == '.')){  
        jouer(plat,couleurJoueur,x-1,y-1); 
        return true; 
    }
    return false;
  }

JNIEXPORT jboolean JNICALL Java_interfaces_InterfaceJavaC_retourArriere 
  (JNIEnv * env, jclass nomClasse){ 
    plat = retourArriere(plat);
    if(plat != NULL)
        return true;
    else
        return false;
  }

JNIEXPORT jint JNICALL Java_interfaces_InterfaceJavaC_sauvegarder
  (JNIEnv* env, jclass nomClasse, jstring nomFichierASauvegarder){
    
    const char* nom = (*env)->GetStringUTFChars(env, nomFichierASauvegarder, 0);
    int retour = ecrireDansFichier(nom, plat);
    (*env)->ReleaseStringUTFChars(env, nomFichierASauvegarder, nom);
    
    return retour;
  }


JNIEXPORT jboolean JNICALL Java_interfaces_InterfaceJavaC_charger
  (JNIEnv * env, jclass nomClasse, jstring nomFichierACharger){

    const char* nom = (*env)->GetStringUTFChars(env, nomFichierACharger, 0);
    plat = chargerPlateau(plat, nom);
    (*env)->ReleaseStringUTFChars(env, nomFichierACharger, nom);
    if(plat != NULL)
      return true;
    else
      return false;
  }

JNIEXPORT void JNICALL Java_interfaces_InterfaceJavaC_supprimerPlateau
  (JNIEnv * env, jclass nomClasse){
    supprimerPlateau(plat);
  }

JNIEXPORT jboolean JNICALL Java_interfaces_InterfaceJavaC_estTerminePartie
  (JNIEnv * env, jclass nomClasse){
    return (vainqueur(plat));
  }

JNIEXPORT jint JNICALL Java_interfaces_InterfaceJavaC_getTaille
  (JNIEnv * env, jclass nomClasse){
    return(getTaille(plat));
  }

JNIEXPORT jint JNICALL Java_interfaces_InterfaceJavaC_getNbrCoup
  (JNIEnv * env, jclass nomClasse){
    return(getNbrCoup(plat));
  }

JNIEXPORT void JNICALL Java_interfaces_InterfaceJavaC_coupIA
  (JNIEnv *env, jclass jclass, jchar couleur, jint niveauDifficulte){
    //generation d'un nombre alétoire
    int vide = 0, coup;
    switch(niveauDifficulte){
      case 0: 
        srand(time(NULL));
        int nbgen;
        do{
          nbgen = rand()%(getTaille(plat)*getTaille(plat));   //max = nombre de case
        }while(getCase(plat, nbgen/getTaille(plat), nbgen%getTaille(plat)) != '.');
        jouer(plat,couleur,nbgen/getTaille(plat),nbgen%getTaille(plat));
        break;
      case 1:
        if(couleur == 'o'){
          while(vide == 0){
            coup = IasimpleChemin(getEnsemble(plat),'o','x',0);
            vide = caseVide(plat,coup/getTaille(plat), coup%getTaille(plat));
          }
        } else {
          while(vide == 0){
            coup = IasimpleChemin(getEnsemble(plat),'x','o',1);
            vide = caseVide(plat,coup/getTaille(plat), coup%getTaille(plat));
          }
        }
          jouer(plat, couleur, coup/getTaille(plat), coup%getTaille(plat));
        break;
      case 2:
		    if(couleur == 'o'){
          while(vide == 0){
            coup =IAdePont(getEnsemble(plat),'o',0);
            vide = caseVide(plat,coup/getTaille(plat), coup%getTaille(plat));
          }
        } else {
          while(vide == 0){
            coup = IAdePont(getEnsemble(plat),'x',1);
            vide = caseVide(plat,coup/getTaille(plat), coup%getTaille(plat));
          }
        }
          jouer(plat, couleur, coup/getTaille(plat), coup%getTaille(plat));
        break;
      default:
        printf("Problème lors du coup de l'IA");
        break;
    }
 }
