package com.rodrigobatista.stoom.mock;

import com.rodrigobatista.services.EnderecoService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class crudTestConfiguration {

    @Bean
    @Primary
    public EnderecoService enderecoService() {
        return Mockito.mock(EnderecoService.class);
    }

}
