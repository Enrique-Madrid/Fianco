package Fianco;
import java.util.Scanner;

public class Fianco {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Hola, desea jugar FIANCO?\nSi\nNo");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            /*Pone en minusculas la variable respuesta */
            respuesta = respuesta.toLowerCase();
            if (respuesta.equals("si")) {
                System.out.println("Bienvenido al juego");
                /*Crea una matriz 9 x 9 */
                String[][] tablero = new String[9][9];
                
                CrearNuevoTablero(tablero);

                Boolean partida = false;
                Boolean turno = true;
                while(partida == false){
                        ImprimirTablero(tablero);

                        if(turno == true){
                            System.out.println("Es el turno del jugador 1 (0)");
                            int[] coor = ChecarFicha(tablero, "0");
                            System.out.println("Ingrese el movimiento\n1.Abajo\n2.Izquierda\n3.Derecha");
                            Scanner sc2 = new Scanner(System.in);
                            int movimiento = sc2.nextInt();
                            MoverFicha(tablero, coor, movimiento, "0");
                            turno = false;
                        }else{
                            System.out.println("Es el turno del jugador 2 (*)");
                            int[] coor = ChecarFicha(tablero, "*");
                            System.out.println("Ingrese el movimiento\n1.Arriba\n2.Izquierda\n3.Derecha");
                            Scanner sc2 = new Scanner(System.in);
                            int movimiento = sc2.nextInt();
                            MoverFicha(tablero, coor, movimiento, "*");
                            turno = true;
                        }

                        partida = ChecarVictoria(tablero);

                    }

                } else {
                    System.out.println("Hasta luego");
                    break;
                }


            } 

        }

    static void ImprimirTablero(String[][] tablero) {
        System.out.println("012345678");
        System.out.println("|||||||||");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println("-" + i);
        }
    }

    static boolean ChecarVictoria(String[][] tablero) {
        for (int i = 0; i < 9; i++) {
            if (tablero[0][i] == "*"){
                System.out.println("Gano el jugador 2");
                return true;
            } else if (tablero[8][i] == "0"){
                System.out.println("Gano el jugador 1");
                return true;
            }
        }
        return false;
    }

    static int[] ChecarFicha(String[][] tablero, String ficha) {
        int[] coor = new int[2];
        while (true) {
            System.out.println("Seleccione las coordenadas de la ficha separada por comas (fila, columna)");
            Scanner sc1 = new Scanner(System.in);
            String coordenadas = sc1.nextLine();
            String[] coordenadasSeparadas = coordenadas.split(",");
            int fila = Integer.parseInt(coordenadasSeparadas[0]);
            int columna = Integer.parseInt(coordenadasSeparadas[1]);
            if (tablero[fila][columna].equals(ficha)){
                System.out.println("Casilla Seleccionada");
                coor[0] = fila;
                coor[1] = columna;
                break;
            }else{
                System.out.println("Casilla no disponible, vuelva a seleccionar");
        }
        }
        return coor;
    }

    static void ChecarEnemigo(String[][] tablero, int[] coor, String ficha, String enemigo) {
        int [] coor2 = new int[2];
        int [] coor3 = new int[2];
        int casillaAbajo = coor[0] + 1;
        int casillaArriba = coor[0] - 1;
        int casillaIzquierda = coor[1] - 1;
        int casillaDerecha = coor[1] + 1;

        String movimientoInvalido = "";

        if (casillaAbajo <= 8 && casillaArriba >= 0 && casillaDerecha <= 8 && casillaIzquierda >= 0 ) {
            if (tablero[casillaArriba][casillaIzquierda] == enemigo){

                tablero[casillaArriba][casillaIzquierda] = "┼";
    
                coor3[0] = casillaArriba;
                coor3[1] = casillaIzquierda;
                movimientoInvalido = ChecarLimites(coor3);
    
                if (movimientoInvalido != "arriba" && movimientoInvalido != "izquierda"){
                    DiagonalArribaIzquierda(tablero, coor, coor2, 2, ficha);
                } else {
                    DiagonalArribaIzquierda(tablero, coor, coor2, 1, ficha);
                }
    
            } else if (tablero[casillaArriba][casillaDerecha] == enemigo){
    
                tablero[casillaArriba][casillaDerecha] = "┼";
    
                coor3[0] = casillaArriba;
                coor3[1] = casillaDerecha;
                movimientoInvalido = ChecarLimites(coor3);
    
                if (movimientoInvalido != "arriba" && movimientoInvalido != "derecha"){
                    DiagonalArribaDerecha(tablero, coor, coor2, 2, ficha);
                } else {
                    DiagonalArribaDerecha(tablero, coor, coor2, 1, ficha);
                }
    
            } else if (tablero[casillaAbajo][casillaDerecha] == enemigo){
    
                tablero[casillaAbajo][casillaDerecha] = "┼";
    
                coor3[0] = casillaAbajo;
                coor3[1] = casillaDerecha;
                movimientoInvalido = ChecarLimites(coor3);
    
                if (movimientoInvalido != "abajo" && movimientoInvalido != "derecha"){
                    DiagonalAbajoDerecha(tablero, coor, coor2, 2, ficha);
                } else {
                    DiagonalAbajoDerecha(tablero, coor, coor2, 1, ficha);
                }
    
            } else if (tablero[casillaAbajo][casillaIzquierda] == enemigo){
    
                tablero[casillaAbajo][casillaIzquierda] = "┼";
    
                coor3[0] = casillaAbajo;
                coor3[1] = casillaIzquierda;
                movimientoInvalido = ChecarLimites(coor3);
    
                if (movimientoInvalido != "abajo" && movimientoInvalido != "izquierda"){
                    DiagonalAbajoIzquierda(tablero, coor, coor2, 2, ficha);
                } else {
                    DiagonalAbajoIzquierda(tablero, coor, coor2, 1, ficha);
                }
    
            }
        }
    }

    static void MoverFicha(String[][] tablero, int[] coor, int movimiento, String ficha) {
        int [] coor2 = new int[2];
        String enemigo = "";

        String movimientoInvalido = ChecarLimites(coor);
        System.out.println(movimientoInvalido);

        if (ficha == "0"){
            enemigo = "*";
            if (movimiento == 1 && movimientoInvalido != "abajo"){
                MoverAbajo(tablero, coor, coor2, 1, ficha);}

        } else if (ficha == "*") {
            enemigo = "0";
            if (movimiento == 1 && movimientoInvalido != "arriba"){
                MoverArriba(tablero, coor, coor2, 1, ficha);
                movimiento = 0;}
        }

        if (movimiento == 2 && movimientoInvalido != "izquierda"){
            MoverIzquierda(tablero, coor, coor2, 1, ficha);
        } else if (movimiento == 3 && movimientoInvalido != "derecha"){
            MoverDerecha(tablero, coor, coor2, 1, ficha);
        }
        
        ChecarEnemigo(tablero, coor2, ficha, enemigo);

    }

    static String ChecarLimites(int[] coor){
        String val = "ok";
        if (coor[1] <= 0){
            System.out.println("Movimiento izquierda invalido");
            val = "izquierda";
        } if (coor[1] >= 8){
            System.out.println("Movimiento derecha invalido");
            val = "derecha";
        } if (coor[0] <= 0){
            System.out.println("Movimiento arriba invalido");
            val = "arriba";
        } if (coor[0] >= 8){
            System.out.println("Movimiento abajo invalido");
            val = "abajo";
        } 
        return val;
    }

    static void CrearNuevoTablero(String[][] tablero) {
        /*Se crea el tablero */

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = "┼";
            }
        }

        /*Se crean las fichas de los usuarios */

        for(int i = 0; i < 9; i++) {
            tablero[0][i] = "0";
            tablero[8][i] = "*";
        }

        for(int i = 0; i < 9; i++) {
            if(i<=3){
                tablero[i][i] = "0";
                tablero[i][8-i] = "0";
            } else if(i>=5){
                tablero[i][i] = "*";
                tablero[i][8-i] = "*";
            } else {
                tablero[i][i] = "┼";
            }
        }

    }

    static void MoverAbajo(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0] + 1 * pasos; coor2[1] = coor[1];
        if (coor2[0] <= 8){
            tablero[coor2[0]][coor2[1]] = ficha;
            tablero[coor[0]][coor[1]] = "┼";
        } else {
            System.out.println("Movimiento invalido");
        }
    }

    static void MoverArriba(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0] - 1 * pasos; coor2[1] = coor[1];
        if (coor2[0] >= 0){
            tablero[coor2[0]][coor2[1]] = ficha;
            tablero[coor[0]][coor[1]] = "┼";
        } else {
            System.out.println("Movimiento invalido");
        }
    }

    static void MoverDerecha(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0]; coor2[1] = coor[1] + 1 * pasos;
        if (coor2[1] <= 8){
            tablero[coor2[0]][coor2[1]] = ficha;
            tablero[coor[0]][coor[1]] = "┼";
        } else {
            System.out.println("Movimiento invalido");
        }
    }

    static void MoverIzquierda(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0]; coor2[1] = coor[1] - 1 * pasos;
        if (coor2[1] >= 0){
            tablero[coor2[0]][coor2[1]] = ficha;
            tablero[coor[0]][coor[1]] = "┼";
        } else {
            System.out.println("Movimiento invalido");
        }
    }

    static void DiagonalArribaIzquierda(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0] - 1 * pasos;
        coor2[1] = coor[1] - 1 * pasos;
        tablero[coor2[0]][coor2[1]] = ficha;
        tablero[coor[0]][coor[1]] = "┼";
    }

    static void DiagonalArribaDerecha(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0] - 1 * pasos;
        coor2[1] = coor[1] + 1 * pasos;
        tablero[coor2[0]][coor2[1]] = ficha;
        tablero[coor[0]][coor[1]] = "┼";
    }

    static void DiagonalAbajoIzquierda(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0] + 1 * pasos;
        coor2[1] = coor[1] - 1 * pasos;
        tablero[coor2[0]][coor2[1]] = ficha;
        tablero[coor[0]][coor[1]] = "┼";
    }

    static void DiagonalAbajoDerecha(String[][] tablero, int[] coor, int[] coor2, int pasos, String ficha){
        coor2[0] = coor[0] + 1 * pasos;
        coor2[1] = coor[1] + 1 * pasos;
        tablero[coor2[0]][coor2[1]] = ficha;
        tablero[coor[0]][coor[1]] = "┼";
    }

}
