package org.iedvdm.demo_spth2025;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DdemoSpTh2025ApplicationTests {

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

}
