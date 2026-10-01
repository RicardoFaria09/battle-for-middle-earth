/** Troll: sem regras especiais próprias, usa apenas as regras gerais de Besta. */
public class Troll extends Besta {

    public Troll(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public String getTipo() {
        return "Troll";
    }
}
