package com.rribeirolima.domain;

import javax.persistence.Entity;
import com.rribeirolima.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numerParcelas;

	public PagamentoComCartao() {
		
	}
	
	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numerParcelas) {
		super(id, estadoPagamento, pedido);
		this.numerParcelas = numerParcelas;
	}

	public Integer getNumerParcelas() {
		return numerParcelas;
	}

	public void setNumerParcelas(Integer numerParcelas) {
		this.numerParcelas = numerParcelas;
	}
	
}
