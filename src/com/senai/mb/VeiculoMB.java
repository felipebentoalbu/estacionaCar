package com.senai.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senai.dao.ClienteDAO;
import com.senai.dao.VeiculoDAO;
import com.senai.entidades.Cliente;
import com.senai.entidades.Veiculo;

@ManagedBean
@ViewScoped
public class VeiculoMB {

	private Veiculo veiculo;

	private List<Veiculo> veiculos;
	private VeiculoDAO veiculoDAO = new VeiculoDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();

	private String nomeDisp;

	@PostConstruct
	private void init() {

		veiculos = new ArrayList<Veiculo>();
		veiculos = veiculoDAO.buscarTodos();
		limpaVeiculo();
	}

	public List<Cliente> getListaCli() {
		ClienteDAO dao = new ClienteDAO();
		return dao.buscarTodos();
	}

	public String getNomeDisp() {
		return nomeDisp;
	}

	public void setNomeDisp(String nomeDisp) {
		this.nomeDisp = nomeDisp;
	}

	public String excluir() {
		veiculoDAO.delete(veiculo);
		limpaVeiculo();
		return "";
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (veiculo.getId() == null) {
			veiculoDAO.insere(veiculo);
			limpaVeiculo();
		} else {
			veiculoDAO.atualizar(veiculo);
			limpaVeiculo();
		}
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Salvo com sucesso!", ""));

		return "";
	}

	private void limpaVeiculo() {
		veiculos = veiculoDAO.buscarTodos();
		veiculo = new Veiculo();
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
}
