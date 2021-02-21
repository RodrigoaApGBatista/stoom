package com.rodrigobatista.stoom;

import com.rodrigobatista.data.vo.EnderecoVO;
import com.rodrigobatista.services.EnderecoService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EnderecoCrudTest {

    @Autowired
    EnderecoService enderecoService;

    @Test
    public void crudEnderecoTest(){
        //cenário
        EnderecoVO enderecoVO = EnderecoVO.builder()
                .id(1L)
                .streetName("RuaStoom")
                .number("20")
                .complement("Bloco A")
                .neighbourhood("vizinhança teste")
                .city("Campinas")
                .state("São Paulo")
                .country("Brazil")
                .zipcode("00000-00")
                .latitude("-22.805135327026175")
                .longitude("-47.07555192721358")
                .build();

        //insert
        enderecoService.create(enderecoVO);

        //verifica testando a consulta
        final EnderecoVO resultadoPorId = enderecoService.findById(1L);
        Assertions.assertThat(resultadoPorId).isNotNull();

        //update
        enderecoVO.setId(2L);
        EnderecoVO resultadoUpdate = enderecoService.update(enderecoVO);
        Assertions.assertThat(resultadoUpdate.getId()).isEqualTo(enderecoVO.getId());

        //delete
        enderecoService.delete(2L);
        final EnderecoVO resultadoDelete = enderecoService.findById(1L);
        Assertions.assertThat(resultadoPorId).isNull();

    }

}
