package com.rribeirolima;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import com.rribeirolima.domain.Categoria;
import com.rribeirolima.domain.Cidade;
import com.rribeirolima.domain.Cliente;
import com.rribeirolima.domain.Endereco;
import com.rribeirolima.domain.Estado;
import com.rribeirolima.domain.ItemPedido;
import com.rribeirolima.domain.Pagamento;
import com.rribeirolima.domain.PagamentoComBoleto;
import com.rribeirolima.domain.PagamentoComCartao;
import com.rribeirolima.domain.Pedido;
import com.rribeirolima.domain.Produto;
import com.rribeirolima.domain.enums.EstadoPagamento;
import com.rribeirolima.domain.enums.TipoCliente;
import com.rribeirolima.repositories.CategoriaRepository;
import com.rribeirolima.repositories.CidadeRepository;
import com.rribeirolima.repositories.ClienteRepository;
import com.rribeirolima.repositories.EnderecoRepository;
import com.rribeirolima.repositories.EstadoRepository;
import com.rribeirolima.repositories.ItemPedidoRepository;
import com.rribeirolima.repositories.PedidoRepository;
import com.rribeirolima.repositories.ProdutoRepository;

@SpringBootApplication
public class ImplementandoModeloConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImplementandoModeloConceitualApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// O id esta nulo por que é o banco de dados que vai gerar o id
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Automotivo");
		Categoria cat4 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat5 = new Categoria(null, "Lazer");
		Categoria cat6 = new Categoria(null, "Esportes");
		Categoria cat7 = new Categoria(null, "Jardinagem");
		Categoria cat8 = new Categoria(null, "Livros");
		Categoria cat9 = new Categoria(null, "Brinquedos");
		Categoria cat10 = new Categoria(null, "Áudio");
		Categoria cat11 = new Categoria(null, "Moda");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);

		// Adicionando os produtos as categorias
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		// Adicionado as categorias aos produtos
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		// Quando for salvar tem que seguir a ordem. Primeiro os independentes
		// Salvando os produtos e categorias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cid2 = new Cidade(null, "São Paulo", estado2);
		Cidade cid3 = new Cidade(null, "Campinas", estado2);

		// Adicionando estados as cidades
		estado1.getCidades().addAll(Arrays.asList(cid1));
		estado2.getCidades().addAll(Arrays.asList(cid2, cid3));

		// Salvando as cidades e estados
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cliente1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente1, cid2);

		// Adicionando os enderecos ao cliente
		cliente1.getEnderecos().addAll(Arrays.asList(end1, end2));

		// Salvando cliente e endereços
		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017  10:32"), cliente1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017  19:35"), cliente1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped1.setPagamento(pag1);
		ped2.setPagamento(pag2);

		// Adicionando o cliente ao pedido
		cliente1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		// Salvando pedido e pagamento
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 1, 2000.00, 0.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 2, 80.00, 0.00);
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 2, 800.00, 100.00);
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
