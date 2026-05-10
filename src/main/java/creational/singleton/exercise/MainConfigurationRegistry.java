package creational.singleton.exercise;

/**
 * 🏆 <b>RETO: REGISTRO DE CONFIGURACIÓN (SINGLETON)</b>
 * <p>
 * Una aplicación servidor arranca muchos componentes que necesitan leer parámetros
 * (<i>timeouts</i>, URL de bases de datos, claves opcionales, etc.).
 * Para no cargar ficheros repetidamente desde cada clase, debe existir <b>un solo</b>
 * registro de configuración usable por todo el sistema.
 * </p>
 *
 * 📌 <b>TU MISIÓN:</b>
 * <ol>
 * <li>
 *   Implementa una clase {@code ConfigurationRegistry} con el patrón <b>Singleton</b>
 *   (constructor privado + acceso único mediante {@code getInstance()}).
 * </li>
 * <li>
 *   El registro debe permitir:<br/>
 *   {@code void put(String key, String value)} y {@code String get(String key)}.
 *   Si la clave no existe, {@code get} debe devolver {@code null}.
 * </li>
 * <li>
 *   Añade un método {@code void loadDefaults()} que inicialice algunas parejas predefinidas
 *   (ej. {@code "app.name"} → {@code "design-patterns-lab"}}) <b>solo si</b> el mapa está vacío
 *   al momento de cargar.
 * </li>
 * <li>
 *   Documenta por qué un Singleton tiene sentido en este caso y cuando sería mejor
 *   no usar patrón clásico (p. ej. inyección de dependencias).
 * </li>
 * </ol>
 *
 * 💻 <b>RESULTADO ESPERADO (ESQUEMA DE USO):</b>
 * <pre>{@code
 * ConfigurationRegistry reg = ConfigurationRegistry.getInstance();
 * reg.loadDefaults();
 * reg.put("db.url", "jdbc:postgresql://localhost:5432/demo");
 *
 * ConfigurationRegistry mismo = ConfigurationRegistry.getInstance();
 * IO.println(reg == mismo);                    // debe ser true
 * IO.println(mismo.get("app.name"));            // debe mostrar valor por defecto
 * }</pre>
 *
 * ⚠️ <b>REGLA DE ORO:</b>
 * Nadie debe poder hacer {@code new ConfigurationRegistry()}; el acceso público debe ser
 * solo vía tu método {@code getInstance()}.
 */

public class MainConfigurationRegistry {

    void main() {
        // TODO: implementar ConfigurationRegistry como Singleton y probar desde aquí.
    }
}
