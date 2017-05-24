/**
* \file groupe.c
* \brief Fonctions pour la gestion du jeu sous forme de graphe +IA(+tard)
* \author Mathieu Popquillon
* \date 14 mai 2017
*/

//Dépendences libraries standard
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <time.h>

//Dépendences au projet
#include "groupe.h"

//variables globales
#define LARGMAX 20
#define LIENCASEDEB 6
bool chronoLance = false;

/***************************
 *          Types          *
 **************************/

typedef struct te_groupe {
    unsigned short int num;
    unsigned short int* liens ;  //0--65535  LARGEUR max = racine2 (65535 -4)
    unsigned short int nbLien;  //represente l'ensemble des groupes en contacte
    unsigned short int nbLienMax;
    unsigned  char afiliation;     // 3 cas possibles: 0=vide ,1=j1 ,2=j2
} unGroupe;


typedef struct te_ensemble {
    unsigned short int taille;
    groupe *cases; //plus de bord ils sont representé par les valeurs:
    //dessus=taille²
    //dessous=taille²+1
    //gauche=taille²+2
    //droite=taille²+3
} lensemble;

/***************************
        ACCESSEURS
 **************************/

groupe getCaseGroupe(ensemble e, int n){
    return(e->cases[n]);
}

char getAfiliation(ensemble e, int n){
    return(e->cases[n]->afiliation);
}

int getTailleEnsemble(ensemble e){
    return(e->taille);
}
int getCaseNum(ensemble e, int i){
    return(e->cases[i]->num);
}
/***************************
     Fonctions locales
 **************************/
//cases

//evaluateurs

//determine si le groupe c est gagnant dans un plateau de largeur taille dans le sens passé en parametre
bool groupeGagnant(groupe c, int taille, char sens) { //sens 0=haut-bas 1=gauche droite
    bool cot1 = false;
    bool cot2 = false;
    unsigned short int i1;
    unsigned short int i2;
    //les deux bords du plateau à relier pour gagner
    i1 = taille + sens * 2;
    i2 = taille + sens * 2 + 1;
    unsigned short int pos = 0;
    while ((pos < c->nbLien) && ((!cot1) || (!cot2))) {//parcours des liens du groupe à la recherche des liens vers les bords(i1 et i2)
        cot1 = ((cot1) || (i1 == c->liens[pos]));
        cot2 = ((cot2) || (i2 == c->liens[pos]));
        ++pos;
    }

    return (cot1 && cot2);
}
//prend le plateau de jeu e et le sens dans le quel joue le joueur 1 (0 h/b 1g/d)retourne 0 si pas de gagnant ,ou le numero du joueur gagnant (1 ou 2)
int unGagant(ensemble e, int sens) {
    unsigned short int pos1, pos2;
    groupe c1, c2, c3, c4;
    int taille;
    bool victoire = false;
    taille = e->taille;

    if (sens == 0) {
        c1 = e->cases[taille ];
        c2 = e->cases[taille + 1];
        c3 = e->cases[taille + 2];
        c4 = e->cases[taille + 3];
    }
    else {
        c3 = e->cases[taille ];
        c4 = e->cases[taille + 1];
        c1 = e->cases[taille + 2];
        c2 = e->cases[taille + 3];
    }
    pos1 = 0;
    while ((!victoire) && (c1->nbLien > pos1)) {
        if (e->cases [c1->liens[pos1]]->afiliation == 1) {
            pos2 = 0;
            while ((!victoire) && c2->nbLien > pos2) {
                victoire = (c1->liens[pos1] == c2->liens[pos2]);
                pos2++;
            }
        }
        pos1++;

    }
    if (victoire) {return (1);}
    pos1 = 0;
    while ((!victoire) && (c3->nbLien > pos1)) {
        if (e->cases [c3->liens[pos1]]->afiliation == 2) {
            pos2 = 0;
            while ((!victoire) && c4->nbLien > pos2) {
                victoire = (c3->liens[pos1] == c4->liens[pos2]);
                pos2++;
            }
        }
        pos1++;

    }
    if (victoire) {return (2);}
    else {return (0);}
}

//modificateurs

