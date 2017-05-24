#!/bin/sh
FICHIER_CHANGELOG="CHANGELOG"

#fichier de doc
rm -f FICHIER_CHANGELOG
ruby "./Documents de travail/Scripts/git-changelog-generator" > $FICHIER_CHANGELOG
echo "Mise a jour du fichier CHANGELOG" 

#compilation des modules
MODULES_PATH="./Traitement_C_client/"
HOME="../../../../"
FICHIER_LOGS="./logs_compilation.txt"

Modules="Inout Plateau Groupe"
#generation des modules
>$FICHIER_LOGS
for module in $Modules; do
    cd $MODULES_PATH && cd $module && make >> "$FICHIER_LOGS" && make clean >> "$FICHIER_LOGS" && cd $HOME  && echo " - $module - rebuild succes."
    if [ $? -eq 0 ]; then
	    echo "#compilation du module: $module [OK]"
    else
	    echo "#Compilation du module: $module [KO]"
    fi
done

#generation du client
echo "#Debut de la génération du client"
cd ./Client_jeu_hex/
ant >> "$FICHIER_LOGS"
if [ $? -eq 0 ]; then
	echo "#Génération du client [OK]"
else
	echo "#Génération du client [KO]"
fi
cd ../

#generation du launcher
echo "#Debut de la génération du launcher"
cd ./Launcher_jeu_hex/
ant >> "$FICHIER_LOGS"
if [ $? -eq 0 ]; then
    echo "#Génération du launcher [OK]"
else
    echo "#Génération du launcher [KO]"
fi
cd ../