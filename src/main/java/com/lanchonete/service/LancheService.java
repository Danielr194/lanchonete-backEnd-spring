package com.lanchonete.service;

import com.lanchonete.entity.Lanche;
import com.lanchonete.exception.NaoEncontradoException;
import com.lanchonete.repository.LancheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LancheService {

    private final LancheRepository repository;

    public List<Lanche> showLanches(){
        return repository.findAll();
    }

    public Lanche show(Long id){
        Lanche optLanche = repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Lanche"));
        return optLanche;
    }

}
