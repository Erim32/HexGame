CC=gcc
CFLAGS=-Wall -std=c99 
#-DNDEBUG
LDFLAGS= -lm
OBJ= interfaceJavaC.o 
 
all:	$(OBJ)
	$(CC) $(OBJ) $(CFLAGS) -o interfaceJavaC $(LDFLAGS)
interfaceJavaC.o: interfaceJavaC.c interfaceJavaC.h
clean:
	rm -rf *.o
maxclean: 
	clean
	rm -rf $(EXEC)

#utile pour la comilation du .h
generation:
	gcc -c -fPIC interfaceJavaC.c
	gcc -c -fPIC ../Plateau/plateau.c
	gcc -c -fPIC ../Groupe/groupe.c
	gcc -c -fPIC ../Inout/inout.c
	gcc -shared -o ../../Client_jeu_hex/src/libInterfaceJavaC.so interfaceJavaC.o ./plateau.o ./groupe.o ./inout.o


#Generation dll windows 
#gcc -shared -I"C:\Program Files\Java\jdk1.8.0_131\include" -I"C:\Program Files\Java\jdk1.8.0_131\include\win32" -o ..\..\Client_jeu_hex\src\libInterfaceJavaC.dll interfaceJavaC.c ..\Plateau\plateau.c ..\Inout\inout.c ..\Groupe\groupe.c