package creational.abstract_factory.concept;

/**
 * 🏛️ <b>PATRÓN DE DISEÑO: ABSTRACT FACTORY (Fábrica Abstracta)</b>
 * <p>
 * Imagina que vas a comprar un uniforme de fútbol. Si eliges el kit del "Real Madrid",
 * la camiseta, el short y las medias deben ser de ese equipo. No tendría sentido
 * que te dieran la camiseta del Madrid y el short del Barcelona.
 * </p>
 * <p>
 * El <b>Abstract Factory</b> es una interfaz que crea familias de objetos relacionados
 * sin especificar sus clases concretas. Asegura que los productos que usas siempre
 * sean compatibles entre sí (misma marca, mismo estilo, mismo SO).
 * </p>
 *
 * 🌟 <b>¿POR QUÉ USARLO?</b>
 * <ul>
 * <li>✅ <b>Consistencia:</b> Garantiza que los productos de una familia se usen juntos.</li>
 * <li>✅ <b>Desacoplamiento:</b> El cliente no sabe qué clase específica está instanciando.</li>
 * <li>✅ <b>Principio de Responsabilidad Única:</b> La lógica de creación se centraliza.</li>
 * </ul>
 *
 * 🧩 <b>COMPONENTES:</b>
 * <ul>
 * <li><b>Abstract Factory:</b> La interfaz con el "menú" de productos disponibles.</li>
 * <li><b>Concrete Factory:</b> La fábrica específica (ej. "Fábrica Nike", "Fábrica Adidas").</li>
 * <li><b>Abstract Product:</b> La interfaz para cada tipo de producto (ej. "Camiseta").</li>
 * <li><b>Concrete Product:</b> El producto final (ej. "Camiseta Nike").</li>
 * </ul>
 */

public class MainAbstractFactory {

    void main() {
        IFurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        renderHome(victorianFactory);

        IFurnitureFactory modernFactory = new ModernFurnitureFactory();
        renderHome(modernFactory);
    }

    static void renderHome(IFurnitureFactory factory) {
        IChair chair = factory.createChair();
        ITable table = factory.createTable();

        chair.sitOn();
        table.placeItem();
    }
}


interface IChair {
    void sitOn();
}

interface ITable {
    void placeItem();
}


class VictorianChair implements IChair {
    @Override
    public void sitOn() {
        IO.println("👑 Sentándose en una silla de madera tallada y terciopelo.");
    }
}

class VictorianTable implements ITable {
    @Override
    public void placeItem() {
        IO.println("☕ Poniendo té sobre una mesa de roble macizo.");
    }
}


class ModernChair implements IChair {
    @Override
    public void sitOn() {
        IO.println("💺 Sentándose en una silla ergonómica de plástico reciclado.");
    }
}

class ModernTable implements ITable {
    @Override
    public void placeItem() {
        IO.println("💻 Poniendo una laptop sobre una mesa de vidrio templado.");
    }
}

interface IFurnitureFactory {
    IChair createChair();
    ITable createTable();
}

class VictorianFurnitureFactory implements IFurnitureFactory {
    @Override
    public IChair createChair() {
        return new VictorianChair();
    }

    @Override
    public ITable createTable() {
        return new VictorianTable();
    }
}

class ModernFurnitureFactory implements IFurnitureFactory {
    @Override
    public IChair createChair() {
        return new ModernChair();
    }

    @Override
    public ITable createTable() {
        return new ModernTable();
    }
}
