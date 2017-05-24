#include <stdlib.h>
#include <stdio.h>
#include "groupe.h"


int main(int argc, char const *argv[])
{
    //groupe p1,p2,p3;
    //ensemble e1,e2,e3;
    ensemble e3;
	/*p1=creerCase(1);
        p2=creerCase(2);
        p3=creerCase(3);
        for(int i= 0;i<10000;i++){//max
            ajouterLien(p1,i);
        }
        affichegroupe(p1);
        sleep(1);
        for(int i= 25;i>10;i--){
            ajouterLien(p2,i);
            affichegroupe(p2);
        }
        for(int i= 1;i<15;i++){
            supprimerLien(p2,i);
            affichegroupe(p2);
            sleep(1);
        }
        for(int i= 30;i>10;i--){
            ajouterLien(p2,i);
            affichegroupe(p2);
        }
        */
        /*e1=creerEnsemble(3);
        afficherTot(e1);
        jouerCoup(e1,0,1,0);
        jouerCoup(e1,1,1,0);
        printf("plat 1\n");
        afficherTot(e1);
        printf("passage 2\n");
        e2=copierEnsemble(e1);
        printf("plat 2\n");
        afficherTot(e2);
        jouerCoup(e2,3,1,0);
        printf("plat 1\n");
        afficherTot(e1);
        printf("plat 2\n");
        afficherTot(e2);*/
		/*passage pa,pa2;
		pa=creeCheminBase(2);
		printf("taille: %d casenum: %d\n",getTaillePassage(pa),getNCasePassage(pa));
		pa2=ajouter(pa,3);
		printf("taille: %d casenum: %d\n",getTaillePassage(pa2),getNCasePassage(pa2));*/
        char tab[16]={'j','j','j','j','j','j','x','j','j','j','j','j','j','x','j','j'};
		/*e3=creerEnsemble(3);
		int i;*/
        e3=chargerEnsemble(4,tab, 'x','o',0);
        afficherTot(e3);
		passage f1,f2;
		int i;
		f1=lePlusCourt(e3 ,'o',0);
		f2=lePlusCourt(e3 ,'x',1);
		
		if(recherchePont(e3,1,11,'x')){printf("pont\n");}
		else{printf("pas de pont\n");}
		f1=lePlusCourt(e3 ,'x',0);
		affichePassage(f1);
		i=IAdePont(e3 ,'x',0);
		/*affichePassage(f2);
		i=IasimpleChemin(e3 ,'o','x' ,0);
		i=JoueIAj1miniMax(e3,'x','o',1);*/
		printf("jouer en :%d\n",i);
		
        
        
        
        
}