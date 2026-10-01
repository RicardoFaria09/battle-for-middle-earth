/** Hobbit: teme os Trolls, pelo que perde 5 de potência ofensiva contra eles. */
public class Hobbit extends Heroi {

    private static final int PENALIZACAO_CONTRA_TROLLS = 5;

    public Hobbit(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected int calcularPotenciaAtaque(Personagem alvo) {
        int potencia = super.calcularPotenciaAtaque(alvo);
        if (alvo instanceof Troll) {
            potencia -= PENALIZACAO_CONTRA_TROLLS;
        }
        return Math.max(potencia, 0);
    }

    @Override
    public String getTipo() {
        return "Hobbit";
    }
}
