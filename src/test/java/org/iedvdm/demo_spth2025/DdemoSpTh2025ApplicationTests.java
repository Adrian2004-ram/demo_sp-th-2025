package org.iedvdm.demo_spth2025;

import org.iedvdm.demo_spth2025.dao.ClienteDAO;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DdemoSpTh2025ApplicationTests {

    @Autowired @Qualifier("sqlImpl")
    ClienteDAO clienteDAO;

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
    void testGetAll() {

        System.out.println(clienteDAO.getAll());

    }

     @Test
     void testCreate() {

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
    void testUpdate() {

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
    }

    @Test
    void testDelete() {

        clienteDAO.delete(11);

    }

    @Test
    void testFindById() {

        System.out.println(clienteDAO.find(1));

    }

}
