package com.example.marvel.service.impl;

import com.example.marvel.config.MarvelConfig;
import com.example.marvel.dto.*;
import com.example.marvel.dto.Character;
import com.example.marvel.service.MarvelService;
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
    public List<CharacterResponse> getCharacters(Integer limit) {
        try {
            List<CharacterResponse> characters = new ArrayList<>();
            String url=UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host(marvelConfig.getUrl())
                    .path("/v1/public/characters")
                    .queryParam("ts", marvelConfig.getTs())
                    .queryParam("apikey", marvelConfig.getApikey())
                    .queryParam("hash", marvelConfig.getHash())
                    .queryParam("limit", limit)
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OneCharacterResponse getCharacter(Integer idCharacter) {
        try {
            OneCharacterResponse oneCharacterResponse = new OneCharacterResponse();
            String path = "/v1/public/characters/"+ idCharacter;
            String url=UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host(marvelConfig.getUrl())
                    .path(path)
                    .queryParam("ts", marvelConfig.getTs())
                    .queryParam("apikey", marvelConfig.getApikey())
                    .queryParam("hash", marvelConfig.getHash())
                    .toUriString();
            ResponseApiMarvel response = restTemplate.getForObject(url, ResponseApiMarvel.class);
            assert response != null;
            for(Character character: response.getData().getResults()){
                oneCharacterResponse.setId(character.getId());
                oneCharacterResponse.setName(character.getName());
                oneCharacterResponse.setDescription(character.getDescription());
                oneCharacterResponse.setImage(character.getThumbnail().getPath()+"."+character.getThumbnail().getExtension());
                List<Comics> comics = new ArrayList<>();
                for(Item item :character.getComics().getItems()){
                    comics.add(new Comics(item.getName()));
                }
                oneCharacterResponse.setComics(comics);
            }
            return  oneCharacterResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
