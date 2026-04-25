package creational.factory_method.concept;

/**
 * 🏭 <b>PATRÓN: FACTORY METHOD</b>
 * <p>
 * ¿Alguna vez has querido pedir un objeto sin preocuparte de <i>cómo</i> se crea o
 * <i>qué</i> clase específica se debe instanciar? El Factory Method es la solución.
 * Define una interfaz para crear un objeto, pero deja que las subclases decidan
 * qué clase instanciar.
 * </p>
 * <p>
 * Es como un <b>centro de logística</b>: Tú pides un "transporte", y dependiendo de
 * la situación, el centro te entrega un Camión o un Barco. El cliente solo sabe
 * que el objeto recibido sabe "entregar paquetes", no le importa cómo lo hace por dentro.
 * </p>
 * * 📌 <b>VENTAJAS PRINCIPALES:</b>
 * <ul>
 * <li><b>Desacoplamiento:</b> El código que usa el objeto no está amarrado a clases concretas.</li>
 * <li><b>Extensibilidad:</b> Puedes agregar nuevos tipos de productos sin romper el código existente (Principio Open/Closed).</li>
 * <li><b>Centralización:</b> Toda la lógica de creación vive en un solo lugar.</li>
 * </ul>
 * * 🛠️ <b>ESTRUCTURA DEL EJEMPLO:</b>
 * <ol>
 * <li><b>Producto (Interface):</b> Lo que queremos crear (ej. {@code Notificacion}).</li>
 * <li><b>Productos Concretos:</b> Las versiones reales (ej. {@code Email}, {@code SMS}).</li>
 * <li><b>Creador (Factory):</b> La clase que tiene la lógica de decisión.</li>
 * </ol>
 * * 💻 <b>EJEMPLO DE USO:</b>
 * <pre>{@code
 * // No usamos "new EmailNotificacion()", dejamos que la fábrica decida
 * Notificacion service = NotificacionFactory.create("EMAIL");
 * service.enviar("Hola, este es un Factory Method!");
 * }</pre>
 */

public class MainConceptFactoryMethod {
    void main() {
        INotification notification = NotificationFactory.create("EMAIL");
        notification.send("Hola, este es un Factory Method!");

        notification = NotificationFactory.create("SMS");
        notification.send("Hola, este es un Factory Method!");

        notification = NotificationFactory.create("WHATSAPP");
        notification.send("Hola, este es un Factory Method!");
        
    }
}

class NotificationFactory {
    public static INotification create(String type) {

        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Tipo de notificación no puede ser nulo o vacío");
        }

        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS"   -> new SMSNotification();
            case "WHATSAPP" -> new WhatsAppNotification();
            default  -> throw new IllegalArgumentException("Tipo de notificación desconocido: " + type);
        };
    }
}

interface INotification {
    void send(String message);
}

class EmailNotification implements INotification {
    @Override
    public void send(String message) {
        IO.println("📧 Enviando Email: " + message);
    }
}

class SMSNotification implements INotification{
    @Override
    public void send(String message) {
        IO.println("📱 Enviando SMS: " + message);
    }
}

class WhatsAppNotification implements INotification {
    @Override
    public void send(String message) {
        IO.println("\uD83D\uDFE2 Enviando WhatsApp: " + message);
    }
}