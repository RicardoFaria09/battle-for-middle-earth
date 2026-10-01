/** Humano: sem bónus nem penalizações especiais, usa apenas as regras gerais de Heroi. */
public class Humano extends Heroi {

    public Humano(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public String getTipo() {
        return "Humano";
    }
}
