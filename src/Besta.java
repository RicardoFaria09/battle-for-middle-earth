/** Bestas: potência de ataque = valor de um único dado entre 0 e 90. */
public abstract class Besta extends Personagem {

    public Besta(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected int gerarPotenciaBase() {
        return lancarDado(90);
    }
}
