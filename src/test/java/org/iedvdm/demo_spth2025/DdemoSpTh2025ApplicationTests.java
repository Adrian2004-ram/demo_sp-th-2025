package org.iedvdm.demo_spth2025;

import org.iedvdm.demo_spth2025.dao.ClienteDAO;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DdemoSpTh2025ApplicationTests {

    @Autowired
    ClienteDAO clienteDAO;

    @Test
    void contextLoads() {
    }

    @Test
    void prueva() {
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
    public void comprueba() {

        List<Cliente> list = clienteDAO.getAll();

        list.forEach(System.out::println);

    }


}
