# Transformacao-de-dados

Este projeto extrai dados de procedimentos de saúde de um arquivo PDF, os organiza em uma estrutura tabular e os exporta para um arquivo CSV, que é posteriormente compactado em um arquivo ZIP. O projeto foi implementado em Java, utilizando uma arquitetura modular e boas práticas de desenvolvimento de software.

# Metodologia de Desenvolvimento
O desenvolvimento deste projeto foi organizado utilizando o Kanban, uma metodologia ágil. O Kanban ajudou a organizar as tarefas, gerenciar o fluxo de trabalho e garantir que as atividades fossem realizadas de forma eficiente e com foco na entrega contínua. O uso dessa metodologia permitiu a priorização das tarefas e acompanhamento do progresso de cada etapa do desenvolvimento.

## Estrutura do Projeto

```
Transformacao-de-dados/
│
├── src/
│   ├── models/
│   │   └── Procedure.java          # Modelo de dados para os procedimentos
│   ├── extractors/
│   │   └── TextExtractor.java      # Lógica de extração de texto do PDF
│   ├── exporters/
│   │   └── DataExporter.java       # Exportação para CSV e criação de ZIP
│   ├── utils/
│   │   └── FileUtils.java          # Utilitários para manipulação de arquivos
│   └── Main.java                   # Ponto de entrada do programa
├── input/                          # Diretório para o PDF de entrada
├── output/                         # Diretório para os arquivos gerados (CSV e ZIP)
├── lib/                            # (Opcional) Bibliotecas externas
├── pom.xml                         # Configuração do Maven
├── .gitignore                      # Arquivos e diretórios ignorados pelo Git
└── README.md                       # Documentação do projeto

```

## Funcionalidades

- Lê um arquivo PDF contendo uma lista de procedimentos de saúde.
- Extrai informações estruturadas usando expressões regulares.
- Exporta os dados extraídos para um arquivo CSV.
- Compacta o CSV em um arquivo ZIP.
- Organiza os dados em uma estrutura modular com classes específicas para cada responsabilidade.

## Dependências

O projeto utiliza as seguintes bibliotecas externas, gerenciadas pelo Maven:

- **iText 7**: Para leitura e extração de texto de arquivos PDF.
- **OpenCSV**: Para criação de arquivos CSV.

As dependências estão listadas no arquivo `pom.xml`.

## Pré-requisitos

- **Java 11+**: Necessário para compilar e executar o projeto.
- **Maven**: Para gerenciamento de dependências e execução.

## Configuração

1. **Clone o Repositório**
    
    ```bash
    git clone git@github.com:micherenio-nascimento/Transformacao-de-dados.git
    cd Transformacao-de-dados
    ```
    
2. **Instale as Dependências**:
No diretório raiz, execute:
    
    ```bash
    mvn clean install
    ```

## Execução

1. **Compile e Execute**:
No diretório raiz, execute:
    
    ```bash
    mvn exec:java -Dexec.mainClass="src.Main"
    ```
    
2. **Saída**:
    - Um arquivo CSV (`procedures.csv`) será gerado no diretório `output/`.
    - Um arquivo ZIP (`Teste_SeuNome.zip`) contendo o CSV será gerado no mesmo diretório.
    
    Exemplo de saída no console:
    
    ```
    CSV gerado em: ../output/procedures.csv
    ZIP gerado em: ../output/Teste_SeuNome.zip
    
    ```
    

## Personalização

- **Nome do ZIP**: Em `Main.java`, altere a variável `zipName` para seu nome:
    
    ```java
    String zipName = "Teste_SeuNome"; // Substitua "SeuNome" pelo seu nome
    
    ```
    
- **Nome do PDF**: Ajuste o nome do arquivo PDF em `Main.java` se necessário:
    
    ```java
    Path inputFile = inputDir.resolve("anexo_I.pdf");
    
    ```
    

## Arquitetura

- **Modularização**: O código está dividido em pacotes com responsabilidades claras:
    - `models`: Estrutura de dados.
    - `extractors`: Extração de dados do texto.
    - `exporters`: Exportação e compactação.
    - `utils`: Funções utilitárias.
- **Boas Práticas**: Uso de caminhos relativos, tratamento de exceções e dependências gerenciadas por Maven.

## Limitações

- A extração de dados depende da formatação do PDF. Se o arquivo tiver uma estrutura muito complexa ou inconsistente, ajustes no regex em `TextExtractor.java` podem ser necessários.
- Não há suporte nativo a OCR; o texto deve ser extraível diretamente do PDF.