package Intecap;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    private static int main;

    public static void Juego() {
        //Creacion de tablero
        int ps[][] = new int[8][8];
        char caracter[][] = new char[8][8];
        char trampa[][] = new char[8][8];
        int numeroCuadro = 64, posicionUser = 0;
        boolean cambioFila = true;
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        //Creamos tablero con numeros
        for (int i = 0; i < ps.length; i++) {
            if (cambioFila) {
                for (int j = ps[i].length - 1; j >= 0; j--) {
                    ps[i][j] = numeroCuadro;
                    numeroCuadro--;
                }
                cambioFila = false;
            } else {
                for (int j = 0; j < ps[i].length; j++) {
                    ps[i][j] = numeroCuadro;
                    numeroCuadro--;
                }
                cambioFila = true;
            }
        }

        //Creamos trampas aleatorias
        for (int i = 0; i < trampa.length; i++) {
            int numeroTrampas = (int) (Math.random() * (4 - 1) + 1);
            for (int j = 0; j < numeroTrampas; j++) {
                int posicion = (int) (Math.random() * 7);

                //Revisamos trampas duplicadas
                while (trampa[i][posicion] == '#') {
                    posicion = (int) (Math.random() * 7);
                }
                trampa[i][posicion] = '#';
            }
        }

        //Asignamos puesto inicial del jugador
        caracter[7][7] = '@';

        while (posicionUser <= 64) {
            for (int filas = 0; filas < ps.length; filas++) {
                //Impresion de tablero con numero
                System.out.println("---------------------------------------------------------");
                System.out.print("|");
                for (int columnas = 0; columnas < ps[filas].length; columnas++) {
                    if (ps[filas][columnas] <= 9) {
                        System.out.print("     " + ps[filas][columnas] + "|");
                    } else {
                        System.out.print("    " + ps[filas][columnas] + "|");
                    }
                }
                System.out.println();

                //Impresion del tablero con trampas y User
                for (int columnas = 0; columnas < ps.length; columnas++) {
                    if (trampa[filas][columnas] == '#' && caracter[filas][columnas] == '@') {
                        System.out.print("|" + caracter[filas][columnas] + "    " + trampa[filas][columnas]);
                    } else if (caracter[filas][columnas] == '@') {
                        System.out.print("|" + caracter[filas][columnas] + "     ");
                    } else if (trampa[filas][columnas] == '#') {
                        System.out.print("|     " + trampa[filas][columnas]);
                    } else {
                        System.out.print("|      ");
                    }

                }

                System.out.print("|");
                System.out.println();
            }
            System.out.println("---------------------------------------------------------");
            System.out.println();

            //Verificando si el usuario cae en trampa
            for (int i = 0; i < caracter.length; i++) {
                for (int j = 0; j < caracter[i].length; j++) {
                    if (caracter[i][j] == '@' && trampa[i][j] == '#') {
                        System.out.println("----USTED CAYO EN UNA TRAMPA----");
                    }
                }
            }

            //Limpieza de posicion de Usuario
            for (int filas = 0; filas < caracter.length; filas++) {
                for (int columnas = 0; columnas < caracter[filas].length; columnas++) {
                    caracter[filas][columnas] = ' ';
                }
            }

            ////////////////////////////////////////////////////////////////
            System.out.println("-- Desea girar dado con r o salir al menu principal con p --");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "r":
                    int dado = (int) (Math.random() * (6 - 2) + 2);
                    System.out.println("Tu dado salio en " + dado);
                    posicionUser += dado;

                    if (posicionUser >= 64) {
                        System.out.println("FINALIZO JUEGO");
                        System.exit(0);
                    } else if (posicionUser >= 56) {
                        caracter[0][posicionUser - 56] = '@';
                    } else if (posicionUser >= 48) {
                        caracter[1][55 - posicionUser] = '@';
                    } else if (posicionUser >= 40) {
                        caracter[2][posicionUser - 40] = '@';
                    } else if (posicionUser >= 32) {
                        caracter[3][39 - posicionUser] = '@';
                    } else if (posicionUser >= 24) {
                        caracter[4][(posicionUser - 23) - 1] = '@';
                    } else if (posicionUser >= 16) {
                        caracter[5][23 - posicionUser] = '@';
                    } else if (posicionUser >= 8) {
                        caracter[6][posicionUser - 8] = '@';
                    } else {
                        caracter[7][7 - posicionUser] = '@';
                    }

                    break;

                case "p":
                   main(new String[]{});
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Scanner vu = new Scanner(System.in);

        System.out.println("========= Menu Principal =========");
        System.out.println("    |   1. Iniciar Juego     |");
        System.out.println("    |   2. Salir             |");
        System.out.println("Ingrese opcion:");
        int opcion = vu.nextInt();

        while (true) {
            switch (opcion) {
                case 1:
                    Juego();
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }

    }
}
