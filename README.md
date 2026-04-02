Refatoração 

S — Single Responsibility Principle (Princípio da Responsabilidade Única)
Regra: Uma classe deve ter um, e apenas um, motivo para mudar.

A divisão foi feita em pacotes onde cada classe possui uma responsabilidade clara e única:

- Model: Representação dos dados e entidades.
- Repository: Persistência e acesso a dados.
- Service: Regras de negócio e lógica da aplicação.
- Controller: Fluxo de entrada e saída (comunicação com o usuário/API).
- App: Ponto de entrada e configuração do sistema.


O - Open/Closed Principle (Princípio do Aberto/Fechado) 
Regra: O software deve estar aberto para expansão, mas fechado para modificação.

Aplicado por meio de interfaces no Repository. As classes que implementam a interface podem ser injetadas na camada de Service. 


L - Liskov Substitution Principle (Princípio da Substituição de Liskov)
Regra: As classes derivadas (filhas) devem poder substituir suas classes bases (pais) sem quebrar o sistema.

Aplicado por meio de interfaces no Repository. As classes que implementam a interface podem ser injetadas na camada de Service. 


I - Interface Segregation Principle (Princípio da Segregação de Interfaces)
Regra: Uma classe não deve ser forçada a depender de métodos que não utiliza. (Interfaces grandes devem ser divididas).

Este princípio foi aplicado ao dividir interfaces genéricas em interfaces menores e específicas, de acordo com os modelos (Models). Assim, as classes implementam apenas os métodos que realmente precisam utilizar.


D - Dependency Inversion Principle 
Regra: Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações (interfaces). Detalhes devem depender de abstrações.

Aplicado na camada de Service: o módulo de alto nível não tem contato direto com a implementação do módulo de baixo nível (Repository), dependendo agora apenas de interfaces. Além disso, a classe App utiliza a Injeção de Dependência por meio do construtor para fornecer as instâncias necessárias ao Service.

