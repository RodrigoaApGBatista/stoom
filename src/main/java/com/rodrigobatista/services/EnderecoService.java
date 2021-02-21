package com.rodrigobatista.services;

import com.rodrigobatista.data.vo.EnderecoVO;
import com.rodrigobatista.entity.Endereco;
import com.rodrigobatista.exception.ResourceNotFoundException;
import com.rodrigobatista.repository.EnderecoRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoVO create(EnderecoVO enderecoVO) {
        return EnderecoVO.convert(enderecoRepository.save(Endereco.create(enderecoVO)));
    }

    public Page<EnderecoVO> findAll(Pageable pageable){
        var page = enderecoRepository.findAll(pageable);
        return page.map(this::convertToEnderecoVo);
    }

    private EnderecoVO convertToEnderecoVo(Endereco endereco) {
        return EnderecoVO.convert(endereco);
    }

    public EnderecoVO findById(Long id){
        var entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrato"));
        return EnderecoVO.convert(entity);
    }

    public EnderecoVO update(EnderecoVO enderecoVO){
        final Optional<Endereco> endereco = enderecoRepository.findById(enderecoVO.getId());
        if(!endereco.isPresent()){
            new ResourceNotFoundException("Nenhum registro alterado com esse ID");
        }
        return  EnderecoVO.convert(enderecoRepository.save(Endereco.create(enderecoVO)));
    }

    public void delete(Long id){
        var entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrato com esse ID"));
        enderecoRepository.delete(entity);
    }

}