//ajoute au groupe c un lien vers le groupe de numero i (touts les liens font réference à des liens differents OK
void ajouterLien(groupe c, unsigned short int i) {
    bool deja = false;
    unsigned short int pos = 0;

    while ((pos < c->nbLien) && !deja) { //prcour les liens present et pour chercher le lien i
        deja = (i == c->liens[pos]);
        ++pos;
    }

    if (!deja) {  //si i non present l'ajouter
        if (c->nbLien >= c->nbLienMax) {//si plus de place augmenter taille tableau
            c->liens = realloc( c->liens, sizeof(unsigned short int) * (10 + c->nbLienMax));
            assert(c->liens != NULL);
            c->nbLienMax += 10;
        }
        c->liens[c->nbLien] = i;
        ++c->nbLien;
    }

}

//suprime toute presence de lien vers le groupe de numero i du groupe c OK
void supprimerLien(groupe c, unsigned short int i) {
    bool deja = false;
    unsigned short int pos = 0;
    while ((pos < c->nbLien) && !deja) {
        deja = (i == c->liens[pos]);
        if (deja) {
            c->liens[pos] = c->liens[c->nbLien - 1];
            --c->nbLien;
        }
        pos++;
    }
}

//réalise la fusion des groupes c1 et c2 appartenant à l'ensemble e,c1 récuperre l'ensemble des liens de c2 qui est supprimer tout les liens d'autres groupes vers c2 sont redirigé sur c1;
void souderGroupe(groupe c1, groupe c2, ensemble e) {
    assert(c1->afiliation == c2->afiliation);
    unsigned short int pos;
    supprimerLien(c1, c2->num);
    for (pos = 0; pos < c2->nbLien; ++pos) {//cas groupe pointé n'appartenant pas au joueur
        if (((c2->liens[pos]) >= (e->taille)) || (e->cases[c2->liens[pos]]->afiliation != c2->afiliation)) {
            ajouterLien(c1, c2->liens[pos]);
            supprimerLien(e->cases[c2->liens[pos]], c2->num);
            ajouterLien(e->cases[c2->liens[pos]], c1->num);

        }
        else { //cas groupe pointé apartenant au même joueur
            if (e->cases[c2->liens[pos]] != c1) {
                supprimerLien(e->cases[c2->liens[pos]], c2->num);
                souderGroupe(c1, e->cases[c2->liens[pos]], e); //ne va pas ajouter liens vers c1 à c2 mais va supprimer 'cpos'?(comprends plus)
            }
        }
    }
    e->cases[c2->num] = NULL;
    free(c2);

}

//generateurs

//crée une case de numero n OK
groupe creerCase(unsigned short int n) {
    groupe c = (groupe)malloc(sizeof(unGroupe));
    assert(c != NULL);
    c->liens = (unsigned short int*)malloc(sizeof(unsigned short int) * LIENCASEDEB);
    assert(c->liens != NULL);
    c->nbLien = 0;
    c->nbLienMax = 6;
    c->afiliation = 0;
    c->num = n;
    return (c);
}
//réalise une copie de la case g
groupe copierCase(groupe g) {
    if (g == NULL) {return (NULL);}
    groupe bis;
    bis = creerCase(g->num);
    for (int i = 0; i < g->nbLien; i++) {
        ajouterLien(bis, g->liens[i]);
    }
    bis->afiliation = g->afiliation;
    return (bis);
}

void freeCase(groupe g) {
   /* free(g->liens);
    free(g);*/
}

/***************************
 *          Méthodes       *
 **************************/

//le joueur de couleurs affiliation joue un coup  dans l'ensemble e sur la case non jouée de numero i et retourne si le coups était gagnant(sens indiquant les deux bords à joindre
bool jouerCoup(ensemble e, unsigned short int i, unsigned  char afiliation, unsigned  char sens ) {
    if (i >= e->taille)
        return (2);                    //2=problème taille
    if (e->cases[i] == NULL)
        return (3);                    //3=case déja jouée et soudée
    if (e->cases[i]->afiliation != 0)
        return (4);                    //4=case deja jouée

    bool soud = false;
    bool gagne = false;
    unsigned short int pos = 0;
    e->cases[i]->afiliation = afiliation;
    while (!soud && (pos < e->cases[i]->nbLien)) {
        if (e->cases[e->cases[i]->liens[pos]]->afiliation == e->cases[i]->afiliation) {
            groupe g;
            g = e->cases[e->cases[i]->liens[pos]];
            souderGroupe(g, (e->cases[i]), e);
            soud = true;
            gagne = groupeGagnant(g, e->taille, sens);
        }
        ++pos;
    }
    return gagne;
}

