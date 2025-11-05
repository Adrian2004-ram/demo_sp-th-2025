package org.iedvdm.demo_spth2025.sercicio;

import org.iedvdm.demo_spth2025.dao.ClienteDAO;
import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteDAO clienteDAO;

    //alt + insert --> creo constructor
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    //con @AllArgsConstructor se puede ahorar escribir el constructor

    public List<Cliente> all() {
        return clienteDAO.getAll();
    }

}
