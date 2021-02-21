package com.rodrigobatista.controller;

import com.rodrigobatista.data.vo.EnderecoVO;
import com.rodrigobatista.services.EnderecoService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final PagedResourcesAssembler<EnderecoVO> assembler; //ajudar na boa prática do HATEOAS

    @Autowired
    public EnderecoController(EnderecoService enderecoService, PagedResourcesAssembler<EnderecoVO> assembler) {
        this.enderecoService = enderecoService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public EnderecoVO findById(@PathVariable("id") Long id){
        EnderecoVO enderecoVO = enderecoService.findById(id);
        enderecoVO.add(linkTo(methodOn(EnderecoController.class)
                .findById(id)).withSelfRel());
        return enderecoVO;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "country"));
        Page<EnderecoVO> enderecos = enderecoService.findAll(pageable);
        enderecos.stream()
                .forEach(e -> e.add(linkTo(methodOn(EnderecoController.class).findById(e.getId())).withSelfRel()));

        PagedModel<EntityModel<EnderecoVO>> pagedModel = assembler.toModel(enderecos);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public EnderecoVO create(@RequestBody EnderecoVO enderecoVO){
        EnderecoVO endereco = enderecoService.create(enderecoVO);
        return endereco.add(linkTo(methodOn(EnderecoController.class)
                .findById(enderecoVO.getId())).withSelfRel());
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public EnderecoVO update(@RequestBody EnderecoVO enderecoVO){
        EnderecoVO ëndereco = enderecoService.create(enderecoVO);
        return ëndereco.add(linkTo(methodOn(EnderecoController.class)
                .findById(enderecoVO.getId())).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable("id") Long id){
        enderecoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
