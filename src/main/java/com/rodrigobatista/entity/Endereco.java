package com.rodrigobatista.entity;

import com.rodrigobatista.vo.EnderecoVO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "streetName", nullable = false)
    private String streetName;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "complement", nullable = true)
    private String complement;

    @Column(name = "neighbourhood", nullable = false)
    private String neighbourhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "latitude", nullable = true)
    private String latitude;

    @Column(name = "longitude", nullable = true)
    private String longitude;

    public static Endereco create(EnderecoVO enderecoVO){
        return new ModelMapper().map(enderecoVO, Endereco.class);
    }

}
