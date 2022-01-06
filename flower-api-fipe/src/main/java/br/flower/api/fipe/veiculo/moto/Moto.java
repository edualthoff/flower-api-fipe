package br.flower.api.fipe.veiculo.moto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.flower.api.fipe.veiculo.Veiculo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @DiscriminatorValue uso - @Enum TipoVeiculo.MOTO - 2
 * @author edu
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("2")
public class Moto extends Veiculo {
	private static final long serialVersionUID = -3047287922058803361L;

	
	public Moto() {}
}
