package creational.exercise;

/**
 * 🏆 <b>RETO SUPREMO: BUILDER + FACTORY METHOD + ABSTRACT FACTORY</b>
 * <p>
 * Una corporación automotriz produce vehículos <b>Eléctricos</b> y de <b>Combustión</b>.
 * Cada tipo de vehículo tiene su propia familia de componentes (Motores y Baterías/Tanques).
 * Además, cada vehículo es tan complejo (color, rines, GPS, asientos) que requiere un Builder.
 * </p>
 *
 * 📌 <b>TU MISIÓN:</b>
 * <ol>
 * <li>
 *   <b>Abstract Factory (Las Familias):</b>
 *   Crea {@code IVehicleFactory} para producir familias de componentes:
 *   {@code ElectricFactory} y {@code GasolineFactory}.
 * </li>
 * <li>
 *   <b>Builder (La Construcción):</b>
 *   Crea una clase {@code Car} con atributos: {@code brand}, {@code model}, {@code engineType},
 *   {@code color}, y {@code hasSunroof}. Usa el patrón Builder.
 * </li>
 * <li>
 *   <b>Factory Method (La Decisión):</b>
 *   Crea una clase {@code VehicleCustomizer} con un método estático que, según el tipo
 *   de mercado ("ECO" o "POWER"), te devuelva la <b>Abstract Factory</b> correcta.
 * </li>
 * </ol>
 *
 * 💻 <b>FLUJO ESPERADO:</b>
 * <pre>{@code
 * // 1. Factory Method decide qué familia usar
 * IVehicleFactory factory = VehicleCustomizer.getFactory("ECO"); // Devuelve ElectricFactory
 *
 * // 2. La Abstract Factory nos da el tipo de motor base
 * String engine = factory.createEngine();
 *
 * // 3. El Builder construye el auto con ese motor y detalles extra
 * Car myCar = Car.builder()
 *                .engineType(engine)
 *                .color("Tesla White")
 *                .hasSunroof(true)
 *                .build();
 * }</pre>
 */

public class MainAutomotive {
    void main() {
        IVehicleFactory factory = VehicleCustomizer.getFactory("ECO");

        String engine = factory.createEngine();
        String fuel = factory.createFuelSource();

        Car myCar = Car.builder()
                .brand("Tesla")
                .model("Model S")
                .engineType(engine + " alimentado por " + fuel)
                .color("Tesla White")
                .hasSunroof(true)
                .build();

        IO.println(myCar);
    }
}

interface IVehicleFactory {
    String createEngine();
    String createFuelSource();
}

class ElectricFactory implements IVehicleFactory {
    @Override
    public String createEngine() { return "Motor de Inducción Eléctrica"; }
    @Override
    public String createFuelSource() { return "Batería de Litio 100kWh"; }
}

class GasolineFactory implements IVehicleFactory {
    @Override
    public String createEngine() { return "Motor V8 de Combustión"; }
    @Override
    public String createFuelSource() { return "Tanque de Gasolina Premium"; }
}

class Car {
    private final String brand;
    private final String model;
    private final String engineType;
    private final String color;
    private final Boolean hasSunroof;

    private Car(BuilderCar builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.engineType = builder.engineType;
        this.color = builder.color;
        this.hasSunroof = builder.hasSunroof;
    }

    public static BuilderCar builder() { return new BuilderCar(); }

    public static class BuilderCar {
        private String brand, model, engineType, color;
        private Boolean hasSunroof = false;

        public BuilderCar brand(String brand) { this.brand = brand; return this; }
        public BuilderCar model(String model) { this.model = model; return this; }
        public BuilderCar engineType(String engineType) { this.engineType = engineType; return this; }
        public BuilderCar color(String color) { this.color = color; return this; }
        public BuilderCar hasSunroof(Boolean hasSunroof) { this.hasSunroof = hasSunroof; return this; }

        public Car build() { return new Car(this); }
    }

    @Override
    public String toString() {
        return "🚗 VEHÍCULO ENSAMBLADO:\n" +
                "Marca: " + brand + " | Modelo: " + model + "\n" +
                "Propulsión: " + engineType + "\n" +
                "Extras: Color " + color + ", Sunroof: " + (hasSunroof ? "SÍ" : "NO");
    }
}

class VehicleCustomizer {
    public static IVehicleFactory getFactory(String marketType) {
        return switch (marketType.toUpperCase()) {
            case "ECO" -> new ElectricFactory();
            case "POWER" -> new GasolineFactory();
            default -> throw new IllegalArgumentException("Mercado no soportado");
        };
    }
}
