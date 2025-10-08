package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();

        kangaroos.put(1, new Kangaroo(1, "Jack", 1.8, 85.0, "Male", true));
        kangaroos.put(2, new Kangaroo(2, "Lilly", 1.5, 60.0, "Female", false));
    }

    @GetMapping
    public List<Kangaroo> getAll(){
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable int id){
        Kangaroo kangaroo = kangaroos.get(id);
        if(kangaroo == null){
            throw new RuntimeException("Kangaroo not found with id: " + id);
        }
        return kangaroo;
    }

    @PostMapping
    public Kangaroo add(@RequestBody Kangaroo kangaroo){
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        if(!kangaroos.containsKey(id)){
            throw new RuntimeException("Cannot update, no Kangaroo with id: " + id);
        }
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id){
        Kangaroo removed = kangaroos.remove(id);
        if(removed == null){
            throw new RuntimeException("Cannot delete, no Kangaroo with id: " + id);
        }
        return removed;
    }

}
