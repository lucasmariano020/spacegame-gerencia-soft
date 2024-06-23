# Space Game ![Planeta1](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/eaae488a-c5fa-4b93-8a1d-bb41349b08be)

[Download Windows](passos-para-windows)

## Grupo 05

- Eduardo Ribeiro Rodrigues - 13696679
- Guilherme Henrique Bueno - 14677335
- Isabela Oliveira Costa - 10747972

## Requisitos

### Funcionalidades Implementadas:

**Configuração do Jogo:**

- A biblioteca escolhida para criar o jogo foi a [libGDX](https://libgdx.com/).

- O jogo possui uma interface gráfica do usuário, que inclui opções para iniciar um novo jogo, ver os scores salvos e sair.

- Foi criado uma máquina de estados para o gerenciamento do jogo, como por exemplo: MenuState, PlayingState, PausedState e etc.

**Mecânicas do Jogo:**

- O jogador controla uma nave espacial usando o teclado, podendo girar a nave, atirar e alterar a música que está tocando.

- Aliens surgem em volta da nave e tentam se aproximar para destruí-la. Se um alien chegar até a nave, o jogador perde.

- O jogo é baseado em Waves de Aliens, sendo que cada Wave possui uma quantidade N aleatória e progressiva de Aliens. 

- A velocidade dos Aliens é aumentada a cada 2 Waves. Além disso, os Aliens possuem aceleração em seus movimentos, que é uma pequena fração de suas velocidades. Sendo assim, a aceleração também aumentará na medida que a velocidade aumenta.

- Os Aliens possuem 3 tipos de movimentação: movimentação linear, movimentação em ondas e movimentação em espiral.

- O jogador ganha 14 munições a cada 7 Aliens eliminados e 7 munições a cada nova Wave.

**Funcionalidades Adicionais:**

- No menu principal, é possível ver uma lista dos melhores jogadores e suas pontuações. As pontuações são salvas em um arquivo CSV, mantendo sempre apenas as 10 melhores pontuações.

## Descrição do Projeto

O projeto (Engine do jogo) é organizado da seguinte forma:

### Classes Principais

- **SpaceGame.java:** Classe principal que inicializa o jogo e na qual fica o loop principal.

- **Game.java:** Inicializa todos os managers e controla a execução principal do jogo. É inicializada pela classe principal no método `create`.

### Pacote config

- **ConfigUtils.java:** Utilitários para configuração de escalas (não é tanto utilizado mais, foi criado no inicio onde não se tinha muito conhecimento sobre a Libgdx).

- **LevelConfig.java:** Configurações específicas dos níveis do jogo, como parâmetros de dificuldade e estado da última Wave (munições da Spaceship e Score).

### Pacote entities

- **Alien.java:** Define o comportamento e características dos aliens, como movimentação e manuseio da textura.

- **Bullet.java:** Gerencia os projéteis disparados pela nave, incluindo movimentação e manuseio da textura.

- **Spaceship.java:** Representa a nave controlada pelo jogador, incluindo controle de movimento e informações úteis como o contador de Score e munições.

### Pacote graphics

- **Background.java:** Gerencia o fundo do jogo, incluindo a renderização e animação de piscar das estrelas.

- **TextureManager.java:** Carrega e gerencia as texturas utilizadas no jogo.

- **TexturePaths.java:** Define os caminhos para os arquivos de textura utilizados pelo TextureManager.

### Pacote levels

- **DynamicLevel.java:** Implementa níveis dinâmicos com geração procedural de conteúdo.

- **Level.java:** Representa um nível do jogo, incluindo a lógica de progressão e condições de vitória/derrota.

- **LevelFactory.java:** Fábrica para criação de instâncias de níveis, facilitando a gestão e inicialização dos mesmos.

### Pacote managers

- **AlienManager.java:** Gerencia a criação, atualização e remoção dos aliens.

- **BulletManager.java:** Gerencia a criação, atualização e remoção dos projéteis.

- **CollisionManager.java:** Lida com a detecção e resolução de colisões entre entidades do jogo.

- **GameStateManager.java:** Gerencia os diferentes estados do jogo, como menu, jogo em andamento, pausa e game over.

- **InputManager.java:** Captura e processa a entrada do jogador durante o jogo.

- **MapManager.java:** Gerencia o mapa do jogo, excluindo da memória o anterior e construindo um novo.

- **ScoreManager.java:** Gerencia as pontuações dos jogadores, incluindo salvamento e carregamento das mesmas do arquivo CSV.

- **SoundManager.java:** Gerencia os efeitos sonoros e músicas do jogo.

- **UIManager.java:** Gerencia a interface do usuário, incluindo menus e HUD.

### Pacote states

- **GameOverState.java:** Define o estado de game over, incluindo a lógica para exibir a tela de fim de jogo.

- **GameStateInterface.java:** Interface para os diferentes estados do jogo, definindo métodos comuns como enter, update, getState e exit.

- **MenuState.java:** Define o estado do menu principal, incluindo a lógica para navegar entre opções.

- **PausedState.java:** Define o estado de pausa, permitindo que o jogo seja temporariamente interrompido.

- **PlayingState.java:** Define o estado de jogo em andamento, incluindo a lógica principal da gameplay.

- **ScoresState.java:** Define o estado para exibir as pontuações dos jogadores.

## Comentários Sobre o Código

O jogo começa pela classe principal `SpaceGame.java`, que é responsável por inicializar o jogo e configurar o loop principal. Dentro deste loop, a classe `Game.java` é instanciada e inicializa todos os managers, que são responsáveis por diversas funcionalidades, como gerenciamento de entidades (`AlienManager`, `BulletManager`), controle de estado do jogo (`GameStateManager`), e captura de entrada do jogador (`InputManager`). A arquitetura do jogo é fortemente baseada no uso de estados, onde cada estado (por exemplo, MenuState, PlayingState, PausedState) define um comportamento específico para a fase atual do jogo.

As classes dentro do pacote entities definem os principais componentes do jogo, como a nave (`Spaceship.java`), os projéteis (`Bullet.java`) e os aliens (`Alien.java`). Cada uma dessas classes lida com sua própria lógica de movimentação, renderização e interação. A lógica dos níveis é gerenciada pelas classes dentro do pacote levels, com destaque para `DynamicLevel.java`, que implementa a geração procedural de conteúdo, criando uma experiência única a cada partida. A progressão dos níveis e a lógica de dificuldade são gerenciadas pela `LevelFactory.java`.

A interface gráfica e os recursos visuais são gerenciados pelas classes no pacote graphics, onde `Background.java` lida com a renderização do fundo do jogo, e `TextureManager.java` gerencia as texturas utilizadas. Além disso, o `SoundManager.java` cuida dos efeitos sonoros e músicas. Os estados do jogo são definidos no pacote states, com classes específicas para cada fase, como `GameOverState.java` para o fim do jogo e `MenuState.java` para o menu principal. Cada um desses estados implementa uma interface comum (`GameStateInterface.java`), garantindo que todos sigam uma estrutura consistente.

## Plano de Teste

O plano de testes para este jogo se baseia principalmente em testes manuais e no tratamento de exceções do Java. Cada funcionalidade principal, como a movimentação da nave, disparo de projéteis, comportamento dos aliens, transições de estados do jogo (menu, jogo em andamento, pausa, game over) e a geração procedural de níveis, foi testada manualmente durante o desenvolvimento. Além disso, o gerenciamento de pontuações e a persistência dos dados no arquivo CSV também foram verificados manualmente para assegurar que as pontuações são salvas e carregadas corretamente.

Não foi utilizado um framework de testes automático como JUnit ou Spock, mas o tratamento de exceções em Java foi implementado para capturar e lidar adequadamente com possíveis erros durante a execução do jogo. Isso inclui a verificação de entradas inválidas, erros na carga de recursos e falhas na lógica do jogo.

## Resultado dos Testes

Os resultados dos testes manuais realizados indicam que todas as funcionalidades principais do jogo estão operando conforme o esperado. A movimentação da nave, o disparo de projéteis e o comportamento dos aliens funcionam sem problemas. As transições de estados entre menu, jogo em andamento, pausa e game over são suaves e sem interrupções.

## Build Procedures

### Pré-requisitos

Certifique-se de ter os seguintes softwares instalados:

[JDK](https://www.oracle.com/java/technologies/downloads/?er=221886) (de preferência a versão 17)

[Git](https://git-scm.com/)

[VSCode](https://code.visualstudio.com/)

---

### Executando o Jogo a partir do Arquivo ZIP

Além dos métodos que serão mencionados posteriormente, você pode executar o jogo diretamente a partir de um arquivo ZIP que contém um JAR empacotado e um executável. Siga os passos abaixo:

### Passos para Windows

1. Baixe o arquivo ZIP:

    - Vá para a página de [releases do repositório GitHub](https://github.com/EduardoWS/Space-Game-Java/releases).

    - Baixe o arquivo ZIP mais recente.

2. Extraia o arquivo ZIP:

    - Navegue até a pasta onde o ZIP foi baixado.

    - Clique com o botão direito no arquivo ZIP e selecione Extrair tudo...

    - Escolha um local para extrair os arquivos.

3. Execute o jogo:

    - Navegue até a pasta onde os arquivos foram extraídos.

    - Clique duas vezes no arquivo executável (.exe).

### Passos para Linux

1. Baixe o arquivo ZIP:

    - Vá para a página de [releases do repositório GitHub](https://github.com/EduardoWS/Space-Game-Java/releases).

    - Baixe o arquivo ZIP mais recente.

2. Extraia o arquivo ZIP:

    - Navegue até a pasta onde o ZIP foi baixado.

    - Escolha um local para extrair os arquivos.

3. Execute o jogo:

    - Navegue até a pasta onde os arquivos foram extraídos.

    - Abra o terminal na pasta e execute `./Space-Game-setup` para rodar o binário.

    - OBS: caso dê permissão negada, execute o seguinte comando `chmod +x Space-Game-setup` para poder dar a permissão de execução ao arquivo binário.

---

### Buildando o projeto no Windows utilizando o VSCODE

1. Instale o JDK 17:

    - Baixe o JDK do site oficial da Oracle (ou de qualquer outro distribuidor e siga as instruções de instalação.

    - Após a instalação, configure a variável de ambiente JAVA_HOME para apontar para o diretório de instalação do JDK. E configure o Path para apontar ou para o JAVA_HOME ou para a pasta bin dentro da pasta JDK.

2. Instale o Git caso ainda não tenha:

    - Baixe o Git do site oficial e siga as instruções de instalação.

4. Clone o repositório do GitHub:

    ```bash
    git clone git@github.com:EduardoWS/Space-Game-Java.git
    cd Space-Game-Java
    ```

5. Build do projeto:

    - Instale o plugin do Gradle no VSCODE.

    - Vai aparecer o ícone do Gradle ao lado:

    ![image](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/f40d9eb7-c520-4a63-b41e-f3c6e09e97ff)

    - Vá em lwjgl3 > tasks > build e execute o `build`:

    ![image](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/593f4b2d-5656-4203-a273-a60c259f79a2)


6. Executar o jogo:

   - Para executar o projeto, basta ir em lwjgl3 > application e executar o `run`:

   ![image](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/3ebaeeaf-1e92-40e7-a4fa-d7b447eef33d)

### Buildando o projeto no Linux utilizando o VSCODE

1. Instale o JDK:

    - No terminal, execute:

    ```bash
    sudo apt update
    sudo apt install openjdk-17-jdk
    ```

    - Verifique a instalação com:

    ```bash
    java -version
    ```

2. Instale o Git:

    - No terminal, execute:

    ```bash
    sudo apt install git
    ```

3. Clone o repositório do GitHub:

    - No terminal, execute:

    ```bash
    git clone git@github.com:EduardoWS/Space-Game-Java.git
    cd Space-Game-Java
    ```

4. Build do projeto:

    - Instale o plugin do Gradle no VSCODE.

    - Vai aparecer o ícone do Gradle ao lado:

    ![image](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/f40d9eb7-c520-4a63-b41e-f3c6e09e97ff)

    - Vá em lwjgl3 > tasks > build e execute o `build`:

    ![image](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/593f4b2d-5656-4203-a273-a60c259f79a2)


5. Executar o jogo:

   - Para executar o projeto, basta ir em lwjgl3 > application e executar o `run`:

   ![image](https://github.com/EduardoWS/Space-Game-Java/assets/81593054/3ebaeeaf-1e92-40e7-a4fa-d7b447eef33d)

---

## Problems

A maior dificuldade enfrentada durante o desenvolvimento do jogo foi na parte de escalas das entidades, como os aliens, a spaceship, as bullets e as fonts. Foram muitas semanas ajustando o jogo para manter a mesma proporção independentemente da resolução da tela do usuário. Foi um processo desafiador garantir que todos os elementos do jogo fossem dimensionados corretamente e exibidos de forma consistente em diferentes dispositivos.

Além disso, estruturar corretamente a arquitetura do jogo foi um grande desafio. Organizar os diversos componentes e assegurar que cada um desempenhasse seu papel específico de forma eficiente exigiu muito planejamento e revisão de código.

Outro desafio significativo foi desenvolver um nível dinâmico que aumentasse a dificuldade de forma progressiva. Implementar a geração procedural de conteúdo e ajustar os parâmetros de dificuldade para garantir uma experiência de jogo equilibrada e envolvente foi uma tarefa complicada. No entanto, esses desafios contribuíram para o aprendizado e a melhoria contínua do projeto.

## Outras Informações Sobre o Projeto

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

### Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3.

### Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/lib`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
