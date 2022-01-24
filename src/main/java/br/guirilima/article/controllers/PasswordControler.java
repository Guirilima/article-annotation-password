package br.guirilima.article.controllers;

import br.guirilima.article.models.PasswordRequestDTO;
import br.guirilima.article.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/valid-password")
public class PasswordControler {

    private final PasswordService service;

    @PostMapping
    public ResponseEntity register(@Valid @RequestBody PasswordRequestDTO requestDTO) {

        service.saveData(requestDTO);

        return ResponseEntity.ok().build();
    }
}
