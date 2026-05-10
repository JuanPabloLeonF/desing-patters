package creational.abstract_factory.excersice;

/**
 * 🏆 <b>RETO: SISTEMA DE UI MULTIPLATAFORMA</b>
 * <p>
 * Estás creando una herramienta que permite dibujar interfaces de usuario.
 * El cliente quiere que la aplicación funcione en <b>Windows</b> y <b>Mac</b>.
 * </p>
 *
 * 📌 <b>TU MISIÓN:</b>
 * <ol>
 * <li><b>Crear los Productos Abstractos:</b>
 *     Define las interfaces {@code Button} y {@code Checkbox}.
 *     Ambas deben tener un método {@code render()}.
 * </li>
 * <li><b>Crear los Productos Concretos:</b>
 *     - Para Windows: {@code WindowsButton} y {@code WindowsCheckbox}.
 *     - Para Mac: {@code MacButton} y {@code MacCheckbox}.
 *     (Cada uno debe imprimir un mensaje diferente en su método render).
 * </li>
 * <li><b>Crear la Fábrica Abstracta:</b>
 *     Interfaz {@code IGUIFactory} con los métodos {@code createButton()} y {@code createCheckbox()}.
 * </li>
 * <li><b>Crear las Fábricas Concretas:</b>
 *     {@code WindowsFactory} y {@code MacFactory} que devuelvan los objetos de su familia.
 * </li>
 * </ol>
 *
 * 💻 <b>RESULTADO ESPERADO:</b>
 * <pre>{@code
 * IGUIFactory factory = new WindowsFactory();
 * Button btn = factory.createButton();
 * btn.render(); // "Renderizando Botón estilo Windows con borde cuadrado."
 * }</pre>
 *
 * ⚠️ <b>REGLA DE ORO:</b> El código en tu método {@code main} NO debe preguntar
 * si el botón es de Windows o Mac. Solo debe usar la fábrica.
 */

public class MainUIFactory {
    void main() {

        IGUIFactory factoryWindows = new WindowsFactory();
        IButton btn = factoryWindows.createButton();
        btn.render();

        ICheckbox checkbox = factoryWindows.createCheckbox();
        checkbox.render();

        IGUIFactory factoryMac = new MacFactory();
        IButton btnMac = factoryMac.createButton();
        btnMac.render();
        ICheckbox checkboxMac = factoryMac.createCheckbox();
        checkboxMac.render();

    }
}

interface IButton {
    void render();
}

interface ICheckbox {
    void render();
}

class WindowsButton implements IButton {

    @Override
    public void render() {
        IO.println("Renderizando Botón estilo Windows con borde cuadrado.");
    }
}

class WindowsCheckbox implements ICheckbox {

    @Override
    public void render() {
        IO.println("Renderizando Checkbox estilo Windows con borde cuadrado.");
    }
}

class MacButton implements IButton {

    @Override
    public void render() {
        IO.println("Renderizando Botón estilo Mac con borde cuadrado.");
    }
}

class MacCheckbox implements ICheckbox {

    @Override
    public void render() {
        IO.println("Renderizando Checkbox estilo Mac con borde cuadrado.");
    }
}

interface IGUIFactory {
    IButton createButton();
    ICheckbox createCheckbox();
}

class WindowsFactory implements IGUIFactory  {

    @Override
    public IButton createButton() {
        return new WindowsButton();
    }

    @Override
    public ICheckbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements IGUIFactory  {

    @Override
    public IButton createButton() {
        return new MacButton();
    }

    @Override
    public ICheckbox createCheckbox() {
        return new MacCheckbox();
    }
}