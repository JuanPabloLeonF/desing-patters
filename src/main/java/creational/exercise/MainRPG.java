package creational.exercise;

/**
 * 🏆 <b>RETO COMBINADO: BUILDER + FACTORY METHOD</b>
 * <p>
 * Estás desarrollando un motor para un RPG. El sistema debe ser capaz de crear
 * personajes (Héroes) complejos, pero la base de estos personajes depende de su
 * Clase (Guerrero, Mago, Arquero).
 * </p>
 * * 📌 <b>TU MISIÓN:</b>
 * <ol>
 * <li>
 * <b>Parte Builder:</b> Crea una clase {@code Hero} con atributos como:
 * {@code name}, {@code weapon}, {@code armor}, {@code skill}, y {@code health}.
 * Usa el patrón Builder para que el personaje sea inmutable y se construya paso a paso.
 * </li>
 * <li>
 * <b>Parte Factory Method:</b> Crea una clase abstracta o interfaz {@code HeroFactory}.
 * Debe haber fábricas concretas (ej. {@code WarriorFactory}, {@code MageFactory})
 * que utilicen internamente el Builder para devolver un {@code Hero} con
 * configuraciones predeterminadas según la clase.
 * </li>
 * </ol>
 * * 🔧 <b>REQUERIMIENTOS TÉCNICOS:</b>
 * <ul>
 * <li>El {@code WarriorFactory} debe devolver un Hero con mucha vida y una "Espada".</li>
 * <li>El {@code MageFactory} debe devolver un Hero con poca vida, "Túnica" y un "Bastón".</li>
 * <li>El usuario debe poder usar la fábrica para obtener la base, pero TAMBIÉN
 * poder usar el Builder directamente si quiere un héroe totalmente personalizado.</li>
 * </ul>
 * * 💻 <b>RESULTADO ESPERADO:</b>
 * <pre>{@code
 * // Usando Factory que internamente usa el Builder
 * Hero arturo = HeroFactory.getFactory("WARRIOR").createHero("Arturo");
 * * // Personalización total usando solo Builder
 * Hero custom = Hero.builder()
 * .name("Sylvanas")
 * .weapon("Arco Legendario")
 * .skill("Flecha Oscura")
 * .build();
 * }</pre>
 */

public class MainRPG {
    void main() {

        Hero arturo = HeroFactory.getFactory("WARRIOR").createHero("arturo");
        IO.println(arturo);

        Hero fabricio = HeroFactory.getFactory("MAGE").createHero("fabricio");
        IO.println(fabricio);

        Hero hero = Hero.builder()
                .name("Sylvanas")
                .weapon("Arco Legendario")
                .skill("Flecha Oscura")
                .health(500)
                .armor("Armadura de Oro")
                .build();
        IO.println(hero);
    }
}

class Hero {
    private final String name;
    private final String weapon;
    private final String armor;
    private final String skill;
    private final Integer health;

    private Hero(HeroBuilder builder) {
        this.name = builder.name;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.skill = builder.skill;
        this.health = builder.health;
    }

    public static HeroBuilder builder() {
        return new HeroBuilder();
    }

    public static class HeroBuilder {
        private String name;
        private String weapon;
        private String armor;
        private String skill;
        private Integer health;

        public HeroBuilder name(String name) {
            this.name = name;
            return this;
        }

        public HeroBuilder weapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        public HeroBuilder armor(String armor) {
            this.armor = armor;
            return this;
        }
        public HeroBuilder skill(String skill) {
            this.skill = skill;
            return this;
        }
        public HeroBuilder health(Integer health) {
            this.health = health;
            return this;
        }

        Hero build() {
            return new Hero(this);
        }
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", weapon='" + weapon + '\'' +
                ", armor='" + armor + '\'' +
                ", skill='" + skill + '\'' +
                ", health=" + health +
                '}';
    }
}

abstract class HeroFactory {

    private static final String MAGE = "MAGE";
    private static final String WARRIOR = "WARRIOR";

    public abstract Hero createHero(String name);

    public static HeroFactory getFactory(String type) {
        return switch (type) {
            case MAGE -> new MageFactory();
            case WARRIOR -> new WarriorFactory();
            default -> throw new IllegalArgumentException("El tipo de heroe: " + type + " no existe");
        };
    }
}

class WarriorFactory extends HeroFactory {
    @Override
    public Hero createHero(String name) {
        return Hero.builder()
                .name(name)
                .weapon("Espada Legendario")
                .skill("Flecha Oscura")
                .health(2000)
                .armor("Armadura de Oro")
                .build();
    }
}

class MageFactory extends HeroFactory {

    @Override
    public Hero createHero(String name) {
        return Hero.builder()
                .name(name)
                .weapon("Arco Legendario")
                .skill("Flecha Oscura")
                .health(780)
                .armor("Tunica")
                .build();
    }
}