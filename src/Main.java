import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Garante que nomes acentuados (ex.: Uglúk, Mauhúr) saem corretos
        // independentemente da codepage do terminal (relevante no Windows).
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        List<Heroi> herois = new ArrayList<>();
        herois.add(new Elfo("Legolas", 150, 30));
        herois.add(new Humano("Aragorn", 150, 50));
        herois.add(new Humano("Boromir", 100, 60));
        herois.add(new Humano("Gandalf", 300, 30));
        herois.add(new Humano("Frodo", 20, 10));

        List<Besta> bestas = new ArrayList<>();
        bestas.add(new Orc("Lurtz", 200, 60));
        bestas.add(new Orc("Shagrat", 220, 50));
        bestas.add(new Troll("Uglúk", 120, 30));
        bestas.add(new Troll("Mauhúr", 100, 30));

        Batalha batalha = new Batalha(herois, bestas, System.out::println);
        batalha.lutar();
    }
}
