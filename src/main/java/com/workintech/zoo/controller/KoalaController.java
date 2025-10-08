package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();

        koalas.put(1, new Koala(1, "Milo", 8.0, 14, "Male"));
        koalas.put(2, new Koala(2, "Luna", 7.5, 12, "Female"));
    }

    @GetMapping
    public List<Koala> getAll() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable int id){
        Koala koala = koalas.get(id);

        if(koala == null){
            throw new RuntimeException("Koala not found with id: " + id);
        }

        return koala;
    }

    @PostMapping
    public Koala add(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable int id, @RequestBody Koala koala){
        if(!koalas.containsKey(id)){
            throw new RuntimeException("Cannot update, no koala found with id: " + id);
        }
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id){
        Koala removed = koalas.remove(id);
        if(removed == null){
            throw new RuntimeException("Cannot delete, no koala fount with id: " + id);
        }
        return removed;
    }

}
