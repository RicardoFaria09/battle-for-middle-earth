import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Gere o combate entre um exército de Heróis e um exército de Bestas.
 * Em cada turno lutam as personagens que ocupam a mesma posição nos dois exércitos;
 * as personagens sobrantes do exército maior ficam de fora nesse turno.
 * O relato da batalha é enviado para um "log", o que permite usar a mesma
 * classe tanto no modo consola como na interface gráfica.
 */
public class Batalha {

    /**
     * Limite de segurança: se a armadura de ambos os lados for demasiado alta
     * para alguma vez ser ultrapassada pela potência de ataque, a batalha nunca
     * produziria dano e o ciclo nunca terminaria. Ao fim deste número de turnos
     * sem vencedor, a luta termina em empate.
     */
    private static final int LIMITE_TURNOS = 1000;

    private final List<Heroi> herois;
    private final List<Besta> bestas;
    private final Consumer<String> log;

    public Batalha(List<Heroi> herois, List<Besta> bestas, Consumer<String> log) {
        this.herois = herois;
        this.bestas = bestas;
        this.log = log;
    }

    public void lutar() {
        int turno = 1;

        while (!herois.isEmpty() && !bestas.isEmpty() && turno <= LIMITE_TURNOS) {
            log.accept("Turno " + turno + ":");
            int confrontos = Math.min(herois.size(), bestas.size());

            for (int i = 0; i < confrontos; i++) {
                Heroi heroi = herois.get(i);
                Besta besta = bestas.get(i);
                lutarPar(heroi, besta);
            }

            removerMortos(herois);
            removerMortos(bestas);
            turno++;
        }

        if (herois.isEmpty() && bestas.isEmpty()) {
            log.accept("EMPATE! Todas as personagens caíram no mesmo turno.");
        } else if (herois.isEmpty()) {
            log.accept("VITORIA DAS BESTAS!!");
        } else if (bestas.isEmpty()) {
            log.accept("VITORIA DOS HEROIS!!");
        } else {
            log.accept("A luta não terminou em " + LIMITE_TURNOS
                    + " turnos (a armadura de algum lado é demasiado alta para alguma vez ser ultrapassada).");
        }
    }

    private void lutarPar(Heroi heroi, Besta besta) {
        log.accept("  Luta entre " + heroi.getNome() + " (Vida=" + heroi.getVida()
                + " Armadura=" + heroi.getArmadura() + ") e " + besta.getNome()
                + " (Vida=" + besta.getVida() + " Armadura=" + besta.getArmadura() + ")");

        ResultadoAtaque ataqueHeroi = heroi.atacar(besta);
        log.accept("    " + heroi.getNome() + " saca " + ataqueHeroi.getPotencia()
                + " e tira " + ataqueHeroi.getDano() + " de vida a " + besta.getNome());

        ResultadoAtaque ataqueBesta = besta.atacar(heroi);
        log.accept("    " + besta.getNome() + " saca " + ataqueBesta.getPotencia()
                + " e tira " + ataqueBesta.getDano() + " de vida a " + heroi.getNome());
    }

    private <T extends Personagem> void removerMortos(List<T> exercito) {
        List<T> mortos = new ArrayList<>();
        for (T personagem : exercito) {
            if (!personagem.estaViva()) {
                mortos.add(personagem);
            }
        }
        for (T morto : mortos) {
            log.accept("  Morre o " + morto.getTipo() + " " + morto.getNome() + "!");
            exercito.remove(morto);
        }
    }
}
