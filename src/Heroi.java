/** Heróis: potência de ataque = maior valor de dois dados entre 0 e 100. */
public abstract class Heroi extends Personagem {

    public Heroi(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected int gerarPotenciaBase() {
        int dado1 = lancarDado(100);
        int dado2 = lancarDado(100);
        return Math.max(dado1, dado2);
    }
}
