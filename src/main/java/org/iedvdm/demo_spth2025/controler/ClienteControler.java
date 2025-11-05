package org.iedvdm.demo_spth2025.controler;

import org.iedvdm.demo_spth2025.modelo.Cliente;
import org.iedvdm.demo_spth2025.sercicio.ClienteService;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class ClienteControler {

    private ClienteService clienteService;

    public ClienteControler(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //EMPOINT para la ruta http:/localhost
    @GetMapping("/clientes")
    public String listarClientes(Model model) {

        List<Cliente> clienteList = clienteService.all();

        model.addAttribute("listaClientes", clienteList);

        return "clientes";
    }

}