//créé un ensemble (structure representant le plateau sous formes des liens entres des groupes de casesp) de largeur n OK
ensemble creerEnsemble(int n) { //n la taille
    unsigned short int pos;
    assert(n <= LARGMAX);
    ensemble e = (ensemble)malloc(sizeof(lensemble));
    assert(e != NULL);
    e->taille = n * n;
    e->cases = (groupe *)malloc(sizeof(groupe) * (e->taille + 4));
    assert(e->cases != NULL);
    e->cases[n * n] = creerCase(n * n);
    e->cases[n * n + 1] = creerCase(n * n + 1);
    e->cases[n * n + 2] = creerCase(n * n + 2);
    e->cases[n * n + 3] = creerCase(n * n + 3);
    e->cases[n * n] = creerCase(n * n);
    for (pos = 0; pos < (n * n); pos++) {
        e->cases[pos] = creerCase(pos);
        if (pos < n) { //toute les cases n'apartenant pas à la ligne superieure reçoivent un lien vers la case du dessus
            ajouterLien(e->cases[pos], n * n); //dessus ou pas
            ajouterLien(e->cases[n * n], pos);
        }
        else //les autres vers le bord superieur
            ajouterLien(e->cases[pos], pos - n);

        if (pos >= n * (n - 1)) { //toute les cases n'apartenant pas à la ligne inferieure reçoivent un lien vers la case du dessous
            ajouterLien(e->cases[pos], n * n + 1); //dessous ou pas
            ajouterLien(e->cases[n * n + 1], pos);
        }
        else// les autres vers le bord inferieur
            ajouterLien(e->cases[pos], pos + n);


        if (pos % n == 0) { //toute les cases n'apartenant pas  au coté gauche reçoivent un lien vers la case à gauche
            ajouterLien(e->cases[pos], n * n + 2); // gauche ou pas
            ajouterLien(e->cases[n * n + 2], pos);
        }
        else// les autres vers le bord gauche
            ajouterLien(e->cases[pos], pos - 1);

        if ((pos + 1) % n == 0) { //toute les cases n'apartenant pas  au coté droit reçoivent un lien vers la case à droit
            ajouterLien(e->cases[pos], n * n + 3); // droite ou pas
            ajouterLien(e->cases[n * n + 3], pos);
        }
        else// les autres vers le bord droit
            ajouterLien(e->cases[pos], pos + 1);

        //deux derniers bords des cases n'etant pas sur les bords du plateau
        if (((pos + 1) % n != 0) && !(pos < n)) //ni bord droit ni dessus =>lien vers la case au dessus à gauche
            ajouterLien(e->cases[pos], pos - (n - 1));

        if ( (pos  % n != 0) && !(pos >= n * (n - 1)) )//ni  bord gauche ni en bas => lien vers la case  au dessous à droite
            ajouterLien(e->cases[pos], pos + (n - 1));

    }
    return (e);
}

//permet d'initialiser un ensemble à partir d'une grille de caracteres (tab) de largeur n , des caracteres representant j1 et j2 et du sens
ensemble chargerEnsemble(int n, char* tab, char joueur1, char joueur2, unsigned char sensj1) {
    ensemble e;
    bool probleme = false;
    e = creerEnsemble(n);
    for (int i = 0; i < n * n; i++) {
        if (tab[i] == joueur1) {
            probleme = jouerCoup(e, (unsigned short int)i, joueur1, sensj1);
        }
        else {
            if (tab[i] == joueur2) {
                probleme = jouerCoup(e, (unsigned short int)i, joueur2, ((sensj1 + 1) % 2));
            }
        }
        assert(probleme == false);
    }
    return (e);
}

ensemble copierEnsemble(ensemble e) {
    ensemble bis;
    int n, pos;
    n = e->taille;
    bis = (ensemble)malloc(sizeof(lensemble));
    assert(bis != NULL);
    bis->taille = n;
    bis->cases = (groupe *)malloc(sizeof(groupe) * (bis->taille + 4));
    assert(bis->cases != NULL);
    bis->cases[0] = creerCase(0);
    for (pos = 0; pos < n + 4; pos++) {
        bis->cases[pos] = copierCase(e->cases[pos]);
    }
    return (bis);
}

void suprimmerEnsemble(ensemble e) {
    for (int pos = 0; pos < (e->taille + 4); pos++) {
        freeCase(e->cases[pos]);
    }
    free(e);
}
/***************************
 *           IA            *
 **************************/
typedef struct chemin {
    int taille;
    unsigned short int numCase;
    struct chemin * begin;
}lechemin;
typedef struct chemin* passage;

//get and set
int getTaillePassage(passage p){return(p->taille);}
void setTaillePassage(passage p,int i){p->taille=i;}

