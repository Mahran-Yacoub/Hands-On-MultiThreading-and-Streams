package exalt.company.SpringBootrRestAPI.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/** This Class is used to set configuration for the application*/
@Configuration
public class ApplicationConfiguration {


    @Bean
    public Docket swaggerDocumentation() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    /**
     * This method is used to set metadata about project when we use swagger ui
     * to document it
     *
     * @return ApiInfo that has metaData about the app
     *
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Hands On Multithreading and streams by Java")
                .version("1.1").build();
    }
}
