CC=gcc
CFLAGS=-Wall -std=c99 
#-DNDEBUG
LDFLAGS= -lm
EXEC= plateau
OBJ= ../Groupe/groupe.o ../Inout/inout.o plateau.o plateautest.o
 
#all:	$(EXEC)

all:	$(OBJ)
	$(CC) $(OBJ) $(CFLAGS) -o $(EXEC) $(LDFLAGS)
plateau.o: plateau.c plateau.h
plateautest.o: plateautest.c
clean:
	rm -rf *.o
maxclean: clean
	rm -rf $(EXEC)
#gcc -c -fPIC plateauInterface.c
#gcc -c -fPIC plateau.c
#gcc -shared -o libInterfaceJavaC.so plateauInterface.o plateau.o