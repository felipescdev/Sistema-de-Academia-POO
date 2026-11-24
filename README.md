# Sistema-de-Academia-POO
Sistema de Academia e Fichas de Treino

Projeto prático da disciplina *Programação Orientada a Objetos*
(Prof. Me. Marcos Roberto de Moraes – *Maromo*) – Fatec Mogi Mirim.
Aplicação desktop em Java + Swing que gerencia alunos de uma academia
100% em memória RAM (sem banco de dados e sem arquivos), aplicando
Interfaces, Polimorfismo, Exceções Customizadas, Java Collections e Generics.

Tema escolhido: nº 09 – Sistema de Academia e Fichas de Treino.

Autores 
Felipe Sousa Costa – RA: 1630482523030 
Guilherme Karan de Almeida – RA: 

Funcionalidades

Botão e o que faz 
Cadastrar: Cria um novo aluno (impede matrícula duplicada) 
Pesquisar: Busca por matrícula e carrega os dados nos campos 
Atualizar Dados: Salva as alterações feitas nos campos no aluno pesquisado 
Reg. Frequência: Soma uma presença (lança exceção se a matrícula estiver bloqueada) 
Bloquear/Ativar:  Alterna a suspensão por pendência 
 Listar Ordenado: Ordena por matrícula (`Collections.sort`) e exibe o relatório
 Sair: Encerra o sistema (também ao fechar a janela) 

O cálculo de **IMC** e sua classificação são exibidos no relatório de cada aluno.
A altura aceita metros (`1.75`) ou centímetros (`175`) e é normalizada automaticamente.
Arquitetura
```
src/
├── controller/
│   └── App.java                          # método main – abre a tela
├── exceptions/
│   └── MatriculaBloqueadaException.java  # Checked Exception customizada
├── interfaces/
│   └── Avaliavel.java                    # contrato + método default
├── model/
│   └── AlunoAcademia.java                # entidade principal
└── view/
    └── TelaAcademia.java                 # GUI Swing
```
Classe e Responsabilidade 
`interfaces.Avaliavel`- Define `calcularIndiceMassaCorporal()` e o método `default` `classificarImc()` 
`exceptions.MatriculaBloqueadaException` - Estende `Exception`; lançada ao registrar frequência de aluno bloqueado 
`model.AlunoAcademia`-  Implementa `Avaliavel` e `Comparable<AlunoAcademia>` (ordem natural por matrícula); campos privados, getters/setters, `equals`, `hashCode` e `toString` 
`view.TelaAcademia`,`JFrame` com `ActionListener` e `WindowListener`- mantém a `List<AlunoAcademia>` em memória e exibe erros com `JOptionPane` 
`controller.App - Instancia a tela e chama `setVisible(true)` 

Como executar
Pré-requisito: JDK 17 ou superior.

Pela IDE (IntelliJ IDEA / Eclipse):
1. Clone o repositório ou abra a pasta do projeto.
2. Execute o método `main` de `src/controller/App.java`.

Pelo terminal:
bash
git clone https://github.com/felipescdev/Sistema-de-Academia-POO.git
cd Sistema-de-Academia-POO
# compilar
javac -d out src/exceptions/*.java src/interfaces/*.java src/model/*.java src/view/*.java src/controller/*.java
# executar
java -cp out controller.App

Registro de uso de IA generativa
 Ferramenta utilizada : Gemini.
1. Estrutura de pacotes e camada de domínio
- Prompt: Utilize os projetos de GUI em Java que trabalhamos no passado para montar um esqueleto para o projeto de sistema de Academia proposto para este novo projeto. 
-Resposta obtida: a IA propôs a estrutura base de `AlunoAcademia`, da interface `Avaliavel` e da exceção `MatriculaBloqueadaException`.
- Refinamento: revisamos o encapsulamento (setters só onde necessário), ajustamos nomes de parâmetros e implementamos a normalização da altura (metros/centímetros) no `setAltura`.
2. Esqueleto da interface gráfica e tratamento de exceções
- Prompt: Gere apenas o esqueleto de uma GUI em Java Swing, assim como já visto anteriormente, para gerenciar uma List<AlunoAcademia>, com ActionListener e WindowListener. Os botões serão cadastrar, pesquisar, frequencia, bloquear, ordenar e sair, já a  listagem será exibida em um JtextArea, Jtable. Deixe a lógica dos botões, validações e eventos de janela vazios para eu implementar.
- Resposta obtida: estrutura base em javax.swing (setLayout(null), posições e tamanhos) e blocos try/catch para NumberFormatException e MatriculaBloqueadaException, no padrão que vimos em sala de aula.
- Refinamento: ajustamos coordenadas, eventos de ActionListener, WindowListener e alteramos algumas mensagens exibidas na tela ao usuário.

 3. Edição de cadastros existentes
- Prompt: Em caso de erro na digitação, precisamos que seja possível alterarmos dados já cadastrados, o que atualmente não temos. Uma maneira seria utilizar o método pesquisar e alterarmos a partir dos dados trazidos. Exemplifique os setters e o novo botão de atualizar.
- Resposta obtida: sugestão de carregar os dados em Pesquisar e gravar as alterações via setters em Atualizar, além do código para orientação.
- Refinamento: comparamos com a versão já haviamos iniciado e unificamos os dois códigos. Criando um novo botão, o de atualizar e configurando as dimensões do botão para ficar de acordo com a tela. Os setters seguiram o padrão já criado de outros métodos dentro do try catch.

Contexto acadêmico
Trabalho prático baseado nos materiais POO_07 a POO_10 da disciplina.


