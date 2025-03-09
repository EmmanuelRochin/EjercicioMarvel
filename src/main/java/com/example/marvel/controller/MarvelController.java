package com.example.marvel.controller;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.CharacterResponse;
import com.example.marvel.dto.Response;
import com.example.marvel.service.MarvelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarvelController {

    @Autowired
    private MarvelService marvelService;
    @GetMapping("/characters")
    @ResponseBody
    public Response obtenerPersonaje() {
        Response response = new Response();
        List<CharacterResponse> listResponse = marvelService.getCharacters();
        if(!listResponse.isEmpty()){
            response.setType("SUCCESS");
            response.setAction("CONTINUE");
            response.setData(listResponse);
        }else{
            response.setType("ERROR");
            response.setAction("CANCEL");
        }
        return response;
    }


}
