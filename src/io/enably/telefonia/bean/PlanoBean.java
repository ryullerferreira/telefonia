package io.enably.telefonia.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import io.enably.telefonia.dao.DAO;
import io.enably.telefonia.modelo.Plano;

@ManagedBean
@ViewScoped
public class PlanoBean {

	private Plano plano = new Plano();
	
	private Integer planoId;
	
	

	public Integer getPlanoId() {
		return planoId;
	}

	public void setPlanoId(Integer planoId) {
		this.planoId = planoId;
	}
	
	public void carregarPlanoPeloId() {
		this.plano = new DAO<Plano>(Plano.class).buscaPorId(planoId);
	}

	public String gravar() {
		System.out.println("Gravando plano " + this.plano.getNome());

		if(this.plano.getId() == null) {
			new DAO<Plano>(Plano.class).adiciona(this.plano);
		} else {
			new DAO<Plano>(Plano.class).atualiza(this.plano);
		}

		this.plano = new Plano();

		return "contato?faces-redirect=true";
	}
	
	public void remover(Plano plano) {
		System.out.println("Removendo plano " + plano.getNome());
		new DAO<Plano>(Plano.class).remove(plano);
	}
	
	public List<Plano> getPlanos() {
		return new DAO<Plano>(Plano.class).listaTodos();
	}
	
	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}
}
