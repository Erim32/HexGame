//Dépendences externes
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//Dépendences propres au projet
#include "inout.h"

int ecrireDansFichier(const char *nomFichier, plateau plat) {
  FILE* fichier = NULL;
  fichier = fopen(nomFichier, "w");

  if (fichier == NULL) {
    fprintf(stderr, "Impossible de creer le fichier: %s\n", nomFichier);
    return 1;
  } else {
    int taille = getTaille(plat);
    fprintf(fichier, "\\hex\n\\dim %d\n\\board\n", taille);
    for (int y = 0; y < taille; y++) {
      for (int x = 0; x < taille; x++) {
        fputc(getCase(plat, y, x), fichier);
        fputc(' ', fichier);
      }
      fputc('\n', fichier);
    }
    fprintf(fichier, "\\endboard\n\\game\n");
    for (int i = 0; i < getNbrCoup(plat); i++) {
      fprintf(fichier, "\\play %s\n", getHistoriqueCoup(plat, i));
    }
    fprintf(fichier, "\\endgame\n\\endhex");
  }
  //fermeture
  if (fclose(fichier) != 0) {
    fprintf(stderr, "Impossible de fermer le fichier %s\n", nomFichier );
    return 2;
  }
  return 0;
}


plateau lireFichier(const char* nomFichier) {

  plateau platDejaSauvegarde;
  FILE *fichier = fopen (nomFichier, "r" );
  char* grille;
  int positionGrille = 0;
  if ( fichier != NULL ) {
    char ligne[128];

    while ( fgets ( ligne, sizeof ligne, fichier ) != NULL ) {
      //detection des cas:

      //recuperation de la taille \dim
      if ((ligne[0] == '\\') && (ligne[1] == 'd')) {
        int taille = atoi(&ligne[5]);
        platDejaSauvegarde = creerPlateau(taille);
        grille = (char*)malloc(sizeof(char) * (taille * taille));
      }

      //recuperation de lagrille
      if ((ligne[0] != '\\') && (ligne[0] != '\0') && (ligne[0] != '\n')) {
        char charCourant;
        for (int i = 0; i < strlen(ligne); i++) { //ne pas prendre les espaces
          charCourant = ligne[i];
           if ((charCourant == '.') || (charCourant == 'x') || (charCourant == 'o')) {
           grille[positionGrille++] = charCourant;
          }
        }
      }

      if ((ligne[0] == '\\') && (ligne[1] == 'p')){
        platDejaSauvegarde =  ajouterHistoriqueCoups(platDejaSauvegarde, ligne[6], atoi(&ligne[8]), atoi(&ligne[10]));
      }
      
    }
    //maj grille
    platDejaSauvegarde =  setGrille(platDejaSauvegarde, grille);
    fclose (fichier);
  } else {
    perror (nomFichier);
  }
  return platDejaSauvegarde;
}
