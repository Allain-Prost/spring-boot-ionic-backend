package com.allain.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allain.curso.domain.Categoria;
import com.allain.curso.domain.Cidade;
import com.allain.curso.domain.Cliente;
import com.allain.curso.domain.Endereco;
import com.allain.curso.domain.Estado;
import com.allain.curso.domain.ItemPedido;
import com.allain.curso.domain.Pagamento;
import com.allain.curso.domain.PagamentoComBoleto;
import com.allain.curso.domain.PagamentoComCartao;
import com.allain.curso.domain.Pedido;
import com.allain.curso.domain.Produto;
import com.allain.curso.domain.enuns.EstadoPagamento;
import com.allain.curso.domain.enuns.TipoCliente;
import com.allain.curso.repositories.CategoriaRepository;
import com.allain.curso.repositories.CidadeRepository;
import com.allain.curso.repositories.ClienteRepository;
import com.allain.curso.repositories.EnderecoRepository;
import com.allain.curso.repositories.EstadoRepository;
import com.allain.curso.repositories.ItemPedidoRepository;
import com.allain.curso.repositories.PagamentoRepository;
import com.allain.curso.repositories.PedidoRepository;
import com.allain.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
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
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria (null,"Informática");
		Categoria cat2 = new Categoria (null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProduto().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProduto().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado estd1 = new Estado(null,"Minas Gerais");
		Estado estd2 = new Estado(null,"São Paulo");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Cidade c1 = new Cidade(null, "Uberlâdia", estd1);
		Cidade c2 = new Cidade(null, "São Paulo", estd2);
		Cidade c3 = new Cidade(null, "Campinas", estd2);
		
		estd1.getCidades().addAll(Arrays.asList(c1));
		estd2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(estd1,estd2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente (null, "Maria Silva", "maria@gmail.com", "129812345572", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("993839999", "2349598264"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300","Apto 303", "Jardim", "38221234", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105","Sala 800", "Centro", "9874236346", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/06/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2, sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1, 0.00, 1, 2000.00);	
		ItemPedido ip2 = new ItemPedido(ped1,p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
