package br.flower.api.fipe.veiculo.caminhao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.flower.api.fipe.veiculo.Veiculo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @DiscriminatorValue uso - @Enum TipoVeiculo.CAMINHAO - 3
 * @author edu
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "3")
public class Caminhao extends Veiculo {
	private static final long serialVersionUID = -1786468333326934233L;


	
	public Caminhao() {
		
	}
}
