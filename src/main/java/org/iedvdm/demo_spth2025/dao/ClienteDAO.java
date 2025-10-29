package org.iedvdm.demo_spth2025.dao;

import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {

    //Mapear las operaciones CRUD a base de datos
    public void create(Cliente cliente);

    public List<Cliente> getAll();
    public Optional<Cliente> find(int id);

    public void update(Cliente cliente);

    public void delete(int id);

}
