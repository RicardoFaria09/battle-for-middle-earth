/**
 * Classe base de todas as personagens do jogo (Heróis e Bestas).
 * Concentra os dados comuns (nome, vida, armadura) e o algoritmo de ataque,
 * deixando os detalhes específicos de cada tipo para as subclasses (polimorfismo).
 */
public abstract class Personagem {

    private final String nome;
    private int vida;
    private final int armadura;

    public Personagem(String nome, int vida, int armadura) {
        this.nome = nome;
        this.vida = vida;
        this.armadura = armadura;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getArmadura() {
        return armadura;
    }

    public boolean estaViva() {
        return vida > 0;
    }

    public abstract String getTipo();

    /** Lançamento de dado com valores inteiros entre 0 e valorMaximo (inclusive). */
    protected static int lancarDado(int valorMaximo) {
        return (int) (Math.random() * (valorMaximo + 1));
    }

    /** Potência base do ataque (dados), diferente para Heróis e Bestas. */
    protected abstract int gerarPotenciaBase();

    /**
     * Potência final do ataque contra um alvo específico.
     * Por omissão é apenas a potência base; Elfo e Hobbit sobrepõem este método
     * para aplicar os seus bónus/penalizações contra Orques/Trolls.
     */
    protected int calcularPotenciaAtaque(Personagem alvo) {
        return gerarPotenciaBase();
    }

    /**
     * Fator aplicado à armadura do alvo apenas durante este ataque (não é permanente).
     * Por omissão não há redução; o Orc sobrepõe para reduzir 10%.
     */
    protected double fatorReducaoArmadura() {
        return 1.0;
    }

    /** Executa um ataque contra o alvo e devolve a potência gerada e o dano causado. */
    public ResultadoAtaque atacar(Personagem alvo) {
        int potencia = calcularPotenciaAtaque(alvo);
        int armaduraEfetiva = (int) Math.round(alvo.getArmadura() * fatorReducaoArmadura());

        int dano = 0;
        if (potencia > armaduraEfetiva) {
            dano = potencia - armaduraEfetiva;
            alvo.receberDano(dano);
        }
        return new ResultadoAtaque(potencia, dano);
    }

    private void receberDano(int dano) {
        vida -= dano;
    }

    @Override
    public String toString() {
        return nome + " - " + getTipo() + " (" + vida + ", " + armadura + ")";
    }
}
