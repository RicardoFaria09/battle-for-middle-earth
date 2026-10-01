/** Orc: força desmedida, reduz em 10% a armadura do adversário apenas nesse ataque. */
public class Orc extends Besta {

    private static final double FATOR_REDUCAO_ARMADURA = 0.9;

    public Orc(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected double fatorReducaoArmadura() {
        return FATOR_REDUCAO_ARMADURA;
    }

    @Override
    public String getTipo() {
        return "Orc";
    }
}
