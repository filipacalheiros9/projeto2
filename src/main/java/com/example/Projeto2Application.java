package com.example;

import com.example.projeto2.models.*;
import com.example.projeto2.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.time.LocalDate;

//@SpringBootApplication
public class Projeto2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Projeto2Application.class, args);

		ClienteService clienteService = context.getBean(ClienteService.class);
		CodPostalClienteService codPostalClienteService = context.getBean(CodPostalClienteService.class);
		CodPostalFornecedorService codPostalFornecedorService = context.getBean(CodPostalFornecedorService.class);
		FaturaClienteService faturaClienteService = context.getBean(FaturaClienteService.class);
		FornecedorService fornecedorService = context.getBean(FornecedorService.class);
		FuncionarioService funcionarioService = context.getBean(FuncionarioService.class);
		HorarioService horarioService = context.getBean(HorarioService.class);
		LinhaEncomendaService linhaEncomendaService = context.getBean(LinhaEncomendaService.class);
		MaterialService materialService = context.getBean(MaterialService.class);
		PagamentoFaturaFornecedorService pagamentoFaturaFornecedorService = context.getBean(PagamentoFaturaFornecedorService.class);
		ProjetoService projetoService = context.getBean(ProjetoService.class);
		ProjetoMaterialService projetoMaterialService = context.getBean(ProjetoMaterialService.class);
		ServicoService servicoService = context.getBean(ServicoService.class);
		TipoImpressaoService tipoImpressaoService = context.getBean(TipoImpressaoService.class);
		TipoProjetoService tipoProjetoService = context.getBean(TipoProjetoService.class);
		TipoFuncionarioService tipoFuncionarioService = context.getBean(TipoFuncionarioService.class);

		CodPostalCliente codPostalCliente = new CodPostalCliente();
		codPostalCliente.setCodpostalcliente("1000-001");
		codPostalCliente.setLocalidade("Lisboa");
		System.out.println("Código Postal salvo: " + codPostalCliente.getCodpostalcliente());

		Cliente cliente = new Cliente();
		cliente.setId(123456);
		cliente.setNomecliente("Maria Ferreira");
		cliente.setTelefonecliente("919876543");
		cliente.setRuacliente("Rua das Flores");
		cliente.setNportacliente(25);
		cliente.setNifcliente("987654321");
		cliente.setCodpostalcli(codPostalCliente);

		clienteService.salvar(cliente);
		System.out.println("Cliente salvo: " + cliente.getNomecliente());

		CodPostalFornecedor codPostalFornecedor = new CodPostalFornecedor();
		codPostalFornecedor.setCodpostalfornecedor("1000-102");
		codPostalFornecedor.setLocalidade("Porto");

		codPostalFornecedorService.salvar(codPostalFornecedor);
		System.out.println("Código Postal de Fornecedor salvo: " + codPostalFornecedor.getCodpostalfornecedor());


		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(1234);
		fornecedor.setNomefornecedor("Fornecedor XYZ");
		fornecedor.setNiffornecedor("87654321");
		fornecedor.setTelefonefornecedor("919876893");
		fornecedor.setRuafornecedor("Rua do Fornecedor");
		fornecedor.setNportafornecedor(15);
		fornecedor.setCodpostalforn(codPostalFornecedor);

		fornecedorService.salvar(fornecedor);
		System.out.println("Fornecedor salvo: " + fornecedor.getNomefornecedor());

		FaturaCliente faturaCliente = new FaturaCliente();
		faturaCliente.setId(1001);
		faturaCliente.setDatafatura(LocalDate.now());
		faturaCliente.setValorfatura(150.75);

		faturaClienteService.salvar(faturaCliente);
		System.out.println("Fatura do cliente salva: " + faturaCliente.getId());

		TipoFuncionario tipoFuncionario = new TipoFuncionario();
		tipoFuncionario.setId(1);
		tipoFuncionario.setCargo("Gerente");

		tipoFuncionarioService.salvarTipoFuncionario(tipoFuncionario);
		System.out.println("Tipo de funcionário salvo: " + tipoFuncionario.getCargo());

		Funcionario funcionario = new Funcionario();
		funcionario.setId(1001);
		funcionario.setNomefuncionario("Maria Souza");
		funcionario.setUsername("mariasouza");
		funcionario.setPassword("123");
		funcionario.setIdtipofunc(tipoFuncionario);

		funcionarioService.salvar(funcionario);
		System.out.println("Funcionário salvo: " + funcionario.getNomefuncionario());

		Horario horario = new Horario();
		horario.setId(1);
		horario.setData(LocalDate.now());
		horario.setIdfunc(funcionario);
		horario.setHoraini("08:00");
		horario.setHorafim("16:00");

		horarioService.salvar(horario);
		System.out.println("Horário salvo para o funcionário: " + horario.getIdfunc().getNomefuncionario());

		Material material = new Material();
		material.setId(1001);
		material.setNomematerial("Papel");
		material.setQtdstockmaterial(500);
		material.setPrecomaterial(45.75);

		materialService.salvarMaterial(material);
		System.out.println("Material salvo com sucesso!");

		LinhaEncomendaId id = new LinhaEncomendaId();
		id.setIdfornecedor(fornecedor.getId());
		id.setIdmaterial(material.getId());

		LinhaEncomenda linhaEncomenda = new LinhaEncomenda();
		linhaEncomenda.setId(id);
		linhaEncomenda.setIdfornecedor(fornecedor);
		linhaEncomenda.setIdmaterial(material);
		linhaEncomenda.setQtdencomendada(100);
		linhaEncomenda.setValorencomendado(250.0);

		linhaEncomendaService.salvar(linhaEncomenda);
		System.out.println("Linha de encomenda salva!");

		PagamentoFaturaFornecedor pagamento = new PagamentoFaturaFornecedor();
		pagamento.setId(12345);
		pagamento.setDtpagamento(LocalDate.of(2025, 3, 18));
		pagamento.setValor(250.0);
		pagamento.setIdforn(fornecedor);
		pagamento.setDtfatura(LocalDate.of(2025, 3, 10));
		pagamentoFaturaFornecedorService.salvarPagamento(pagamento);

		System.out.println("Pagamento salvo com sucesso!");

		TipoProjeto tipoProjeto = new TipoProjeto();
		tipoProjeto.setId(1);
		tipoProjeto.setTipoprojeto("Design Gráfico");

		tipoProjetoService.salvarTipoProjeto(tipoProjeto);
		System.out.println("Tipo de Projeto salvo com ID: " + tipoProjeto.getId());

		Projeto projeto = new Projeto();
		projeto.setId(1);
		projeto.setTemaprojeto("Novo Projeto de Design");
		projeto.setTipoletra("Arial");
		projeto.setTamanho("12");
		projeto.setIdclien(cliente);
		projeto.setIdtipoproj(tipoProjeto);

		projetoService.salvarProjeto(projeto);
		System.out.println("Projeto salvo com ID: " + projeto.getId());




		TipoImpressao TipoImpressao = new TipoImpressao();
		TipoImpressao.setId(1);
		TipoImpressao.setTipoimpressao("Impressão Digital");

		tipoImpressaoService.salvarTipoImpressao(TipoImpressao);
		System.out.println("Tipo de Impressão salvo com ID: " + TipoImpressao.getId());

		Servico servico = new Servico();
		servico.setId(1);
		servico.setDataservico(LocalDate.now());
		servico.setPrecoservico(150.00);
		servico.setEstadoservico("Em andamento");
		servico.setIdproj(projeto);
		servico.setIdfuncio(funcionario);
		servico.setIdtipoimpre(TipoImpressao);

		servicoService.salvarServico(servico);
		System.out.println("Serviço salvo com ID: " + servico.getId());

		ProjetoMaterialId ids = new ProjetoMaterialId();
		ids.setIdmaterial(material.getId());
		ids.setIdprojeto(projeto.getId());

		ProjetoMaterial projetoMaterial = new ProjetoMaterial();
		projetoMaterial.setId(ids);
		projetoMaterial.setIdmaterial(material);
		projetoMaterial.setIdprojeto(projeto);

		projetoMaterialService.salvarProjetoMaterial(projetoMaterial);
		context.close();
	}
}
