/** Elfo: odeia os Orques, pelo que ganha +10 de potência ofensiva contra eles. */
public class Elfo extends Heroi {

    private static final int BONUS_CONTRA_ORQUES = 10;

    public Elfo(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected int calcularPotenciaAtaque(Personagem alvo) {
        int potencia = super.calcularPotenciaAtaque(alvo);
        if (alvo instanceof Orc) {
            potencia += BONUS_CONTRA_ORQUES;
        }
        return potencia;
    }

    @Override
    public String getTipo() {
        return "Elfo";
    }
}
