package com.senai.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(filterName="AutenticacaoFilter",urlPatterns="/pages/*")
public class AutenticacaoFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		Integer idUserSession = (Integer) session.getAttribute("idUser");

		// Se o usuario nao esta na sessao, nao estao logado...
		if (idUserSession != null && idUserSession > 0) {
			chain.doFilter(request, response);
		} else {

			// Redireciona p/ pagina de login
			httpResponse.sendRedirect(httpRequest.getContextPath()
					+ "/index.jsf");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
