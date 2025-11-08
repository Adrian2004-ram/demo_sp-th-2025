package org.iedvdm.demo_spth2025;

import org.iedvdm.demo_spth2025.dao.ClienteDAO;
import org.iedvdm.demo_spth2025.dao.ComercialDAO;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.iedvdm.demo_spth2025.modelo.Comercial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DdemoSpTh2025ApplicationTests {

    @Autowired @Qualifier("sqlImpl")
    ClienteDAO clienteDAO;

    @Autowired @Qualifier("sqlComercialImpl")
    ComercialDAO comercialDAO;

    @Test
    void prueba() {
        var lines = java.util.List.of("Java 17", "Streams & Lambdas", "Java");
        String out = lines.stream()
            .flatMap(s -> java.util.Arrays.stream(s.toLowerCase().split("\\W+")))

            .peek(System.out::println)

            .filter(w -> !w.isBlank())
            .filter(w -> w.matches("[a-z]+"))
            .distinct()
            .sorted()
            .collect(java.util.stream.Collectors.joining(","));
        System.out.println(out);
    }

    @Test
    void testClienteGetAll() {

        List<Cliente> clientes = clienteDAO.getAll();

        System.out.println(clientes);

        assertNotNull(clientes, "La lista de clientes no debería ser nula");
        assertFalse(clientes.isEmpty(), "La lista de clientes no debería estar vacía");

    }

     @Test
     void testClienteCreate() {

    Cliente cliente = Cliente.builder().nombre("adrian")
            .apellido1("Espada")
            .apellido2("Cerrillo")
            .ciudad("Malaga")
            .categoria(1)
            .build();

    clienteDAO.create(cliente);

    System.out.println("Cliente creado con ID: " + cliente.getId());

    }

    @Test
    void testClienteCreate2() {

        Cliente cliente = Cliente.builder()
                .nombre("Adrian")
                .apellido1("Espada")
                .apellido2("Cerrillo")
                .ciudad("Malaga")
                .categoria(1)
                .build();

        clienteDAO.create(cliente);

        assertTrue(cliente.getId() > 0, "El ID del cliente debería haberse generado");

        Optional<Cliente> clienteDB = clienteDAO.find(cliente.getId());

        assertNotNull(clienteDB, "El cliente guardado debería existir en la base de datos");
        assertEquals("Adrian", clienteDB.get().getNombre());
        assertEquals("Espada", clienteDB.get().getApellido1());
        assertEquals("Cerrillo", clienteDB.get().getApellido2());
        assertEquals("Malaga", clienteDB.get().getCiudad());
        assertEquals(1, clienteDB.get().getCategoria());

        System.out.println("Cliente creado y verificado con ID: " + cliente.getId());

    }

    @Test
    void testClienteUpdate() {

        Cliente cliente = Cliente.builder().nombre("Jose")
                .apellido1("Martín")
                .apellido2("Lopez")
                .ciudad("Malaga")
                .categoria(1)
                .build();

        System.out.println("Cliente actualizado con ID: " + cliente.getId());

        clienteDAO.create(cliente);

        cliente.setNombre("Jose Carlos");

        clienteDAO.update(cliente);

        Optional<Cliente> clienteDB = clienteDAO.find(cliente.getId());

        assertTrue(clienteDB.isPresent(), "El cliente debería existir tras la actualización");
        assertEquals("Jose Carlos", clienteDB.get().getNombre(), "El nombre debería haberse actualizado correctamente");
        assertEquals("Martín", clienteDB.get().getApellido1());
        assertEquals("Lopez", clienteDB.get().getApellido2());
        assertEquals("Malaga", clienteDB.get().getCiudad());
        assertEquals(1, clienteDB.get().getCategoria());

        System.out.println("Cliente actualizado correctamente con ID: " + cliente.getId());

    }

    @Test
    void testClienteDelete() {

        clienteDAO.delete(11);

        Optional<Cliente> clienteDB = clienteDAO.find(11);

        assertTrue(clienteDB.isEmpty(), "El cliente debería haberse eliminado de la base de datos");

        System.out.println("Cliente eliminado correctamente con ID: " + 11);

    }

    @Test
    void testClienteFindById() {

        Optional<Cliente> clienteOpt = clienteDAO.find(1);

        assertTrue(clienteOpt.isPresent(), "El cliente con ID 1 debería existir en la base de datos");

        Cliente cliente = clienteOpt.get();

        System.out.println("Cliente encontrado: " + cliente);

        assertEquals(1, cliente.getId());
        assertNotNull(cliente.getNombre(), "El nombre del cliente no debería ser nulo");
        assertNotNull(cliente.getApellido1(), "El primer apellido no debería ser nulo");
    }

    @Test
    void testComercialGetAll() {

        List<Comercial> comerciales = comercialDAO.getAll();

        System.out.println(comerciales);

        assertNotNull(comerciales, "La lista de clientes no debería ser nula");
        assertFalse(comerciales.isEmpty(), "La lista de clientes no debería estar vacía");


    }

    @Test
    void testComercialCreate() {

        Comercial comercial = Comercial.builder()
                .nombre("Adrian")
                .apellido1("Espada")
                .apellido2("Cerrillo")
                .comision(0.15)
                .build();

        comercialDAO.create(comercial);

        Optional<Comercial> comercialDB = comercialDAO.find(comercial.getId());

        assertTrue(comercialDB.isPresent(), "El comercial guardado debería existir en la base de datos");
        assertEquals("Adrian", comercialDB.get().getNombre());
        assertEquals("Espada", comercialDB.get().getApellido1());
        assertEquals("Cerrillo", comercialDB.get().getApellido2());
        assertEquals(0.15, comercialDB.get().getComision());

        System.out.println("Comercial creado y verificado con ID: " + comercial.getId());

    }

    @Test
    void testComercialUpdate() {

        Comercial comercial = Comercial.builder()
                .nombre("Fuenanta")
                .apellido1("Rodriguez")
                .apellido2("Guzman")
                .comision(5.00)
                .build();

        System.out.println("Comercial actualizado con ID: " + comercial.getId());

        comercialDAO.create(comercial);

        comercial.setNombre("Maria");

        comercialDAO.update(comercial);


        Optional<Comercial> comercialDB = comercialDAO.find(comercial.getId());

        assertTrue(comercialDB.isPresent(), "El cliente debería existir tras la actualización");
        assertEquals("Maria", comercialDB.get().getNombre(), "El nombre debería haberse actualizado correctamente");
        assertEquals("Rodriguez", comercialDB.get().getApellido1());
        assertEquals("Guzman", comercialDB.get().getApellido2());
        assertEquals(5.00, comercialDB.get().getComision());

        System.out.println("Comercial actualizado correctamente con ID: " + comercial.getId());
    }

    @Test
    void testComercialDelete() {

        comercialDAO.delete(11);

        Optional<Comercial> comercialDB = comercialDAO.find(11);

        assertTrue(comercialDB.isEmpty(), "El cliente debería haberse eliminado de la base de datos");

        System.out.println("Cliente eliminado correctamente con ID: " + 11);
    }

    @Test
    void testComercialFindById() {

        Optional<Comercial> comercialOpt = comercialDAO.find(1);

        assertTrue(comercialOpt.isPresent(), "El comercial con ID 1 debería existir en la base de datos");

        Comercial comercial = comercialOpt.get();

        System.out.println("Comercial encontrado: " + comercial);

        assertEquals(1, comercial.getId());
        assertNotNull(comercial.getNombre(), "El nombre del comercial no debería ser nulo");
        assertNotNull(comercial.getApellido1(), "El primer apellido no debería ser nulo");
        assertNotNull(comercial.getApellido2(), "El segundo apellido no debería ser nulo");

    }

}
