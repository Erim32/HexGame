CC= gcc
CFLAGS= -Wall -std=c99
EXEC= hexTest
OBJ= ./Groupe/groupe.o ./Inout/inout.o ./Plateau/plateau.o ./Groupe/IA.o test.o
JNI= -I"C:\Program Files\Java\jdk1.8.0_131\include" -I"C:\Program Files\Java\jdk1.8.0_131\include\win32"
DLL= ../Client_jeu_hex/src/libInterfaceJavaC.dll ./InterfaceJava/interfaceJavaC.c ./Plateau/plateau.c ./Inout/inout.c ./Groupe/groupe.c
SO= ./InterfaceJava/interfaceJavaC.o ./Plateau/plateau.o ./Inout/inout.o ./Groupe/groupe.o

all:	$(OBJ)
	$(CC) $(OBJ) $(CFLAGS) -o $(EXEC)

./Groupe/groupe.o: ./Groupe/groupe.c ./Groupe/groupe.h
./Groupe/IA.o: ./Groupe/IA.c ./Groupe/IA.h
./Inout/inout.o: ./Inout/inout.c ./Inout/inout.h
./Plateau/plateau.o: ./Plateau/plateau.c ./Plateau/plateau.h
./InterfaceJava/interfaceJavaC.o: ./InterfaceJava/interfaceJavaC.c ./InterfaceJava/interfaceJavaC.h
test.o: test.c

so:
	$(CC) -c -fPIC ./InterfaceJava/interfaceJavaC.c
	$(CC) -c -fPIC ./Plateau/plateau.c
	$(CC) -c -fPIC ./Groupe/groupe.c
	$(CC) -c -fPIC ./Inout/inout.c
	$(CC) $(CLFAGS) -shared -o ../../libInterfaceJavaC.so $(SO)

dll:
	$(CC) $(CFLAGS) -shared $(JNI) -o $(DLL)

clean:
	rm -rf *.o
	rm -rf ./*/*.o
	
maxclean: clean
	rm -rf $(EXEC)