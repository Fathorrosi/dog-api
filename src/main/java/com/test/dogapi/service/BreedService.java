package com.test.dogapi.service;

import com.test.dogapi.dto.request.BreedUpdateRequest;
import com.test.dogapi.entity.Breed;
import com.test.dogapi.entity.Type;

import com.test.dogapi.exception.DataNotFoundException;
import com.test.dogapi.repository.BreedRepository;
import com.test.dogapi.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BreedService {

    private final BreedRepository breedRepository;
    private final TypeRepository typeRepository;

    public JSONObject storeNasabah() {
        String uri = "https://dog.ceo/api/breeds/list/all";
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);
        Object obj = JSONValue.parse(data);
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject result = (JSONObject) jsonObject.get("message");

        for (Object keyObject : result.keySet())
        {

            var breed = (String)keyObject;
            var type = String.valueOf(result.get(breed));

            var newBreed = new Breed(breed);
            var listType = new ArrayList<Type>();

            var typeArray = type.replace("[", "")
                    .replace("]", "")
                    .replace("\"","").split(",");

            if(!type.equals("[]")){
                for (int i = 0; i < typeArray.length; i++) {
                    var newType = new Type(typeArray[i], newBreed);
                    listType.add(newType);
                }
            }

            breedRepository.save(newBreed);
            typeRepository.saveAll(listType);
        }
        return result;
    }

    public List<Breed> getAll(){
        return breedRepository.findAll();
    }

    public Breed create(String name){
        var exist = breedRepository.existsByName(name);
        if(!exist){
            return breedRepository.save(new Breed(name));
        }
        return null;
    }

    public Breed update(BreedUpdateRequest breedRequest){
        var tempBreed = breedRepository.findById(breedRequest.getId());
        if(tempBreed.isEmpty()) {
            throw new DataNotFoundException("data not found!");
        }
        var breed = tempBreed.get();
        breed.setName(breedRequest.getName());
        return breedRepository.save(breed);
    }

    public Breed detail(long id){
        var breed = breedRepository.findById(id);
        return breed.get();
    }

    public void delete(long id){
        breedRepository.deleteById(id);
    }
}
