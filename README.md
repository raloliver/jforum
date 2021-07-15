# SPRING FRAMEWORK & SPRING BOOT

- Um dos frameworks Java mais antigos da atualidade
- Livro que também motivou a criação do Spring Framework (por **Red Johnson**): **Expert one-on-one: J2EE Design and Development without EJB**
- Com a chegada do Spring Boot por volta de 2013, o Spring Framework voltou a ser popular, principalmente pelo fato de não ser necessário a utilização de um container de execução, e sim com um JAR, além de ser simples e de rápida configuração (facilitado pelo uso das *annotations*).
- E todas as boas práticas e facilidades do Spring Boot (criação de API Rest com *microservices*) resultaram numa excelente adesão do mercado, o que deixou de lado outros frameworks (Spring Cloud).


### NOTES

- `LocalDateTime`: API de data do (Java 8)
- Classe `Arrays` possui o método `asList` (métod estático) que recebe como parêmetros items de uma lista (no exemplo a seguir, objetos), exemplo: `return Arrays.asList(topic, topic, topic)`
- **Jackson**: biblioteca responsável por converter as respostas de um lista para o formato JSON (stringfy)
- `spring-boot-devtools`: dependencia responsavel por evitar o rebuild da app a cada alteração. Lembre-se de adicionar o `scope` como `runtime`; O módulo DevTools inclui ferramentas utilitárias no projeto, dentre elas a Automatic Restart, que reinicia o servidor automaticamente ao detectar alterações no código fonte da aplicação.
- DTO (Data Transfer Object): muito utilizado para retornar apenas os valores específicos e não todos os atributos da classe modelo. Geralmente para este tipo de classe, usamos apenas *getters* e um "constructor".
- REST: Representational State Transfer. Quais **recursos** serão gerenciadas no sistema? Você pode utilizar uma as **URI** para identificar esses recursos e para manipular essas informações, é necessário utilizarmos os **verbos HTTP** e por fim, a API recebe e devolve **representações dos recursos** (que são feitas através dos **medias types**)
- Comunicação Stateless: as apps não guardam o state do lado do servidor (sessions).


### ANNOTATIONS

- `@ResponseBody`: Por padrão, o Spring considera que o retorno do método é o nome da página que ele deve carregar, mas ao utilizar a anotação `@ResponseBody`, indicamos que o retorno do método deve ser serializado e devolvido no corpo da resposta. Para evitar a repetição dessa anotação, podemos usar como anotação principal da classe, o `@RestController`.
- `@Entity`: toda entidade (modelo) deve ser anotada dessa maneira, pois as mesmas são as classes de domínio que representam as tabelas no banco de dados. Com essa anotação, a criação da tabela ocorre de forma automatica.
- `@GeneratedValue`: possui o atributo `strategy` onde deve ser informado se é `IDENTITY` para auto-increment ou `SEQUENCE`, exemplo: `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
- `@Enumerated`: por padrão os atributos do tipo `Enum` são guardados de maneira ordenada no banco de dados, para evitar isso, você pode customizar, exemplo: `@Enumerated(EnumType.STRING)`.
- Cardinalidades: `@ManyToOne` e `@OneToMany`. No caso do segundo exemplo, é necessário informar o tipo de mapeado, exemplo: `@OneToMany(mappedBy = "topic")`, onde "topic" é o nome do atributo da classe que estou anotando.
### JPA

- **JPA**: especificação do Java para banco de dados.
- `spring-boot-starter-data-jpa`
- H2: banco de dados em memória. A partir do arquivo **application.properties** é possível configurar o acesso ao painel admin do H2, onde é necessario apontar no campo **JDBC URL** a url do datasource (atenção ao username e password).
- **application.properties**: arquivo necessário para configuração do datasource, jpa e do banco de dados escolhido. Nesse arquivo devem ser declaradas as configurações da aplicação, inclusive as relacionadas ao banco de dados dela.
- Arquivo **data.sql** em resources: o SB gera automaticamente esses dados no banco de dados como arquivos de inicialização (no exemplo do projeto utilizamos um banco de dados em mémoria, que sempre perde os dados após reiniciado o projeto). A partir da versão 2.5 do SB, é necessário adicionar uma propriedade no arquivo **application.properties** `spring.jpa.defer-datasource-initialization=true`.


### NEXT

- API de stream do Java 8
- JPA