package br.flower.api.fipe.veiculo.carro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.flower.api.fipe.veiculo.Veiculo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 *  @DiscriminatorValue uso - @Enum TipoVeiculo.CARRO - 1
 * @author edu
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "1")
public class Carro extends Veiculo {
	private static final long serialVersionUID = 5413125738411815361L;

	public Carro() {}
}
