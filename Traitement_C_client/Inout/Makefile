CC=gcc
CFLAGS=-Wall -std=c99 
#-DNDEBUG
LDFLAGS= -lm
EXEC= inout
OBJ=  ../Groupe/groupe.o  ../Plateau/plateau.o inout.o inouttest.o
 
all:	$(OBJ)
	$(CC) $(OBJ) $(CFLAGS) -o $(EXEC) $(LDFLAGS)

inout.o: inout.c inout.h
inouttest.o: inouttest.c

clean:
	rm -rf *.o
maxclean: clean
	rm -rf $(EXEC)
