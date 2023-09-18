package io.enably.telefonia.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
	private String telefone;
	private double preco;
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento = Calendar.getInstance();

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Plano> planos = new ArrayList<Plano>();

	public List<Plano> getPlanos() {
		return planos;
	}

	public void adicionaPlano(Plano plano) {
		this.planos.add(plano);
	}

	public Contato() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void removePlano(Plano plano) {
		this.planos.remove(plano);
	}

}