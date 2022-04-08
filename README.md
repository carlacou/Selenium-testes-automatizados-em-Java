# Selenium: testes automatizados de aceitação em Java

O **Selenium** é uma ferramenta utilizada para automatização de testes de sistemas, também conhecido como Teste de Aceitação, E2E (End-to-End) ou Teste de UI (User Interface) que permite ao usuário reproduzi-los rapidamente no ambiente real da aplicação, em função da sua integração direta com o navegador.


## :footprints: Passo-a-Passo adicionando o Selenium no projeto

Abaixo da dependência h2 banco de dados, precisamos adicionar o **Selenium** ao projeto. Como estamos utilizando o Maven, podemos baixar e instalar o Selenium na aplicação simplesmente adicionando-o como uma dependência. Para isso, abriremos o arquivo pom.xml, buscaremos a área de dependências e adicionaremos, após a dependência com.h2database, a do Selenium. Isso requer que saibamos o groupId e o artifactId desta dependência. 

Encontrado no site do Selenium <https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/install_library/> :

```
<dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
        </dependency>

    </dependencies>
```

Após salvarmos o arquivo, voltaremos para nossa classe de teste. Agora que já temos a API disponível, podemos escrever nosso primeiro teste automatizado utilizando o **Selenium**. A ideia desse teste é abrirmos o navegador e entrarmos na página principal do nosso projeto, http://localhost:8080/leiloes.

Para abrir o navegador, existe uma interface principal do **Selenium** chamada **WebDriver**. Criaremos uma variável browser do tipo **WebDriver** e importaremos esta interface do pacote **org.openqa.selenium.WebDriver**. Sendo uma interface, no momento da instanciação precisamos passar qual será a implementação. 
Já que queremos utilizar o Chrome, instanciaremos um novo ChromeDriver() e o importaremos. Se quiséssemos utilizar o Firefox, teríamos new FirefoxDriver, e assim por diante para cada navegador específico.

```
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

    @Test
    public void hello() {

        WebDriver browser = new ChromeDriver();
    }
}

```

Sendo assim, além de baixarmos a dependência do **Selenium** por meio do pom.xml, também precisamos baixar o driver do Google Chrome <https://chromedriver.chromium.org/downloads>.
É ideal que você baixe a versão respectiva ao navegador que você tem instalado em sua máquina, de preferência atualizando o seu Chrome para ter disponível a versão mais recente.
Em seguida, criaremos no projeto um diretório "drivers" dentro da qual manteremos o driver que acabamos de baixar. Todos os drivers que quisermos utilizar posteriormente serão salvos nessa pasta.
Agora precisamos apontar para o Selenium que o driver do Chrome está no diretório que criamos. Na documentação disponível no site do Selenium encontramos a seção "Driver requirements" (requisitos de driver). Na parte específica do Java, ele indica a linha que informa ao Selenium o caminho do executável.

```
System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
```

Ao executarmos, teremos um novo erro indicando que o driver encontrado não é executável. Isso acontece no Linux e no MacOS, pois o arquivo baixado não está com permissão de escrita. Para resolvermos, abriremos o Terminal/Prompt de comando, acessaremos o diretório em que está o driver e substituiremos a sua permissão de execução (no Linux) com:

```
chmod +x chromedriver
```

Após executarmos, voltaremos ao teste e tentaremos executá-lo novamente.

Nosso navegador será aberto, mas não conseguiremos entrar no endereço - nesse caso, somente porque nosso projeto não está sendo executado. Resolveremos esse problema acessando a classe **LeilaoApplicationJava** e executando-a com botão direito seguido de **"Run as > Java Application".**

Ao rodarmos o teste, uma janela do navegador será aberta, acessará o endereço da aplicação e será fechada em seguida. Na aba do JUnit veremos que 1 teste foi executado e que ele passou com sucesso.

Esse foi o nosso "hello world" com **Selenium**. Perceba que foi um processo um pouco complicado, já que tivemos que fazer todo o passo a passo de baixar as dependências, baixar o driver, alterar suas permissões de execução, setar o caminho do driver e escrever o teste em si com os passos específicos. Não se preocupe, pois essas configurações só precisam ser feitas uma vez, e daqui para frente somente nos preocuparemos em escrever os testes.

```
public class HelloWorldSelenium {

    @Test
    public void hello() {
        System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit();
    }
}
```

# Page object models

Page Object é um Design Pattern que se tornou popular na automação de testes para aprimorar a manutenção de testes e reduzir a duplicação de código. Um objeto de página é uma classe orientada a objetos que serve como interface para uma página do seu AUT. Os testes usam os métodos dessa classe de objeto de página sempre que precisam interagir com a interface do usuário dessa página. A vantagem é que, se a interface do usuário for alterada para a página, os testes em si não precisarão ser alterados, apenas o código dentro do objeto da página precisará ser alterado. Subsequentemente, todas as alterações para dar suporte a essa nova interface do usuário estão localizadas em um só lugar.


Na documentação do Selenium, é possível encontrar uma página de diretrizes e recomendações <https://www.selenium.dev/documentation/test_practices/encouraged/>.


