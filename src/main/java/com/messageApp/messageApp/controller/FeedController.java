
package com.messageApp.messageApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageApp.messageApp.postagem.PostagemResponseDTO;
import com.messageApp.messageApp.service.PostagemService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/feed")
public class FeedController {
    
    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public List<PostagemResponseDTO> getFeed() {
        return postagemService.getAll();
    }
}
