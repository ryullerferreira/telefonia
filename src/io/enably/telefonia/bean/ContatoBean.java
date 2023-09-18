package io.enably.telefonia.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import io.enably.telefonia.dao.DAO;
import io.enably.telefonia.modelo.Plano;
import io.enably.telefonia.modelo.Contato;

@ManagedBean
@ViewScoped
public class ContatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contato contato = new Contato();

	private Integer planoId;

	public void setPlanoId(Integer planoId) {
		this.planoId = planoId;
	}

	public Integer getPlanoId() {
		return planoId;
	}

	public Contato getContato() {
		return contato;
	}

	public List<Contato> getContatos() {
		return new DAO<Contato>(Contato.class).listaTodos();
	}

	public List<Plano> getPlanos() {
		return new DAO<Plano>(Plano.class).listaTodos();
	}

	public List<Plano> getPlanosDoContato() {
		return this.contato.getPlanos();
	}

	public void carregarContatoPeloId() {
		this.contato = new DAO<Contato>(Contato.class).buscaPorId(this.contato.getId());
	}

	public void gravarPlano() {
		Plano plano = new DAO<Plano>(Plano.class).buscaPorId(this.planoId);
		this.contato.adicionaPlano(plano);
		System.out.println("Plano: " + plano.getNome());
	}

	public void gravar() {
		System.out.println("Gravando Contato " + this.contato.getNome());

		if (contato.getPlanos().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("plano",
					new FacesMessage("O Contato deve ter pelo menos um Plano Contratado."));
			return;
		}

		if (this.contato.getId() == null) {
			new DAO<Contato>(Contato.class).adiciona(this.contato);
		} else {
			new DAO<Contato>(Contato.class).atualiza(this.contato);
		}

		this.contato = new Contato();
	}

	public void remover(Contato contato) {
		System.out.println("Removendo contato");
		new DAO<Contato>(Contato.class).remove(contato);
	}

	public void removerPlanoDoContato(Plano plano) {
		this.contato.removePlano(plano);
	}

	public void carregar(Contato contato) {
		System.out.println("Carregando contato");
		this.contato = contato;
	}

	public String formPlano() {
		System.out.println("Chamando do formulário do Plano de assinatura.");
		return "plano?faces-redirect=true";
	}

//	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
//
//		String valor = value.toString();
//		if (!valor.startsWith("9")) {
//			throw new ValidatorException(new FacesMessage("Telefone deveria começar com 9"));
//		}
//
//	}
}
