package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void init() {
        koalas = new HashMap<>();
        koalas.put(1, new Koala(1, "Milo", 8.0, 14, "Male"));
        koalas.put(2, new Koala(2, "Luna", 7.5, 12, "Female"));
    }

    @GetMapping
    public List<Koala> getAll() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable int id) {
        Koala k = koalas.get(id);
        if (k == null) {
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return k;
    }

    @PostMapping
    public ResponseEntity<Koala> add(@RequestBody Koala koala) {
        if (koala.getId() <= 0 || koala.getName() == null || koala.getName().isBlank()) {
            throw new ZooException("Invalid koala payload", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return ResponseEntity.ok(koala);
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable int id, @RequestBody Koala koala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Cannot update. No Koala with id: " + id, HttpStatus.NOT_FOUND);
        }
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id) {
        Koala removed = koalas.remove(id);
        if (removed == null) {
            throw new ZooException("Cannot delete. No Koala with id: " + id, HttpStatus.NOT_FOUND);
        }
        return removed;
    }
}
