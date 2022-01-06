package br.flower.api.fipe.valor;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.flower.api.fipe.mes.referencia.MesReferencia;
import br.flower.api.fipe.veiculo.Veiculo;
import lombok.Data;

/*
@Data
@Entity
@Table(name = "fp_mes_referencia_valor_veiculo")*/
public class ValorVeiculoOld2 implements Serializable {
	private static final long serialVersionUID = -7977792517232189771L;

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "valor_id")
	private Long valorVeiculoId;
	
	@Column(name = "valor", nullable = false)
	private String valor;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "mes_id_fp_mes_referencia", referencedColumnName = "mes_id")
	private MesReferencia mesId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//foreignKey = @ForeignKey(name = "fp_veiculo_fk"),
	@JoinColumns(
		value =  {
			@JoinColumn(name = "ano_id_fp_ano_fp_veiculo", referencedColumnName = "ano_id_fp_ano"  ),
			@JoinColumn(name = "marca_id_fp_marca_fp_veiculo", referencedColumnName = "marca_id_fp_marca"),
			@JoinColumn(name = "modelo_id_fp_modelo_fp_veiculo", referencedColumnName = "modelo_id_fp_modelo"),
		})
	private Veiculo veiculo;
	
	public ValorVeiculo() {
		super();
	}

	public ValorVeiculo(String valor, MesReferencia mesId) {
		super();
		this.valor = valor;
		this.mesId = mesId;
	}
	public ValorVeiculo(String valor, MesReferencia mesId, Veiculo veiculo) {
		super();
		this.valor = valor;
		this.mesId = mesId;
		this.veiculo = veiculo;
	}*/
}
