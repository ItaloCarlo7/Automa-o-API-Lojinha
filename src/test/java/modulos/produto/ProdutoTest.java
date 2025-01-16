package modulos.produto;
import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.List;
@DisplayName("Testes de Api Rest do modulo de Produto")
public class ProdutoTest {
     private String token;
     @BeforeEach
     public void beforeEach() {
         // Configurando os dados da Api Rest da Lojinha
         baseURI = "http://165.227.93.41";
         // port = 8080;
         basePath = "/lojinha";


         // Obter o Token do usuário admin
         this.token = given()
                 .contentType(ContentType.JSON)
                 .body(UsuarioDataFactory.tokenDoUsuario())
             .when()
                 .post("/v2/login")

             .then()
                 .extract()
                     .path("data.token");

     }
    @Test
    @DisplayName("Validar os limites proibidos do valor do Produto")
    public void testValidarLimitesProibidosValorProduto() {
        // token , extração de dados
        // Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        // Status code retornado foi 422
       given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))

       .when()
                .post("/v2/produtos")

       .then()
                .assertThat()
                     .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                       .statusCode(422);
        //then , teste.
    }
    @Test
    @DisplayName("Validar os limites proibidos acima de Sete mil do valor do Produto")
    public void testValidarLimiteMaiorSeteProibidoValorProduto() {
        // Tentar inserir um produto com valor 7000.01 e validar que a mensagem de erro foi apresentada e o
        // Status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                 .header("token", this.token)
                 .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))

         .when()
                .post("/v2/produtos")

         .then()
                .assertThat()
                  .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                     .statusCode(422);


    }
    @Test
    @DisplayName("Validar os limites proibidos abaixo de 0,00 do valor do Produto")
    public void testValidarLimiteMenorProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(-0.00))

                .when()
                .post("/v2/produtos")

                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);


    }
}
