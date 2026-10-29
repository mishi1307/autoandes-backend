package pe.edu.upeu.autoandes.config;
import io.swagger.v3.oas.models.*; import io.swagger.v3.oas.models.info.*; import io.swagger.v3.oas.models.security.*; import org.springframework.context.annotation.*;
@Configuration public class OpenApiConfig { @Bean OpenAPI autoAndesApi(){return new OpenAPI().info(new Info().title("AutoAndes API").version("v1").description("API de gestión de servicios y órdenes del taller AutoAndes")).components(new Components().addSecuritySchemes("bearerAuth",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));} }
