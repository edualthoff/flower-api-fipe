package br.flower.api.fipe.valor;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.flower.api.fipe.mes.referencia.MesReferencia;
import br.flower.api.fipe.veiculo.Veiculo;
import br.flower.api.fipe.veiculo.Veiculo.VeiculoPk;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "fp_mes_referencia_valor_veiculo")
public class ValorVeiculo implements Serializable {
	
	private static final long serialVersionUID = 8186203801497239729L;

	@Data
	@Embeddable
	public static class ValorVeiculoId implements Serializable {
		private static final long serialVersionUID = 6711922788778711416L;

		@Column(name = "mes_id_fp_mes_referencia")
		@JsonFormat(pattern = "dd/MMMM/yyyy")
		@Setter(value = AccessLevel.NONE)
		private LocalDate mesId;
		@Column(name = "veiculo_id_fp_veiculo")
		@AttributeOverrides(
				value = {
						@AttributeOverride(name = "anoVeiculoId", column = @Column(name="ano_id_fp_ano_fp_veiculo")),
						@AttributeOverride(name = "marcaId", column = @Column(name="marca_id_fp_marca_fp_veiculo")),
						@AttributeOverride(name = "modeloId", column = @Column(name="modelo_id_fp_modelo_fp_veiculo"))
				})
		private Veiculo.VeiculoPk veiculoId;
		
		public ValorVeiculoId() {
			super();
		}

		public ValorVeiculoId(LocalDate mesId, VeiculoPk veiculoId) {
			super();
			this.mesId = mesId;
			this.veiculoId = veiculoId;
		}

		public void setMesId(LocalDate mesId) {
			System.out.println("ValorVeiculoId "+mesId);
			this.mesId = mesId;
		}
		
	}
	
	@EmbeddedId
	private ValorVeiculoId valorVeiculoId;
	
	@Column(name = "valor", nullable = false)
	private String valor;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "mes_id_fp_mes_referencia", referencedColumnName = "mes_id")
	@MapsId("mesId")
	private MesReferencia mesReferencia;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	//foreignKey = @ForeignKey(name = "fp_veiculo_fk"),
	
	@MapsId("veiculoId")
	@Fetch(FetchMode.JOIN)
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
	
	public ValorVeiculo(Veiculo.VeiculoPk veiculoId, LocalDate mesId, String valor, MesReferencia merReferencia, Veiculo veiculo) {
		super();
		this.valorVeiculoId = new ValorVeiculoId(mesId, veiculoId);
		//this.valorVeiculoId.veiculoId = veiculoId;
		//this.valorVeiculoId.mesId = mesId;
		this.mesReferencia = merReferencia;
		this.veiculo = veiculo;
		this.valor = valor;
	}


	public ValorVeiculo(String valor, MesReferencia mesReferencia, Veiculo veiculo) {
		super();
		System.out.println("date "+mesReferencia.getMesId());
		this.valor = valor;
		this.mesReferencia = mesReferencia;
		this.veiculo = veiculo;
	}
	
	
}
