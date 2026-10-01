# Battle for Middle-earth ⚔️

A turn-based battle simulator inspired by *The Lord of the Rings*, written in **pure Java** with a **Swing** desktop interface.
Final project of the Java module at Tokio School.

> 🇵🇹 Simulador de batalhas por turnos entre Heróis e Bestas da Terra-Média, em Java com interface gráfica Swing. Projeto final do módulo de Java da Tokio School.

<!-- Add a screenshot of the window to docs/screenshot.png and remove these comment marks:
![Battle for Middle-earth](docs/screenshot.png)
-->

## Features

- Build two armies — **Heroes** (Elf, Hobbit, Human) and **Beasts** (Orc, Troll) — with custom name, health and armour
- Reorder and remove characters before the fight (who fights whom depends on their position)
- Turn-based combat with dice rolls and a live battle log
- Each race has its own rules:
  - **Heroes** attack with the highest of two dice (0–100); **Beasts** roll one die (0–90)
  - **Elves** get +10 attack against Orcs
  - **Hobbits** fear Trolls and lose 5 attack against them
  - **Orcs** ignore 10% of the opponent's armour on each hit
- Two modes: graphical interface (Swing) or console
- Safety limit of 1,000 turns, so a battle where no one can ever deal damage ends in a draw instead of looping forever

## Object-oriented design

The project was built to practise OOP concepts:

| Concept | Where |
|---|---|
| Abstraction | `Personagem` (abstract base class), `Heroi` and `Besta` |
| Inheritance | `Elfo`, `Hobbit`, `Humano` extend `Heroi`; `Orc`, `Troll` extend `Besta` |
| Polymorphism | Each race overrides `calcularPotenciaAtaque()` or `fatorReducaoArmadura()` |
| Encapsulation | Private fields; health only changes through `atacar()` |
| Separation of concerns | `Batalha` contains the combat logic and sends its log to a `Consumer<String>`, so the same class works in the console and in the GUI |

```
Personagem (abstract)
├── Heroi (abstract)  → Elfo, Hobbit, Humano
└── Besta (abstract)  → Orc, Troll
```

## Tech stack

- Java 17+ (no external libraries)
- Swing for the user interface

## How to run

**From an IDE (IntelliJ IDEA, Eclipse…):** open the project and run `MainGUI` (graphical mode) or `Main` (console mode with predefined armies).

**From the terminal:**

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out MainGUI     # graphical interface
java -cp out Main        # console mode
```

## Example output (console mode)

```
Turno 1:
  Luta entre Boromir (Vida=100 Armadura=60) e Uglúk (Vida=120 Armadura=30)
    Boromir saca 72 e tira 42 de vida a Uglúk
    Uglúk saca 27 e tira 0 de vida a Boromir
...
VITORIA DOS HEROIS!!
```

## Author

**Ricardo Faria** — Junior Full Stack Developer
[LinkedIn](https://www.linkedin.com/in/ricardo-faria-dev)
