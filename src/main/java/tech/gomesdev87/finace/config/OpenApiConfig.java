package tech.gomesdev87.finace.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Finace API")
                        .version("1.0.0")
                        .description("API de Gestão Financeira e Estoque"))
                // ✅ TROCA o localhost pela URL CORRETA!
                .servers(List.of(
                        new Server()
                                .url("https://cuddly-couscous-q7rv4vpx4jpj296r-8089.app.github.dev")
                                .description("Servidor GitHub Codespaces")
                ));
    }
}