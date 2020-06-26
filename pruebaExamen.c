#include  <stdio.h>
#include  <unistd.h>
#include  <fcntl.h>

#define TAM 12

int main(void){
char buffer[TAM] = "Hello World!";
char aux[TAM];
int leidos;
int fichero;
int pid = fork();

if(pid == 0){

fichero = open("text.txt", O_WRONLY | O_CREAT, TAM);

if(fichero == -1){

printf("Error");

return 0;

}

write(fichero, buffer, TAM);
close(fichero);

}else{

int p, status;
p = wait(&status);
fichero = open("text.txt", O_RDONLY, TAM);

if(fichero == -1){

printf("Error");
return 0;

}

leidos = read(fichero, aux, TAM);
close(fichero);
printf("nº bytes: %d, Texto: %s.", leidos, aux); 
}
return 0;
}
