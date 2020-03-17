public class Laberinto {

    private final int TAMAÑO;
    private char[][] tablero;
    private Coordenada entrada, salida;
    private Pila pilaCaminos;
    private boolean[][] visitados;


    public Laberinto(int tamaño, char[][] tablero, Coordenada entrada, Coordenada salida) {
        this.TAMAÑO = tamaño;
        this.tablero = tablero;
        this.entrada = entrada;
        this.salida = salida;
        this.pilaCaminos = new Pila();
        this.visitados = new boolean[TAMAÑO][TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                this.visitados[i][j] = false;
            }
        }
    }

    public void mostrar() {
        for (int i = 0; i <= TAMAÑO + 1; i++) {
            System.out.print("X");
        }
        System.out.println();
        for (int i = 0; i < TAMAÑO; i++) {
            if (i != entrada.getFila()) {
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
            for (int j = 0; j < TAMAÑO; j++) {
                System.out.print(tablero[i][j]);
            }
            if (i != salida.getFila()) {
                System.out.println("X");
            } else {
                System.out.println(" ");
            }
        }
        for (int i = 0; i <= TAMAÑO + 1; i++) {
            System.out.print("X");
        }
        System.out.println();
        System.out.println();
    }



    private boolean esCaminoValidoNuevo(Coordenada coordenada) {
        if (coordenada.getFila() < TAMAÑO &&
                coordenada.getFila() >= 0 &&
                coordenada.getColumna() < TAMAÑO &&
                coordenada.getColumna() >= 0) {
            if (tablero[coordenada.getFila()][coordenada.getColumna()] != 'X') {
                if(visitados[coordenada.getFila()][coordenada.getColumna()] == false) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean existeCamino() {
        boolean existeSiguienteCelda;
        pilaCaminos.apilar(entrada);
        visitados[entrada.getFila()][entrada.getColumna()] = true;

        while(!pilaCaminos.vacia() && !salida.iguales(pilaCaminos.getCima())) {
            existeSiguienteCelda = false;
            if(esCaminoValidoNuevo(pilaCaminos.getCima().arriba())) {
                pilaCaminos.apilar(pilaCaminos.getCima().arriba());
                visitados[pilaCaminos.getCima().getFila()][pilaCaminos.getCima().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(esCaminoValidoNuevo(pilaCaminos.getCima().abajo())) {
                pilaCaminos.apilar(pilaCaminos.getCima().abajo());
                visitados[pilaCaminos.getCima().getFila()][pilaCaminos.getCima().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(esCaminoValidoNuevo(pilaCaminos.getCima().izquierda())) {
                pilaCaminos.apilar(pilaCaminos.getCima().izquierda());
                visitados[pilaCaminos.getCima().getFila()][pilaCaminos.getCima().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(esCaminoValidoNuevo(pilaCaminos.getCima().derecha())) {
                pilaCaminos.apilar(pilaCaminos.getCima().derecha());
                visitados[pilaCaminos.getCima().getFila()][pilaCaminos.getCima().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(existeSiguienteCelda == false) {
                pilaCaminos.desapilar();
            }
        }

        if(pilaCaminos.vacia()) {
            return false;
        } else {
            return true;
        }
    }
}
