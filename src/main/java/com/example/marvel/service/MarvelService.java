package com.example.marvel.service;


import com.example.marvel.dto.CharacterResponse;

import java.util.List;

public interface MarvelService {
    List<CharacterResponse> getCharacters();
    CharacterResponse getCharacter(Integer idCharacter);
}
