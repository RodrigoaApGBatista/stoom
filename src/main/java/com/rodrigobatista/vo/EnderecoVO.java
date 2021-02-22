package com.rodrigobatista.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rodrigobatista.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoVO extends RepresentationModel<EnderecoVO> implements Serializable {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("streetName")
    private String streetName;

    @JsonProperty("number")
    private String number;

    @JsonProperty("complement")
    private String complement;

    @JsonProperty("neighbourhood")
    private String neighbourhood;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    public static EnderecoVO convert(Endereco endereco){
        return new ModelMapper().map(endereco, EnderecoVO.class);
    }
}
