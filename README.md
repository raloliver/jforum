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


### ANNOTATIONS

- `@ResponseBody`: Por padrão, o Spring considera que o retorno do método é o nome da página que ele deve carregar, mas ao utilizar a anotação `@ResponseBody`, indicamos que o retorno do método deve ser serializado e devolvido no corpo da resposta. Para evitar a repetição dessa anotação, podemos usar como anotação principal da classe, o `@RestController`.


### NEXT

- API de stream do Java 8