unsigned short int getNCasePassage(passage p){return(p->numCase);}
void setNCasePassage(passage p, unsigned short int i){p->numCase=i;}

unsigned short int getProfond(passage p ,int n){
	assert(n>0);
	if(n<getTaillePassage(p)){return(getProfond(getBeginPassage(p),n));}
	else{return(getNCasePassage(p));}
}

passage getBeginPassage(passage p){return(p->begin);}
void setBeginPassage(passage p,passage b){p->begin=b;}

passage creeCheminBase(unsigned short int num){
    passage p;
    p=(passage)malloc(sizeof(struct chemin));
    p->taille=1;
    p->numCase=num;
    p->begin=NULL;
    return(p);
}


 passage ajouter(passage p, unsigned short int num){
    passage new=creeCheminBase(num);
	setBeginPassage(new,p);
    setTaillePassage(new,getTaillePassage(p)+1);
    return(new);
}

void affichePassage(passage p){
	printf(":%d",p->numCase);
	if (p->taille>1){
		affichePassage(p->begin);
		}
	else {printf("\n");}	
}

bool recherchePont(ensemble e,unsigned short int case1,unsigned short int case2,unsigned  char afiliation){
	groupe g1,g2,g3,g4;
	int cpt1,cpt2,cpt3,cpt4,nbchem,numCase;
	nbchem=0;
	g1=getCaseGroupe(e,case1);
	g2=getCaseGroupe(e,case2);
	cpt1=0;
	while((cpt1<g1->nbLien)&&(nbchem<2)){
		if(getAfiliation(e,g1->liens[cpt1])==afiliation){
			g3=getCaseGroupe(e,g1->liens[cpt1]);
			cpt3=0;
				while((cpt3<g3->nbLien)&&(nbchem<2)){
					cpt2=0;
					while((cpt2<g2->nbLien)&&(nbchem<2)){
						if(getAfiliation(e,g2->liens[cpt2])==afiliation){
							g4=getCaseGroupe(e,g2->liens[cpt2]);
							cpt4=0;
							while((cpt4<g4->nbLien)&&(nbchem<2)){
								if((g4->liens[cpt4]==g3->liens[cpt3])&&(g3->liens[cpt3]<getTailleEnsemble(e))&&(getAfiliation(e,g3->liens[cpt3])==0)){
									if(nbchem==0){
										numCase=g4->liens[cpt4];
										nbchem++;
									}
									else{
										if(g4->liens[cpt4]!=numCase){nbchem++;}
									}
								}
								cpt4++;
							}
						}
						else{
							if((g2->liens[cpt2]==g3->liens[cpt3])&&(getAfiliation(e,g2->liens[cpt2])==0)&&(g3->liens[cpt3]<getTailleEnsemble(e))){
								if(nbchem==0){
									numCase=g2->liens[cpt2];
									nbchem++;
								}
								else{
									if(g2->liens[cpt2]!=numCase){nbchem++;}
								}
							}
						}
						cpt2++;
					}
					cpt3++;
				}
			}
		else{	
			cpt2=0;
			while((cpt2<g2->nbLien)&&(nbchem<2)){
				if(getAfiliation(e,g2->liens[cpt2])==afiliation){
						g4=getCaseGroupe(e,g2->liens[cpt2]);
						cpt4=0;
						while((cpt4<g4->nbLien)&&(nbchem<2)){
							if((g4->liens[cpt4]==g1->liens[cpt1])&&(g1->liens[cpt1]<getTailleEnsemble(e))&&(getAfiliation(e,g1->liens[cpt1])==0)){
								if(nbchem==0){
									numCase=g4->liens[cpt4];
									nbchem++;
								}
								else{
									if(g4->liens[cpt4]!=numCase){nbchem++;}
								}
							}
							cpt4++;
						}
					}
					else{
						if((g2->liens[cpt2]==g1->liens[cpt1])&&(g2->liens[cpt2]<getTailleEnsemble(e))&&(getAfiliation(e,g2->liens[cpt2])==0)){
							if(nbchem==0){
								numCase=g2->liens[cpt2];
								nbchem++;
							}
							else{
								if(g2->liens[cpt2]!=numCase){nbchem++;}
							}
						}
					}
					cpt2++;
				}
			}
			cpt1++;
		}
	return(nbchem>1);
}
int IAdePont(ensemble e ,unsigned char afiliation, unsigned char sens){
	passage p1;
	int cpt,pos;
	cpt=0;
	unsigned short int* tab1;
	int *tabstat;
	p1=lePlusCourt(e ,afiliation,sens);
	tab1=(unsigned short int*)malloc(sizeof(unsigned short int) *getTaillePassage(p1)); 
	tabstat=(int*)malloc(sizeof(int) *getTaillePassage(p1));
	for(int pos= getTaillePassage(p1);pos>0;pos--){
		tab1[pos -1]=getProfond(p1,pos);
		tabstat[pos -1]=0;
	}
	for(int pos=0;pos+2<getTaillePassage(p1);pos++){
		if(recherchePont(e,tab1[pos],tab1[pos+2],afiliation)){
			tabstat[pos+1]=1;
			cpt++;
		}
	}
	if(getTaillePassage(p1)>=2){ 
		if(recherchePont(e,tab1[1],getTailleEnsemble(e) + sens *2,afiliation)){
				tabstat[0]=1;
				cpt++;
		}
		if(recherchePont(e,tab1[getTaillePassage(p1)-2],getTailleEnsemble(e) +1 + sens *2,afiliation)){
				tabstat[getTaillePassage(p1)-1]=1;
				cpt++;
		}
		}
	if((cpt==0)||(cpt>=getTaillePassage(p1))) {return(tab1[0]);}
	else{
		pos=0;
		while(tabstat[pos]==1){pos++;}
		return(tab1[pos]);
	}
}	
		
