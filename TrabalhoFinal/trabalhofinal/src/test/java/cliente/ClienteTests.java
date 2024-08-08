package cliente;

import cliente.model.ClienteConcreto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTests {

    @Test
    public void testeCriarClienteInvalido() {
        assertThrows(RuntimeException.class, () -> {
            new ClienteConcreto(0, null, null, null, null, null, null);
        });
    }
}
