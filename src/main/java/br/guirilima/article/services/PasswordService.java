package br.guirilima.article.services;

import br.guirilima.article.models.PasswordRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public void saveData(PasswordRequestDTO requestDTO) {

        System.out.println("Valido !");
    }
}
