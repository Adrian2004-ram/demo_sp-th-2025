package org.iedvdm.demo_spth2025.dao;


import jakarta.annotation.Resource;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Slf4j

@Repository("sqlImpl")
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Cliente cliente) {

        String sql = """
                    insert into cliente (nombre, apellido1, apellido2, ciudad, categoria)
                    values (                  ?,         ?,         ?,      ?,         ?);
                """;

        String[] ids = {"id"};

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(cn -> {

            PreparedStatement ps = cn.prepareStatement(sql, ids);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setString(4, cliente.getCiudad());
            ps.setInt(5, cliente.getCategoria());

            return ps;

        }, keyHolder);

        cliente.setId(keyHolder.getKey().intValue());

    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> listFab = jdbcTemplate.query("""
            select *
            from cliente c
            """,
            //lamda RowMapper ------v
            (rs, rowNum) -> new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido1"),
                    rs.getString("apellido2"),
                    rs.getString("ciudad"),
                    rs.getInt("categoria")
            )
        );

        log.info("Devueltos {} registros", listFab.size());

        return listFab;

    }

    @Override
    public Optional<Cliente> find(int id) {

        try {

            Cliente cliente = jdbcTemplate.queryForObject("""
                    select *
                    from cliente c
                    where c.id = ?
                    """,
                    (rs, rowNum) -> Cliente.builder()
                            .id(rs.getInt("id"))
                            .nombre(rs.getString("nombre"))
                            .apellido1(rs.getString("apellido1"))
                            .apellido2(rs.getString("apellido2"))
                            .ciudad(rs.getString("ciudad"))
                            .categoria(rs.getInt("categoria"))
                            .build()
                    ,
                    id
            );

            return Optional.of(cliente);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }


    }

    @Override
    public void update(Cliente cliente) {

        int rowsUpdate = jdbcTemplate.update("""
                    UPDATE cliente
                    SET nombre = ?,
                        apellido1 = ?,
                        apellido2 = ?,
                        ciudad = ?,
                        categoria = ?
                    WHERE id = ?
                """,
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getCiudad(),
                cliente.getCategoria(),
                cliente.getId());

        log.info("Filas actualizadas {}", rowsUpdate);

    }

    @Override
    public void delete(int id) {

        int rowDelete = jdbcTemplate.update("""
                DELETE
                FROM cliente
                WHERE id = ?
                """, id);

        log.info("Fila eliminada {}", rowDelete);

    }


}
