package br.flower.api.fipe.veiculo;

public enum TipoVeiculo {

	CARRO(1),
	MOTO(2),
	CAMINHAO(3);
	
	private int value;
	
	TipoVeiculo(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}

	public static TipoVeiculo setVeiculo(int value) {
		switch (value) {
		case 1:
			return CARRO;
		case 2:
			return MOTO;
		case 3:
			return CAMINHAO;
		}
		return null;
	}
	
	public String getStringValue() {
		return String.valueOf(this.value);
	}
}
