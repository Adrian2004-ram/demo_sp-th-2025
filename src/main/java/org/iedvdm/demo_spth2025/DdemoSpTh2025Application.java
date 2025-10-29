package org.iedvdm.demo_spth2025;

import org.iedvdm.demo_spth2025.dao.ClienteDAO;
import org.iedvdm.demo_spth2025.dao.ClienteDAOImpl;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DdemoSpTh2025Application {

    public static void main(String[] args) {SpringApplication.run(DdemoSpTh2025Application.class, args);}

}