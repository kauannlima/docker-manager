# Docker Manager

Este projeto é um gerenciador de containers Docker utilizando Java. A aplicação permite iniciar, parar e visualizar containers através de uma interface interativa, facilitando o gerenciamento de serviços.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Docker Java API

## Configuração de docker.socket.path

Para garantir que a aplicação se conecte corretamente ao Docker, é necessário configurar o valor de `docker.socket.path` de acordo com o sistema operacional utilizado:

### Windows:

Defina `docker.socket.path` como:

```properties
docker.socket.path=tcp://localhost:2375
```

Certifique-se de que o Docker Desktop esteja configurado para expor o daemon no TCP:

1. Abra o Docker Desktop.
2. Vá em **Settings** (Configurações).
3. Navegue até **General** ou **Exposed Daemon**.
4. Ative a opção: **Expose daemon on tcp://localhost:2375 without TLS**.

### Linux/macOS:

Defina `docker.socket.path` como:

```properties
docker.socket.path=unix:///var/run/docker.sock
```

Este é o caminho padrão do socket Unix utilizado pelo Docker para comunicação local.

## Como Executar a Aplicação

1. Clone o repositório:

   ```bash
   git clone https://github.com/kauannlima/docker-manager.git
   ```
   
2. Defina o valor de `docker.socket.path` no arquivo de propriedades da aplicação conforme o seu sistema operacional (conforme explicado acima).

3. Inicie a aplicação utilizando o Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação no navegador em `http://localhost:8080`.

## Licença

Este projeto está licenciado sob os termos da licença MIT. Consulte o arquivo `LICENSE` para mais informações.