passage lePlusCourt(ensemble e , unsigned  char afiliation, unsigned  char sens ){
    passage tab[getTailleEnsemble(e)];
    int tabstat[getTailleEnsemble(e)]; //statut 0:non traitée  1:bout 2:traitée 
    bool fini=false;
	int postrait;
	passage gfin;
	int taillechem; 
    groupe g1,g2,gcourt,gtrait;
    for(int pos=0;pos<e->taille;pos++){
        tab[pos]=NULL;
        tabstat[pos]=0;
    }
	
	g1=getCaseGroupe(e,getTailleEnsemble(e) + sens *2);
	g2=getCaseGroupe(e,getTailleEnsemble(e) + 1 + sens *2);
	for(int pos1=0;pos1<g1->nbLien;pos1++){
		if((getAfiliation(e,g1->liens[pos1])==0)&&(tabstat[g1->liens[pos1]]==0)){ 
			tab[g1->liens[pos1]]=creeCheminBase(g1->liens[pos1]);
			tabstat[g1->liens[pos1]]=1;
		}
		else if(getAfiliation(e,g1->liens[pos1])==afiliation){
				gcourt=getCaseGroupe(e,g1->liens[pos1]);
				for(int pos2=0;pos2<gcourt->nbLien;pos2++){
					if((gcourt->liens[pos2]<getTailleEnsemble(e))&&(getAfiliation(e,gcourt->liens[pos2])==0)&&(tabstat[gcourt->liens[pos2]]==0)){
						tab[g1->liens[pos1]]=creeCheminBase(g1->liens[pos1]);
						tabstat[g1->liens[pos1]]=1;
					}
				}
		}
		
    }
	
	while(!fini){
		//trouver le plus court
		taillechem=getTailleEnsemble(e);
		for(int pos3=0;pos3<getTailleEnsemble(e);pos3++){
			if ((tabstat[pos3]==1)&&(tab[pos3]->taille<taillechem)){
				postrait=pos3;
				taillechem=tab[pos3]->taille;
			}
		}
		//initialisation
		gtrait=getCaseGroupe(e,(unsigned short int)postrait);
		
		tabstat[postrait]=2;
		//réaliser tout les chemins possibles
		for(int pos4=0;((pos4<gtrait->nbLien)&&(!fini));pos4++){
			
			fini=((gtrait->liens[pos4]==g2->num)||(fini));
			if(fini){
				gfin=tab[postrait];
			}

			
			if ((gtrait->liens[pos4]<e->taille)&&(tabstat[gtrait->liens[pos4]]==0)&&(e->cases[gtrait->liens[pos4]]->afiliation==0)){
				
				tab[gtrait->liens[pos4]]=ajouter(tab[postrait],gtrait->liens[pos4]);
				
				tabstat[gtrait->liens[pos4]]=1;
				
			}
			else if (e->cases[gtrait->liens[pos4]]->afiliation==afiliation){
					
					gcourt=getCaseGroupe(e,gtrait->liens[pos4]);
					for(int pos2=0;((pos2<gcourt->nbLien)&(!fini));pos2++){
					
						fini=((gcourt->liens[pos2])==g2->num||(fini));
						if(fini){gfin=tab[postrait];}
						
						if((gcourt->liens[pos2]<e->taille)&&(e->cases[gcourt->liens[pos2]]->afiliation==0)&&(tabstat[gcourt->liens[pos2]]==0)){
							tab[gcourt->liens[pos2]]=ajouter(tab[postrait],gcourt->liens[pos2]);
							tabstat[gcourt->liens[pos2]]=1;
						}
					}
			}
			
		}
	}
	return(gfin);
}	

