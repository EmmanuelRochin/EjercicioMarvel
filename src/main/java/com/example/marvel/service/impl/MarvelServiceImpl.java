package com.example.marvel.service.impl;

import com.example.marvel.config.MarvelConfig;
import com.example.marvel.dto.Character;
import com.example.marvel.dto.CharacterResponse;
import com.example.marvel.dto.ResponseApiMarvel;
import com.example.marvel.service.MarvelService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
@Service
public class MarvelServiceImpl implements MarvelService {
    @Autowired
    private MarvelConfig marvelConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CharacterResponse> getCharacters() {
        List<CharacterResponse> characters = new ArrayList<>();
        String url=UriComponentsBuilder.newInstance() // Usa newInstance() en lugar de fromHttpUrl()
                .scheme("http")
                .host(marvelConfig.getUrl())
                .path("/v1/public/characters")
                .queryParam("ts", marvelConfig.getTs())
                .queryParam("apikey", marvelConfig.getApikey())
                .queryParam("hash", marvelConfig.getHash())
                .toUriString();

        ResponseApiMarvel response = restTemplate.getForObject(url, ResponseApiMarvel.class);
        assert response != null;
        for(Character character: response.getData().getResults()){
            characters.add(new CharacterResponse(
                    character.getId(),
                    character.getName(),
                    character.getDescription(),
                    character.getThumbnail().getPath()+"."+character.getThumbnail().getExtension()));
        }
        return characters;
    }

    @Override
    public CharacterResponse getCharacter(Integer idCharacter) {
        return null;
    }
}
