Refatoração 1

S - Single Responsibility Principle (SRP)
Feita a divisão em packages:
- Model
- Repository
- Service
- Controller

D - Dependency Inversion Principle (DIP)
Ao criar a interface OlimpiadaRepository, a camada Service vai depenser desse contrato e não diretamente da ArrayList.

