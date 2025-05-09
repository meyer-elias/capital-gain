# Code Challenge: Capital Gains

---

## Contexto

O objetivo deste exercício é implementar um programa de linha de comando (CLI) que calcula o imposto a ser
pago sobre lucros ou prejuízos de operações no mercado financeiro de ações.

Para melhor entendimento, consulte o documento [Challenge Capital Gain](/docs/Code_Challenge__Ganho_de_Capital_ptbr.pdf)

----

## Considerações

A solução desenvolvida utilizou as seguintes tecnologias:

- **Java 21 (21.0.2-openJdk)**;
- **Gradle (gradle-8.10)**;

Além disso, as bibliotecas abaixo foram utilizadas:

- **Json Jackson (jackson-databind:2.18.3)**: Para manipulações em Json;
- **Testes Unitários**:
    - Junit5 (5.10.0);
    - Assertj (3.27.2);
    - json-unit-assertj (4.1.0);

O projeto seguiu o príncipio de Hexagonal Architecture a fim de isolar o domínio e incrementar outras **ports** conforme for a evoluir da solução.
Portanto, obedeceu o seguinte layout:

```
/capital-gain (root)
├── capital-gain-core (module)
│   └── src/main/java
│       └── com.nubank.capitalgain.core
│           ├── calculator
│           │   ├── application
│           │   ├── exception
│           │   ├── domain
│           │   └── model
│           └── commons
└── capital-gain-adapter-cli (module)
    └── src/main/java
        └── com.nubank.capitalgain.adapter.cli
            ├── in
            └── CapitalGainMain.java
```

No package `capital-gain-core/com.nubank.capitalgain.core/commons` há os tipos de objetos primitivos para facilitar o encapsulamento e incrementar a
legibilidade.
O `model` são objetos mais elaborados e fazem sentido para melhor entendimento do contexto do negócio. Veja:

Já no `calculator` reside o cálculo. No meu entendimento, é necessário efetuar o cálculo do ganho de capital conforme a transação
de compra ou venda das ações. Além disso, sobre este comportamento, é necessário adicionar outro comportamento (taxar) esse lucro/perda e, portanto,
utilizei o pattern `Decorator` para aplicar o cálculo
do imposto. Imaginei que, dessa forma, outros tipos ou regras de cobranças poderiam ser inseridas nessa estrutura caso fosse desejado.

Por fim, foram criados testes unitários para validação de alguns pontos. São:

- Cenários/Casos de testes dispostos no enunciado do desafio para validação;
- Escrita e leitura de Json;

Há informações de javadoc no código para facilitar melhor compreensão da solução.

## Executar

Descompacte o arquivo .zip. Após isso poderá executar:

##### Docker

Abra um 'shell' (terminal) e execute o seguinte comando na raiz do projeto:

```dockerfile
docker build -t capital-gain . && docker run -it capital-gain
```

##### Gradle e Java

Efetue o build do projeto via gradle:

```groovy
gradle build
  ```

Vá o diretório **build** e execute:

```java
java -jar ./libs/capital-gain-adapter-cli-1.0-SNAPSHOT.jar
```
    