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
    private final String correctType = "SUCCESS";
    private final String correctAction = "CONTINUE";
    private final String errorType = "ERROR";
    private final String errorAction = "CANCEL";

    @GetMapping("/characters")
    public ResponseEntity<Response> getCharacters(@RequestParam Integer limit) {
        try {
            Response response = new Response();
            if(limit>0 && limit<101){
                List<CharacterResponse> listResponse = marvelService.getCharacters(limit);
                if(!listResponse.isEmpty()){
                    response.setType(correctType);
                    response.setAction(correctAction);
                    response.setData(listResponse);
                    return  new ResponseEntity<Response>(response, HttpStatus.OK);
                }else{
                    response.setType(errorType);
                    response.setAction(errorAction);
                    return  new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                response.setType(errorType);
                response.setAction(errorAction);
                response.setCode("INVALID LIMIT");
                response.setMessage("Provide value for limit param is invalid");
                return  new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            Response errorResponse = new Response();
            errorResponse.setData(e.toString());
            errorResponse.setType(errorType);
            errorResponse.setAction(errorAction);
            return  new ResponseEntity<Response>(errorResponse, HttpStatus.BAD_REQUEST);
        }



    }
    @GetMapping("/characters/{id}")
    @ResponseBody
    public ResponseEntity<Response> getCharacter(@PathVariable Integer id) {
        try {
            Response response = new Response();
            OneCharacterResponse oneCharacterResponse = marvelService.getCharacter(id);
            response.setType(correctType);
            response.setAction(correctAction);
            response.setData(oneCharacterResponse);
            return  new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response errorResponse = new Response();
            errorResponse.setData(e.toString());
            errorResponse.setType(errorType);
            errorResponse.setAction(errorAction);
            return  new ResponseEntity<Response>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }


}
