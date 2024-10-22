package com.lanchonete.controller;


import com.lanchonete.dto.lanche.LancheExibitionDto;
import com.lanchonete.dto.lanche.LancheMapper;
import com.lanchonete.entity.Lanche;
import com.lanchonete.repository.LancheRepository;
import com.lanchonete.service.LancheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lanches")
@RequiredArgsConstructor
public class LancheController {

    private final LancheService lancheService;
    private final LancheRepository repository;

    @GetMapping
    public ResponseEntity<List<LancheExibitionDto>> showLanches(){
        List<Lanche> lanches = lancheService.showLanches();
        if (lanches.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(LancheMapper.toDto(lanches));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancheExibitionDto> show(@PathVariable Long id){
        Lanche lanche = lancheService.show(id);
        return ResponseEntity.ok(LancheMapper.toDto(lanche));
    }


}
