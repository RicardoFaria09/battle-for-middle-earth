/** Resultado de um único ataque: a potência gerada e o dano efetivamente causado. */
public class ResultadoAtaque {

    private final int potencia;
    private final int dano;

    public ResultadoAtaque(int potencia, int dano) {
        this.potencia = potencia;
        this.dano = dano;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getDano() {
        return dano;
    }
}
