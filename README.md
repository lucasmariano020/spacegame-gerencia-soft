# Space-Game-setup

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
