import java.util.Stack;

public class LaberintoStack {
    private final int TAMAÑO;
    private char[][] tablero;
    private Coordenada entrada, salida;
    private boolean[][] visitados;
    private Stack<Coordenada> pilaCaminos;

    public LaberintoStack(int tamaño, char[][] tablero, Coordenada entrada, Coordenada salida) {
        this.TAMAÑO = tamaño;
        this.tablero = tablero;
        this.entrada = entrada;
        this.salida = salida;
        this.visitados = new boolean[TAMAÑO][TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                this.visitados[i][j] = false;
            }
        }
        this.pilaCaminos = new Stack<Coordenada>();
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
        pilaCaminos.push(entrada);
        visitados[entrada.getFila()][entrada.getColumna()] = true;

        while(!pilaCaminos.empty() && !salida.iguales(pilaCaminos.peek())) {
            existeSiguienteCelda = false;
            if(esCaminoValidoNuevo(pilaCaminos.peek().arriba())) {
                pilaCaminos.push(pilaCaminos.peek().arriba());
                visitados[pilaCaminos.peek().getFila()][pilaCaminos.peek().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(esCaminoValidoNuevo(pilaCaminos.peek().abajo())) {
                pilaCaminos.push(pilaCaminos.peek().abajo());
                visitados[pilaCaminos.peek().getFila()][pilaCaminos.peek().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(esCaminoValidoNuevo(pilaCaminos.peek().izquierda())) {
                pilaCaminos.push(pilaCaminos.peek().izquierda());
                visitados[pilaCaminos.peek().getFila()][pilaCaminos.peek().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(esCaminoValidoNuevo(pilaCaminos.peek().derecha())) {
                pilaCaminos.push(pilaCaminos.peek().derecha());
                visitados[pilaCaminos.peek().getFila()][pilaCaminos.peek().getColumna()] = true;
                existeSiguienteCelda = true;
            }
            if(existeSiguienteCelda == false) {
                pilaCaminos.pop();
            }
        }

        if(pilaCaminos.empty()) {
            return false;
        } else {
            return true;
        }
    }


}