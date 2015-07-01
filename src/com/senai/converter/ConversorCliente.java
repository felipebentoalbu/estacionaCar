package com.senai.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.senai.dao.ClienteDAO;
import com.senai.entidades.Cliente;

@FacesConverter(value = "conversorCliente")
public class ConversorCliente implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ClienteDAO dao = new ClienteDAO();
		Cliente func = dao.buscarNome(arg2);
		return func;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Cliente func = new Cliente();
		func = (Cliente) arg2;
		return func.getNomeCliente();

	}
}
