# Code Challenge: Capital Gains

---

## Contexto

O objetivo deste exercício é implementar um programa de linha de comando (CLI) que calcula o imposto a ser
pago sobre lucros ou prejuízos de operações no mercado financeiro de ações.

### Entrada

Seu programa vai receber listas, uma por linha, de operações do mercado financeiro de ações em formato json através da entrada padrão (stdin). Cada
operação desta lista contém os seguintes campos:

| **Nome**    | **Significado**                                              |
|-------------|--------------------------------------------------------------|
| *operation* | Se a operação é uma operação de compra (buy) ou venda (sell) |
| *unit-cost* | Preço unitário da ação em uma moeda com duas casas decimais  |
| *quantity*  | Quantidade de ações negociadas                               |

Este é um exemplo da entrada:

```
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]
```

As operações estarão na ordem em que elas ocorreram, ou seja, a segunda operação na lista aconteceu depois da primeira e assim por diante.

Cada linha é uma simulação independente, seu programa não deve manter o estado obtido em uma linha para as outras.
A última linha da entrada será uma linha vazia.

### Saída

Para cada linha da entrada, o programa deve retornar uma lista contendo o imposto pago para cada operação recebida.
Os elementos desta lista devem estar codificados em formato json e a saída deve ser retornada através da saída padrão (stdout). O retorno é composto
pelo seguinte campo:

| **Nome** | **Significado**                         |
|----------|-----------------------------------------|
| *tax*    | O valor do imposto pago em uma operação |

```
[{"tax":0.00}, {"tax":10000.00}]
[{"tax":0.00}, {"tax":0.00}]
```

## Regras do Ganho de Capital

A lista retornada pelo programa deve ter o mesmo tamanho da lista de operações processadas na entrada. Por exemplo, se foram processadas três
operações (buy, buy, sell), o retorno do programa deve ser uma lista com três valores que representam o imposto pago em cada
operação. O programa deve lidar com dois tipos de operações (buy e sell) e ele deve seguir as seguintes regras:

- O percentual de imposto pago é de 20% sobre o lucro obtido na operação. Ou seja, o imposto vai ser pago quando
  há uma operação de venda cujo preço é superior ao preço médio ponderado de compra.

- O percentual de imposto pago é de 20% sobre o lucro obtido na operação. Ou seja, o imposto vai ser pago quando há
  uma operação de venda cujo preço é superior ao preço médio ponderado de compra.
    - Para determinar se a operação resultou em lucro ou prejuízo, você pode calcular o preço médio
      ponderado, então quando você compra ações você deve recalcular o preço médio ponderado
      utilizando essa fórmula:

  > nova-média-ponderada = ((quantidade-de-ações-atual * média-ponderada-atual) + (quantidade-de-ações-compradas * valor-de-compra)) /
  (quantidade-de-ações-atual + quantidade-de-ações-compradas).

  Por exemplo, se você comprou 10 ações por R\$ 20,00, vendeu 5, depois comprou outras 5 por R\$ 10,00, a média ponderada é:
  > ((5 x 20.00) + (5 x 10.00)) / (5 + 5) = 15.00.

- Você deve usar o prejuízo passado para deduzir múltiplos lucros futuros, até que todo prejuízo seja deduzido.

- Prejuízos acontecem quando você vende ações a um valor menor do que o preço médio ponderado de compra. Neste caso, nenhum imposto deve ser pago e
  você deve subtrair o prejuízo dos lucros
  seguintes, antes de calcular o imposto. Você não paga nenhum imposto se o valor total da operação (custo unitário da ação x quantidade) for
  menor ou igual a R$ 20000,00. Use o valor total da operação e não o lucro obtido para determinar se o imposto deve ou não ser pago. E não se esqueça
  de deduzir o prejuízo dos lucros seguintes.

- Nenhum imposto é pago em operações de compra.

- Você pode assumir que nenhuma operação vai vender mais ações do que você tem naquele momento.

----

## Considerações

A solução desenvolvida utilizou as seguintes tecnologias:

- **Java 21 (21.0.2-openJdk)**;
- **Gradle (gradle-8.10)**;

Além disso, as bibliotecas abaixos foram utilizadas:

- **Apache Commons Lang3 (commons-lang3:3.17.0)**: Para tratativas de String;
- **Json Jackson (jackson-databind:2.18.3)**: Para manipulações em Json;
- **Testes Unitários**:
    - Junit5 (5.10.0);
    - Assertj (3.27.2);
    - json-unit-assertj (4.1.0);

O projeto seguiu os príncipios de Hexagonal Architecture a fim de isolar o domínio e incrementar outras **ports** conforme for evoluindo a solução.
Portanto, obedeceu o seguinte layout:

```
/
└── capital-gain
    └── src
        └── java
            └── com.nubank.capitalgain
                ├── adapter
                │   └── in
                │       ├── JsonMapper
                │       └── TaxCalculatorAdapterConsoleCli
                ├── application
                │       ├── domain
                │       │   ├── calculator
                │       │   ├── commons
                │       │   ├── dto
                │       │   ├── exceptions
                │       │   └── primitives
                │       └── ports
                │           └── in
                │               ├── TaxCalculatorUseCasePort
                │               └── TaxCalculatorUseCasePortImpl
                └── CapitalGainMain
```

No package `application/domain/primitives` há os tipos de objetos primitivos para facilitar o encapsulamento e incrementar a legibilidade.
O `commons` são objetos mais elaborados e fazem sentido para melhor entendimento do contexto do negócio. Vide:

```
application
└── domain
    ├── primitives
    │   ├── Money.java
    │   ├── Percentage.java
    │   ├── Quantity.java
    │   └── ZeroMoney.java
    └── commons
        ├── Fee.java
        ├── Stock.java
        ├── Trade.java
        └── TypeOperation.java
```

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

Abra um shell (terminal) e execute o seguinte comando na raiz do projeto:

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
java -jar ./libs/capital-gain-1.0-SNAPSHOT.jar
```
    