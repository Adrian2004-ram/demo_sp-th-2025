package org.iedvdm.demo_spth2025.dao;

import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.iedvdm.demo_spth2025.modelo.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {

    //Mapear las operaciones CRUD a base de datos
    public void create(Comercial comercial);

    public List<Comercial> getAll();

    public Optional<Comercial> find(int id);

    public void update(Comercial comercial);

    public void delete(int id);


}
