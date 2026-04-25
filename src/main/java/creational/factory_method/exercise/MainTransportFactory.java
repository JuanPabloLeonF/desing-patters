package creational.factory_method.exercise;

/**
 * 🏆 <b>RETO: EL SISTEMA DE LOGÍSTICA INTERNACIONAL</b>
 * <p>
 * Una empresa de mensajería está creciendo y necesita un sistema que pueda
 * crear diferentes métodos de transporte (Tierra, Mar, Aire) sin que el
 * código principal sepa los detalles de cada vehículo.
 * </p>
 * * 📌 <b>TU MISIÓN:</b>
 * <ol>
 * <li><b>Crear la interfaz {@code Transport}:</b> Con un método {@code void deliver()}.</li>
 * <li><b>Crear clases concretas:</b>
 * <ul>
 * <li>{@code Truck}: Debe imprimir "🚛 Entrega por tierra en caja de cartón".</li>
 * <li>{@code Ship}: Debe imprimir "🚢 Entrega por mar en contenedor marítimo".</li>
 * <li>{@code Plane}: Debe imprimir "✈️ Entrega por aire con prioridad alta".</li>
 * </ul>
 * </li>
 * <li><b>Crear la clase {@code TransportFactory}:</b> Con un método estático que reciba
 * el tipo de transporte y retorne la instancia correcta.</li>
 * </ol>
 * * 💻 <b>RESULTADO ESPERADO:</b>
 * <pre>{@code
 * Transport t = TransportFactory.create("SEA");
 * t.deliver(); // Debería mostrar el barco
 * }</pre>
 * * ⚠️ <b>REGLA DE ORO:</b> Si el tipo no existe o es nulo, lanza una excepción.
 */

public class MainTransportFactory {
    void main() {
        ITransport transport = TransportFactory.create("SHIP");
        transport.deliver();

        ITransport transport2 = TransportFactory.create("PLANE");
        transport2.deliver();

        ITransport transport3 = TransportFactory.create("TRUCK");
        transport3.deliver();

        ITransport transport4 = TransportFactory.create("NO SE");
        transport4.deliver();
    }
}

class TransportFactory {

    static final String PLANE = "PLANE";
    static final String TRUCK = "TRUCK";
    static final String SHIP = "SHIP";

    public static ITransport create(String type) {
        validatedType(type);
        type = type.toUpperCase();
        return switch (type) {
            case PLANE -> new Plane();
            case TRUCK -> new Truck();
            case SHIP  -> new Ship();
            default -> throw new IllegalArgumentException("Tipo de notificación desconocido: " + type);
        };
    }

    public static void validatedType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Tipo de transporte no puede ser nulo o vacío");
        }
    }
}

interface ITransport {
    void deliver();
}

class Truck implements ITransport {
    @Override
    public void deliver() {
        IO.println("🚛 Entrega por tierra en caja de cartón");
    }
}

class Ship implements ITransport {
    @Override
    public void deliver() {
        IO.println("🚢 Entrega por mar en contenedor marítimo");
    }
}

class Plane implements ITransport {
    @Override
    public void deliver() {
        IO.println("✈️ Entrega por aire con prioridad alta");
    }
}
