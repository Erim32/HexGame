CC=gcc
CFLAGS=-Wall -std=c99 
#-DNDEBUG
LDFLAGS= -lm
EXEC= groupe
OBJ= groupe.o groupetest.o
 
all:	$(OBJ)
	$(CC) $(OBJ) $(CFLAGS) -o $(EXEC) $(LDFLAGS)
groupe.o: groupe.c groupe.h
groupetest.o: groupetest.c
clean:
	rm -rf *.o
maxclean: clean
	rm -rf $(EXEC)
