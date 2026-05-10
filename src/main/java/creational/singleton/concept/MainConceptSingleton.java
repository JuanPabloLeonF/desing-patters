package creational.singleton.concept;

/**
 * 🔒 <b>PATRÓN DE DISEÑO: SINGLETON</b>
 * <p>
 * Algunos recursos o servicios solo deben existir <b>una vez</b> en toda la aplicación:
 * un cliente de configuración cargado desde disco, un pool de conexiones, un registro
 * global de eventos, el “motor” central de un videojuego, etc.
 * </p>
 * <p>
 * El patrón <b>Singleton</b> garantiza que una clase tenga como mucho una instancia
 * y proporciona un punto de acceso global conocido ({@code getInstance()}, u otro método
 * estático equivalente).
 * </p>
 *
 * <p>
 * ⚠️ <b>Matiz práctico:</b> usar Singleton como “comodín” suele llevar al acoplamiento
 * global y pruebas difíciles. Útil con moderación y muchas veces se prefiere
 * “una sola instancia” gestionada por un contenedor de inyección de dependencias
 * sin patrón clásico.
 * </p>
 *
 * * 🌟 <b>¿POR QUÉ USARLO?</b>
 * <ul>
 * <li>✅ <b>Un solo estado compartido:</b> Todas las partes del programa ven los mismos datos.</li>
 * <li>✅ <b>Control de recurso único:</b> Evitas abrir dos veces un canal costoso si no debe existir dos veces.</li>
 * <li>✅ <b>Lugar único para inicializar:</b> El primer acceso puede preparar configuración pesada una sola vez.</li>
 * </ul>
 *
 * * 🔧 <b>COMPONENTES CLAVE (variante habitual en Java):</b>
 * <ul>
 * <li><b>Constructor privado:</b> Impide {@code new} desde fuera y fuerza usar el método de obtención.</li>
 * <li><b>Instancia única:</b> Almacenada en un campo estático (directo o mediante clase interna “holder”).</li>
 * <li><b>Método estático de acceso:</b> P. ej. {@code ConceptSettings#getInstance()}, que siempre devuelve la misma referencia.</li>
 * </ul>
 *
 * * 🧵 <b>VARIANTE MOSTRADA: “initialization-on-demand holder”:</b>
 * <p>
 * La clase interna estática {@code Holder} carga cuando alguien llama por primera vez
 * a {@code getInstance()}, lo crea lazy y el cargador de clases Java ya garantiza
 * seguridad ante hilos en esa inicialización, sin usar {@code synchronized} explícito.
 * </p>
 *
 * * 💻 <b>EJEMPLO DE USO:</b>
 * <pre>{@code
 * ConceptSettings a = ConceptSettings.getInstance();
 * ConceptSettings b = ConceptSettings.getInstance();
 *
 * IO.println(a == b);           // true: misma instancia
 * a.setAppName("DesignPatterns");
 * IO.println(b.getAppName());  // DesignPatterns
 * }</pre>
 */

public class MainConceptSingleton {

    void main() {
        ConceptSettings first = ConceptSettings.getInstance();
        ConceptSettings second = ConceptSettings.getInstance();

        IO.println("¿Misma instancia? " + (first == second));

        first.setAppName("DesignPatterns");
        IO.println(second.getAppName());

        ConceptSettings.configureDebug(true);
        IO.println("¿Debug desde la otra referencia? " + second.isDebugEnabled());
    }
}


class ConceptSettings {

    /**
     * Clase holder: la JVM carga Holder la primera vez que se usa en {@code getInstance()}.
     */
    private static final class Holder {
        static final ConceptSettings INSTANCE = new ConceptSettings();
    }

    private String appName = "(sin configurar)";
    private boolean debugEnabled;

    private ConceptSettings() {
        IO.println("ConceptSettings inicializado una sola vez.");
    }

    public static ConceptSettings getInstance() {
        return Holder.INSTANCE;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? "(sin configurar)" : appName;
    }

    public String getAppName() {
        return appName;
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /** Cambia comportamiento sobre la única instancia compartida. */
    static void configureDebug(boolean enabled) {
        getInstance().debugEnabled = enabled;
    }

    @Override
    public String toString() {
        return "ConceptSettings{appName='" + appName + "', debugEnabled=" + debugEnabled + '}';
    }
}
