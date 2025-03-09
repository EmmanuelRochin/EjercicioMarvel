package com.example.marvel.controller;

import com.example.marvel.dto.CharacterResponse;
import com.example.marvel.dto.OneCharacterResponse;
import com.example.marvel.dto.Response;
import com.example.marvel.service.MarvelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarvelController {

    @Autowired
    private MarvelService marvelService;

    @GetMapping("/characters")
    public ResponseEntity<Response> obtenerPersonajes() {
        Response response = new Response();
        List<CharacterResponse> listResponse = marvelService.getCharacters();
        if(!listResponse.isEmpty()){
            response.setType("SUCCESS");
            response.setAction("CONTINUE");
            response.setData(listResponse);
            return  new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            response.setType("ERROR");
            response.setAction("CANCEL");
            return  new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/characters/{id}")
    @ResponseBody
    public ResponseEntity<Response> obtenerPersonaje(@PathVariable Integer id) {
        try {
            Response response = new Response();
            OneCharacterResponse oneCharacterResponse = marvelService.getCharacter(id);
            if(oneCharacterResponse!=null){
                response.setType("SUCCESS");
                response.setAction("CONTINUE");
                response.setData(oneCharacterResponse);
                return  new ResponseEntity<Response>(response, HttpStatus.OK);
            }else{
                response.setType("ERROR");
                response.setAction("CANCEL");
                return  new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (RuntimeException e) {
            Response errorResponse = new Response();
            errorResponse.setData(e.toString());
            return  new ResponseEntity<Response>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }


}
