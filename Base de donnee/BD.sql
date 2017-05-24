DROP TABLE JOUERCOUPS CASCADE CONSTRAINT;
DROP TABLE PARTIE CASCADE CONSTRAINT;
DROP TABLE JOUEUR CASCADE CONSTRAINT;

create table JOUEUR
    (pseudo VARCHAR2(30),
    email VARCHAR2(50),
    anneeN VARCHAR2(50),
    CONSTRAINT pk_dpseudo PRIMARY KEY (pseudo)
    );

create table PARTIE
    (idPartie NUMBER(6),
    taille NUMBER(2),
    sensj1 NUMBER(1),
    couleurj1 VARCHAR2(1),
    couleurj2 VARCHAR2(1),
    joueur1 VARCHAR2(30),
    joueur2 VARCHAR2(30),
    ladate DATE,
    gagnant VARCHAR2(30),
    CONSTRAINT pk_idPartie PRIMARY KEY (idPartie),
    CONSTRAINT fk_joueur1 FOREIGN KEY (joueur1)
                         REFERENCES JOUEUR (pseudo)  on delete cascade,
    CONSTRAINT fk_joueur2 FOREIGN KEY (joueur2)
                         REFERENCES JOUEUR (pseudo)  on delete cascade,
    CONSTRAINT fk_gagnant FOREIGN KEY (gagnant)
                         REFERENCES JOUEUR (pseudo)  on delete cascade,
    CONSTRAINT ck_taille CHECK (taille IS NOT NULL),
    CONSTRAINT ck_sensj1 CHECK ((sensj1 IS NOT NULL) AND (sensj1>=0) AND (sensJ1<2)),
    CONSTRAINT ck_couleurj1 CHECK (couleurj1 IS NOT NULL),
    CONSTRAINT ck_couleurj2 CHECK (couleurj2 IS NOT NULL),
    CONSTRAINT ck_joueur1 CHECK (joueur1 IS NOT NULL),
    CONSTRAINT ck_joueur2 CHECK (joueur2 IS NOT NULL),
    CONSTRAINT ck_ladate CHECK (ladate IS NOT NULL)
    );
    
create table JOUERCOUPS
    (pseudo VARCHAR2(30),
    idPartie NUMBER(6),
    numeroCoups NUMBER(4),
    lacase NUMBER(4),
    CONSTRAINT fk_pseudoJC FOREIGN KEY (pseudo)
                         REFERENCES JOUEUR (pseudo)  on delete cascade,
    CONSTRAINT fk_idPartieJC FOREIGN KEY (idPartie)
                         REFERENCES PARTIE (idPartie)  on delete cascade,
    CONSTRAINT pk_idJcoup PRIMARY KEY (pseudo,idPartie,numeroCoups),
    CONSTRAINT ck_lacase CHECK (lacase IS NOT NULL)
    );
--abandonner gagnant is null
--  numeroCoups=1 premier coups de la partie  (mumeroCoups=1 and   lacase=n) idPartie(JOUERCOUPS)=idPartie(PARTIE)
    