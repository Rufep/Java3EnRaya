/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg3enraya;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa el juego de 3 en raya.
 */
public class TresEnRaya {

    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO = "\u001B[31m";

    /**
     * Método principal que inicia el juego.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este
     * caso).
     */
    public static void main(String[] args) {
        // Crear el tablero de 3x3
        char[][] tablero = new char[3][3];

        // Inicializar el tablero (por ejemplo, puedes usar espacios en blanco)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }

        // Mostrar el tablero inicial
        mostrarTablero(tablero);

        // Juego: Jugador vs. Computadora
        boolean empate = false;

        while (true) {
            // Jugador 1 (X) - Ficha verde
            elegirPosicion(tablero, 'X');
            mostrarTablero(tablero);

            // Verificar si el jugador 1 ha ganado
            if (hayGanador(tablero, 'X')) {
                System.out.println(VERDE + "¡Felicidades! ¡Has ganado!" + RESET);
                break;
            }

            // Verificar empate
            if (tableroLleno(tablero)) {
                empate = true;
                break;
            }

            // Computadora (O) - Ficha roja
            seleccionAutomatica(tablero, 'O');
            mostrarTablero(tablero);

            // Verificar si la computadora ha ganado
            if (hayGanador(tablero, 'O')) {
                System.out.println(ROJO + "¡La computadora ha ganado! Mejor suerte la próxima vez." + RESET);
                break;
            }

            // Verificar empate después del movimiento de la computadora
            if (tableroLleno(tablero)) {
                empate = true;
                break;
            }
        }

        if (empate) {
            System.out.println("¡El juego ha terminado en empate!");
        }
    }

    /**
     * Método para verificar si el tablero está lleno.
     *
     * @param tablero Tablero de 3x3 a verificar.
     * @return true si el tablero está lleno, false de lo contrario.
     */
    public static boolean tableroLleno(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false; // Si hay al menos una celda vacía, el tablero no está lleno
                }
            }
        }
        return true; // Todas las celdas están ocupadas
    }

    /**
     * Método para mostrar el tablero en la consola con números de fila y
     * columna y colores.
     *
     * @param tablero Tablero de 3x3 a mostrar.
     */
    public static void mostrarTablero(char[][] tablero) {
        System.out.println("   0   1   2 ");
        System.out.println("  -------------");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 3; j++) {
                char ficha = tablero[i][j];
                String colorFicha = (ficha == 'X') ? VERDE : (ficha == 'O') ? ROJO : AMARILLO;
                System.out.print(" " + colorFicha + ficha + RESET + " |");
            }
            System.out.println();
            System.out.println("  -------------");
        }
    }

    /**
     * Método para que el jugador elija una posición en el tablero.
     *
     * @param tablero Tablero de 3x3 en el que el jugador hará su movimiento.
     * @param jugador Símbolo del jugador ('X' o 'O').
     */
    public static void elegirPosicion(char[][] tablero, char jugador) {
        Scanner scanner = new Scanner(System.in);

        int fila = 0, columna = 0;
        boolean entradaValida = false;

        do {
            System.out.println("Jugador " + jugador + ", elige una posición (fila columna): ");
            String entrada = scanner.nextLine();

            // Dividir la entrada en fila y columna
            String[] partes = entrada.split(" ");

            if (partes.length == 2) {
                try {
                    // Convertir a enteros
                    fila = Integer.parseInt(partes[0]);
                    columna = Integer.parseInt(partes[1]);

                    // Verificar si la posición está dentro del rango y está libre
                    if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ') {
                        entradaValida = true;
                    } else {
                        System.out.println("Posición inválida. Inténtalo de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa dos números válidos.");
                }
            } else {
                System.out.println("Por favor, ingresa dos números separados por espacio.");
            }
        } while (!entradaValida);

        // Marcar la posición en el tablero
        tablero[fila][columna] = jugador;
    }

    /**
     * Método para que la computadora seleccione una posición automáticamente.
     *
     * @param tablero Tablero de 3x3 en el que la computadora hará su
     * movimiento.
     * @param jugador Símbolo de la computadora ('X' o 'O').
     */
    public static void seleccionAutomatica(char[][] tablero, char jugador) {
        Random random = new Random();

        int fila, columna;

        do {
            // Generar una posición aleatoria
            fila = random.nextInt(3);
            columna = random.nextInt(3);

            // Verificar si la posición está ocupada
        } while (tablero[fila][columna] != ' ');

        // Marcar la posición en el tablero
        tablero[fila][columna] = jugador;

        System.out.println("La computadora ha elegido la posición (" + fila + ", " + columna + ").");
    }

    /**
     * Método para verificar si hay un ganador en el tablero.
     *
     * @param tablero Tablero de 3x3 a verificar.
     * @param jugador Símbolo del jugador ('X' o 'O').
     * @return true si hay un ganador, false de lo contrario.
     */
    public static boolean hayGanador(char[][] tablero, char jugador) {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador)
                    || (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador)) {
                return true;
            }
        }

        // Verificar diagonales
        if ((tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador)
                || (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador)) {
            return true;
        }

        return false;
    }
}
