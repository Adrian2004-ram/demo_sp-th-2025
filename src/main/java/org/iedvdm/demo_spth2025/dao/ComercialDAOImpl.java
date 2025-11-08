package org.iedvdm.demo_spth2025.dao;

import lombok.extern.slf4j.Slf4j;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.iedvdm.demo_spth2025.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j

@Repository("sqlComercialImpl")
public class ComercialDAOImpl implements ComercialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {

        String sql = """
                    insert into comercial (nombre, apellido1, apellido2, comision)
                    values (                  ?,         ?,         ?,        ?);
                """;

        String[] ids = {"id"};

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(cn -> {

            PreparedStatement ps = cn.prepareStatement(sql, ids);

            ps.setString(2, comercial.getApellido1());
            ps.setString(1, comercial.getNombre());
            ps.setString(3, comercial.getApellido2());
            ps.setDouble(4, comercial.getComision());


            return ps;

        }, keyHolder);

        comercial.setId(keyHolder.getKey().intValue());

    }

    @Override
    public List<Comercial> getAll() {
        List<Comercial> listFab = jdbcTemplate.query("""
            select *
            from comercial c
            """,
                //lamda RowMapper ------v
                (rs, rowNum) -> new Comercial(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getDouble("comision")
                )
        );

        log.info("Devueltos {} registros", listFab.size());

        return listFab;

    }

    @Override
    public Optional<Comercial> find(int id) {

        try {

            Comercial comercial = jdbcTemplate.queryForObject("""
                    select *
                    from comercial c
                    where c.id = ?
                    """,
                    (rs, rowNum) -> Comercial.builder()
                            .id(rs.getInt("id"))
                            .nombre(rs.getString("nombre"))
                            .apellido1(rs.getString("apellido1"))
                            .apellido2(rs.getString("apellido2"))
                            .comision(rs.getDouble("comision"))
                            .build()
                    ,
                    id
            );

            return Optional.of(comercial);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }


    }

    @Override
    public void update(Comercial comercial) {

        int rowsUpdate = jdbcTemplate.update("""
                    UPDATE comercial
                    SET nombre = ?,
                        apellido1 = ?,
                        apellido2 = ?,
                        comision = ?
                    WHERE id = ?
                """,
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                comercial.getId());

        log.info("Filas actualizadas {}", rowsUpdate);

    }

    @Override
    public void delete(int id) {

        int rowDelete = jdbcTemplate.update("""
                DELETE
                FROM comercial
                WHERE id = ?
                """, id);

        log.info("Fila eliminada {}", rowDelete);

    }



}