int IasimpleChemin(ensemble e ,unsigned char afiliation,unsigned char afiautre  , unsigned char sens){
	passage p1,p2;
	int  cpt1=0;
	int cpt2=0;
	unsigned short int* tab1;
	unsigned short int* tab2;
	p1=lePlusCourt(e ,afiliation,sens);
	p2=lePlusCourt(e ,afiautre,(sens+1)%2);
	tab1=(unsigned short int*)malloc(sizeof(unsigned short int) *getTaillePassage(p1)); 
	tab2=(unsigned short int*)malloc(sizeof(unsigned short int) *getTaillePassage(p2));
	for(int pos= getTaillePassage(p1);pos>0;pos--){
		tab1[pos -1]=getProfond(p1,pos);
	}
	for(int pos1= getTaillePassage(p2);pos1>0;pos1--){
		tab2[pos1 -1]=getProfond(p2,pos1);
	}
	//comparaison
	while(tab1[cpt1]!=tab2[cpt2]){
		if(cpt2<getTaillePassage(p2)){cpt2++;}
		else{
			cpt1++;
			cpt2=0;
			
		}
	}
	return((int)tab1[cpt1]);
	
}

int evalEnsembleChemin(ensemble e ,unsigned char afiliation,unsigned char afiautre  , unsigned char sens){
	passage p1,p2;
	p1=lePlusCourt(e ,afiliation,sens);
	p2=lePlusCourt(e ,afiautre,(sens+1)%2);
	return(getTaillePassage(p2)-getTaillePassage(p1));	
}
//ia 2
int minMax(ensemble e,unsigned char j,unsigned char jsec,unsigned char sens,unsigned char jchercheG){
	printf("lancement minmax");
     bool fin;
     int res;
     ensemble bis;
     unsigned short int pos=0;
     fin=false;
    
     while(!fin){  //parcours l'ensemble des cases
         if ((e->cases[pos]!=NULL)&&(e->cases[pos]->afiliation==0)){ //jouables
             bis=copierEnsemble(e);
             if(jouerCoup(bis,pos,j,sens)){  //si le coups est gagnant remonter résultat
                 suprimmerEnsemble(bis);
                 if (j==jchercheG){return(pos);}
                 else{return(0);}
             }
            res=minMax(bis,jsec,j,(sens+1)%2,jchercheG);
            suprimmerEnsemble(bis);
            if (res!=0){return(pos);}
         }
         pos++;
         fin=(pos==e->taille);
     }
       return(0);
 }
 
int joueAlea(ensemble e) {
    int numb = 0;
    int tab[(e->taille)];
    if (!(chronoLance)) {
        srand((unsigned)time(NULL));
        chronoLance = true;
    }

    for (int i = 0; i < e->taille; i++) {
        if ((e->cases[i] != NULL) && (e->cases[i]->afiliation == 0)) {
            tab[i] = e->cases[i]->num;
            numb++;
        }
    }
    assert(numb != 0);
    return (tab[rand() % (numb)]) ;
}
 
//imbatable(si j1) sur petit plateau infinie sur grand >5? ,pas test
int JoueIAj1miniMax(ensemble e,unsigned char j,unsigned char jsec,unsigned char sens){
    int i;
    i=minMax(e,j,jsec,sens,j);
    if (i!=0){return(i);}
    else {return(joueAlea(e));}
}      
	
	
	
/***************************
 * fonctions de test :)    *
 **************************/

 

void affichegroupe(groupe p) {
    printf("num:%d nbLiens %d, nblienMax %d, afiliation %d :", p->num, p->nbLien, p->nbLienMax, p->afiliation);
    for (int i = 0; i < p->nbLien; i++) {
        printf(" %d,", p->liens[i]);
    }
    printf("\n");
}
void afficherTot(ensemble e) {
    for (int t = 0; t < e->taille + 4; t++) {
        if (e->cases[t] != NULL) {affichegroupe(e->cases[t]);}
    }
}
