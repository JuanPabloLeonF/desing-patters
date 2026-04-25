package creational.builder.exercise;

/**
 * <h3>Escenario: Sistema de Notificaciones Empresarial</h3>
 * <p>
 * Imagina que trabajas para una plataforma como Slack o WhatsApp.
 * Debes crear una clase <b>Message</b> que cumpla con reglas de negocio estrictas:
 * </p>
 * <ul>
 * <li><b>Destinatario:</b> Es obligatorio.</li>
 * <li><b>Contenido:</b> No puede estar vacío.</li>
 * <li><b>Tipo:</b> Puede ser TEXT, IMAGE, o VIDEO.</li>
 * <li><b>Prioridad:</b> (Opcional) Un rango de 1 a 5.</li>
 * <li><b>Timestamp:</b> Se debe generar automáticamente al construirlo.</li>
 * </ul>
 *
 * <h3>Tu Tarea (El Reto)</h3>
 * <p>
 * Implementar el patrón <b>Builder</b> siguiendo estas reglas profesionales:
 * </p>
 * <ol>
 * <li><b>Validación:</b> Si falta el destinatario o el contenido, build() debe lanzar una <code>IllegalStateException</code>.</li>
 * <li><b>Lógica de Negocio:</b> Si el tipo es IMAGE pero no se proporciona una URL, debe fallar.</li>
 * <li><b>Inmutabilidad:</b> Una vez creado, el mensaje no se puede modificar.</li>
 * </ol>
 */


public class MainMessage {

    void main() {
        Message message = Message.builder()
                .to("person@gmail.com")
                .setPriority(5)
                .withContent("enviando un mensaje")
                .messageType("TEXT")
                .build();

        IO.println(message);
    }
}

class Message {

    private final String recipient;
    private final String content;
    private final String messageType;
    private final int priority;
    private final long timestamp;

    private Message(Builder builder) {
        this.recipient = builder.recipient;
        this.content = builder.content;
        this.messageType = builder.messageType;
        this.priority = builder.priority;
        this.timestamp = System.currentTimeMillis();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String recipient;
        private String content;
        private String messageType = "TEXT";
        private int priority = 3;

        public Builder to(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder messageType(String messageType) {
            validateMessageType(messageType);
            this.messageType = messageType;
            return this;
        }

        public Builder setPriority(int priority) {
            validatePriority(priority);
            this.priority = priority;
            return this;
        }

        Message build() {
            validateField(this.recipient);
            validateField(this.content);

            if (this.messageType.equals("IMAGE")) {
                validateContentContainsUrl(content);
            }

            return new Message(this);
        }
    }

    private static void validatePriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
    }

    private static void validateContentContainsUrl(String content) {
        if (!content.contains("https://")) {
            throw new RuntimeException("The IMAGE message must contain a URL");
        }
    }

    private static void validateMessageType(String messageType) {
        if (!messageType.equals("TEXT") && !messageType.equals("IMAGE") && !messageType.equals("VIDEO")) {
            throw new IllegalArgumentException("Invalid message type");
        }
    }

    private static void validateField(Object field) {
        if (field == null) {
            throw new IllegalArgumentException("Field cannot be null");
        }

        if (field instanceof String && ((String) field).isEmpty()) {
            throw new IllegalArgumentException("Field cannot be empty");
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                ", messageType='" + messageType + '\'' +
                ", priority=" + priority +
                ", timestamp=" + timestamp +
                '}';
    }
}
