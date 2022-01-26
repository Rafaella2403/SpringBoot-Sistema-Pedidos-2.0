package com.rribeirolima.domain.enums;

public enum TipoCliente {
	/*
	 	O JPA cria os códigos do enum automáticamente, porém dessa forma a manutenção do código pode ficar arriscada, 
	  levando em consideração que é possivel alterar a ordem da numeração se a ordem dos nomes for alterada.
	  Por isso vou criar o código de forma manual e colocar uma descrição.  
	 */
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	//Contrutures para o tipo enumerado tem que ser private
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	//Vou criar apenas o método get para o código e a descrição por que uma vez que é instanciado não pode ser alterado
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	//Método para converter o código em enum
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente tipoCliente : TipoCliente.values()) {
			if(cod.equals(tipoCliente.getCod()))
				return tipoCliente;
		}
		
		throw new IllegalArgumentException("O id " + cod + " é inválido!");
		
	}
	
}
