package br.flower.api.fipe.veiculo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "fp_veiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_veiculo", discriminatorType = DiscriminatorType.INTEGER)
public class Veiculo implements Serializable {
	private static final long serialVersionUID = -5416299417165480444L;

	@Embeddable
	@Data
	public static class VeiculoPk implements Serializable {
		private static final long serialVersionUID = -7732954123013728809L;

		@Column(name = "ano_id_fp_ano", nullable = false)
		private String anoVeiculoId;

		@Column(name = "marca_id_fp_marca", nullable = false)
		private Long marcaId;

		@Column(name = "modelo_id_fp_modelo", nullable = false)
		private Long modeloId;

		public VeiculoPk() {
			super();
		}

		public VeiculoPk(String anoVeiculoId, Long marcaId, Long modeloId) {
			super();
			this.anoVeiculoId = anoVeiculoId;
			this.marcaId = marcaId;
			this.modeloId = modeloId;
		}
		public VeiculoPk(Long marcaId, Long modeloId, String anoVeiculoId) {
			super();
			this.anoVeiculoId = anoVeiculoId;
			this.marcaId = marcaId;
			this.modeloId = modeloId;
		}
		
	}


	@EmbeddedId
	private VeiculoPk veiculoPk;
	
	@Column(name = "codigo_auto", insertable = false, updatable = false)
	private Long codigoAuto;

	@Column(name = "ano_modelo")
	private int anoModelo;

	/*
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_veiculo")
	private TipoVeiculo TIPO_VEICLO;*/
	
	@MapsId("anoVeiculoId")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "ano_id_fp_ano", referencedColumnName = "ano_id", nullable = false)
	private Ano anoVeiculo;
	
	@MapsId("marcaId")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "marca_id_fp_marca", referencedColumnName = "marca_id", nullable = false, updatable = true)
	private Marca marca;

	@MapsId("modeloId")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "modelo_id_fp_modelo", referencedColumnName = "modelo_id", nullable = false)
	private Modelo modelo;

	//@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "veiculo", fetch = FetchType.LAZY)
	//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	//private Set<ValorVeiculo> valorVeiculos;
	
	public Veiculo() {
		super();
	}

	public Veiculo(VeiculoPk veiculoPk) {
		super();
		this.veiculoPk = veiculoPk;
	}
}
