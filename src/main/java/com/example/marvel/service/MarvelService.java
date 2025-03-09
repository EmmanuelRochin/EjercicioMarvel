package com.example.marvel.service;


import com.example.marvel.dto.CharacterResponse;
import com.example.marvel.dto.OneCharacterResponse;

import java.util.List;

public interface MarvelService {
    List<CharacterResponse> getCharacters(Integer limit);
    OneCharacterResponse getCharacter(Integer idCharacter);
}
