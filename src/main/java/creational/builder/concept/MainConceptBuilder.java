package creational.builder.concept;

/**
 * 🏗️ <b>PATRÓN DE DISEÑO: BUILDER</b>
 * <p>
 * Imagina que estás armando la computadora de tus sueños. No quieres que te
 * entreguen una caja cerrada con piezas que no elegiste; quieres decidir cada
 * detalle: qué procesador lleva, cuánta memoria necesita y qué tan potente
 * será su gráfica.
 * </p>
 * <p>
 * El patrón <b>Builder</b> es como ese técnico experto que te acompaña en el
 * proceso. Su misión es separar la construcción de un objeto complejo de su
 * representación, permitiéndote crear distintas configuraciones de un objeto
 * paso a paso, de manera clara y sin errores.
 * </p>
 * * 🌟 <b>¿POR QUÉ USARLO?</b>
 * <ul>
 * <li>✅ <b>Legibilidad:</b> Evita los "constructores monstruosos" con 10 parámetros iguales.</li>
 * <li>✅ <b>Flexibilidad:</b> Puedes omitir piezas opcionales sin problemas.</li>
 * <li>✅ <b>Inmutabilidad:</b> El objeto final (ConceptBuilder) solo se crea cuando está todo listo.</li>
 * <li>✅ <b>Fluidez:</b> Permite el encadenamiento de métodos (Method Chaining).</li>
 * </ul>
 * * 🔧 <b>COMPONENTES CLAVE:</b>
 * <ul>
 * <li><b>cpu(String)</b>: Define el cerebro de la máquina.</li>
 * <li><b>gpu(String)</b>: Define el poder gráfico.</li>
 * <li><b>storage(String)</b>: Define la capacidad de almacenamiento.</li>
 * <li><b>ram(String)</b>: Define la memoria de trabajo.</li>
 * <li><b>build()</b>: El momento mágico donde se ensambla y entrega el objeto final.</li>
 * </ul>
 * * 💻 <b>EJEMPLO DE USO:</b>
 * <pre>{@code
 * ConceptBuilder pcGamer = ConceptBuilder.builder()
 * .cpu("Intel i9")
 * .gpu("RTX 4090")
 * .ram("64GB")
 * .storage("2TB NVMe")
 * .build();
 * * System.out.println(pcGamer);
 * // Resultado: ConceptBuilder{cpu='Intel i9', gpu='RTX 4090', ...}
 * }</pre>
 */

public class MainConceptBuilder {

    void main() {
        ConceptBuilder pcGamer = ConceptBuilder.builder()
                .cpu("Intel i9")
                .gpu("RTX 4090")
                .ram("64GB")
                .storage("2TB NVMe")
                .build();

        IO.println(pcGamer);
    }
}

class ConceptBuilder {
    private final String cpu;
    private final String gpu;
    private final String storage;
    private final String ram;

    private ConceptBuilder(Builder builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.storage = builder.storage;
        this.ram = builder.ram;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String cpu;
        private String gpu;
        private String storage;
        private String ram;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        ConceptBuilder build() {
            return new ConceptBuilder(this);
        }
    }

    @Override
    public String toString() {
        return "ConceptBuilder{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", storage='" + storage + '\'' +
                ", ram='" + ram + '\'' +
                '}';
    }
}
