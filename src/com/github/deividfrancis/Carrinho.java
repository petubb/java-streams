package com.github.deividfrancis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Carrinho {

	private static List<Produto> produtoList = new ArrayList<Produto>();

	static {

		produtoList.add(new Produto(744, "Redragon Kumara",	243.35,	"Tecnologia", true));
		produtoList.add(new Produto(850, "Redragon Cobra",	190.20, "Tecnologia", true));
		produtoList.add(new Produto(022, "SSD M.2",	102.02, "Tecnologia", true));
		produtoList.add(new Produto(848, "Monitor 24p 165hz" , 1153.00, "Tecnologia", false));
		produtoList.add(new Produto(254, "Oculos de ciclismo", 104.02, "Esporte", true));
		produtoList.add(new Produto(78, "Molinete de pesca", 175.00, "Esporte", false));
		produtoList.add(new Produto(415, "Tenis allstar", 167.00, "Vestuario", true));
		produtoList.add(new Produto(403, "Luva de motociclista", 134.00, "Vestuario", false));
		produtoList.add(new Produto(625, "Chave de fenda magnética", 55.00, "Equipamentos", true));
		produtoList.add(new Produto(573, "Tapete Geometrico", 114.00, "Lazer", true));

	}
	public static void main(String[] args) {

		filtraItensTecnologia();

		ItensCaros();

		temOuNao();

		somaEsporte();

		achaPrimeiroEquipamento();

		ordenaAlf();

		agrupaPorCategoria();

		categoriaMaisCara();

		idEspecifico();

	}

	//1 - FILTRA PRODUTOS PELA CATEGORIA "TECNOLOGIA"
	private static void filtraItensTecnologia() {
		imprimeTitulo("Produtos de Tecnologia");


		List<String> nomes = produtoList.stream()
				.filter(p -> p.getCategoria() == "Tecnologia")
				.map(Produto::getNome)
				.collect(Collectors.toList());

		System.out.println(nomes);
	}

	//2 - LISTA OS PRODUTOS COM O PREÇO ACIMA DE R$200,00
	private static void ItensCaros() {
		imprimeTitulo("Itens Caros *+R$200,00*");

		List<String> caro = produtoList.stream()
				.filter(p -> p.getValor() > 200.00)
				.map(Produto::getNome)
				.collect(Collectors.toList());

		System.out.println(caro);

		//		imprimeTitulo("Itens Caros *+R$200*");
		//		List<String> caro = produtoList.stream()
		//				.filter(p -> p.getValor() > 200.00)
		//				.map(Produto::getNome)
		//				.collect(Collectors.toList());
		//
		//		System.out.println(caro);
	}

	//3 - LISTA OS PRODUTOS EM ESTOQUE
	private static void temOuNao() {
		imprimeTitulo("Produtos em estoque");

		List<String> temEstoque = produtoList.stream()
				.filter(p -> p.getTemEstoque() == true)
				.map(Produto::getNome)
				.collect(Collectors.toList());

		System.out.println(temEstoque);
	}

	//4 - SOMA TODOS O PREÇO DE TODOS OS PRODUTOS DA CATEGORIA ESPORTE
	private static void somaEsporte() {
		imprimeTitulo("Soma dos Produtos de Esporte");

		List<String> itens = produtoList.stream()
				.filter(p -> p.getCategoria() == "Esporte")
				.map(Produto::getNome)
				.collect(Collectors.toList());
		System.out.println(itens);		


		List<Double> valores = produtoList.stream()
				.filter(p -> p.getCategoria() == "Esporte")
				.map(Produto::getValor)
				.collect(Collectors.toList());
		System.out.println(valores);		

		double valor = produtoList.stream()
				.filter(p -> p.getCategoria() == "Esporte")
				.map(x -> x.getValor())
				.reduce((x,y) -> x + y)
				.orElse(0.0);
		System.out.println("total: " + valor);
	}

	//5 - PROCURA PELO PRIMEIRO EQUIPAMENTO
	private static void achaPrimeiroEquipamento() {
		imprimeTitulo("Primeiro equipamento");

		Produto ProdutoFirst = produtoList.stream()
				.filter(p -> p.getCategoria() == "Equipamentos")
				.findFirst()
				.orElse(null);

		System.out.println(ProdutoFirst.getNome());
	}


	//6 - ORDENA OS PRODUTOS EM ORDEM ALFABETICA
	private static void  ordenaAlf() {
		imprimeTitulo("Produtos em ordem alfabetica");
		List<String> produtoAlfabeticoList = produtoList.stream()
				.map(p -> p.getNome())
				.sorted()
				.collect(Collectors.toList());

		produtoAlfabeticoList.forEach(s -> System.out.println(s));
	}


	//7 - AGRUPA PRODUTOS POR CATEGORIA 
	private static void agrupaPorCategoria() {
		imprimeTitulo("Agrupando por categoria");

		Map<String, List<Produto>> produtoMap = produtoList.stream()
				.collect(Collectors.groupingBy(Produto::getCategoria));
		produtoMap.forEach((s, p) -> {
			System.out.println(s); 
			p.forEach(prod -> produto(prod));
			System.out.println("/////////////////"); //<--- só pra separar
		});
	}

	//8 - COMPARA CATEGORIAS E 
	private static void categoriaMaisCara() {
		imprimeTitulo("Categoria Mais Cara");
		Entry<String, Double> categoriaMaisCara = produtoList.stream()
				.collect(Collectors.groupingBy(Produto::getCategoria, 
						 Collectors.summingDouble(Produto::getValor)))
				.entrySet()
				.stream()
				.max(Comparator.comparing(Map.Entry::getValue))
				.orElse(null);

		System.out.println(categoriaMaisCara.getKey() + " - " + categoriaMaisCara.getValue());
	}

	//9 - PROCURA PELOS IDS ESPECIFICOS "850, 403, 625"
	private static void idEspecifico() {
		imprimeTitulo("Ids Especificos");

		List<Integer> idEsp = Arrays.asList(850, 403, 625);
		produtoList.stream()
		.filter(p -> idEsp.contains(p.getId()))
		.toList();

		System.out.println(idEsp);
	}



	//metodo pra deixar os titulos bonitinhos
	private static void imprimeTitulo(String titulo) {
		int width = 30;
		String token = "#";
		int space = (((titulo.length() + 2) - width) / 2) * -1;
		String titleFull = token + " ".repeat(space) + titulo + " ".repeat(space) + token;

		System.out.println("\n");
		System.out.println(token.repeat(width));
		System.out.println(titleFull);
		System.out.println(token.repeat(width));
	}

	//metodo pra formatar produtos
	private static void produto(Produto produto) {
		Integer id = produto.getId();
		String nome = produto.getNome();
		String categoria = produto.getCategoria();
		Double valor = produto.getValor();
		Boolean temEstoque = produto.getTemEstoque();

		System.out.println(id + " - " + nome + "/ " + categoria + "/ " + valor + "/ " +  temEstoque);
	}

}
