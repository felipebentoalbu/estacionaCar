package com.senai.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senai.dao.ClienteDAO;
import com.senai.entidades.Cliente;

@ManagedBean
@ViewScoped
public class ClienteMB {

	private Cliente cliente;

	private List<Cliente> clientes;
	private ClienteDAO clienteDAO = new ClienteDAO();

	private String nomeDisp;

	@PostConstruct
	private void init() {

		clientes = new ArrayList<Cliente>();
		clientes = clienteDAO.buscarTodos();
		limpaCliente();
	}

	public String getNomeDisp() {
		return nomeDisp;
	}

	public void setNomeDisp(String nomeDisp) {
		this.nomeDisp = nomeDisp;
	}

	public String excluir() {
		clienteDAO.delete(cliente);
		limpaCliente();
		return "";
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (cliente.getId() == null) {
			clienteDAO.insere(cliente);
			limpaCliente();
		} else {
			clienteDAO.atualizar(cliente);
			limpaCliente();
		}
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Salvo com sucesso!", ""));

		return "";
	}

	private void limpaCliente() {
		clientes = clienteDAO.buscarTodos();
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
