package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Play Station 5");
        produto.setProdutoValor(0.00);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();
        // Add componente Controle a lista componentes.
        ComponentePojo componente = new ComponentePojo();
        componente.setComponeteNome("Controle");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        // Add segundoComponente a lista componentes.
        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponeteNome("Memory card");
        segundoComponente.setComponenteQuantidade(2);
        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;


    }


}
