package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
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
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
        kangaroos.put(1, new Kangaroo(1, "Jack", 1.8, 85.0, "Male", true));
        kangaroos.put(2, new Kangaroo(2, "Lilly", 1.5, 60.0, "Female", false));
    }

    @GetMapping
    public List<Kangaroo> getAll() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable int id) {
        Kangaroo k = kangaroos.get(id);
        if (k == null) {
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return k;
    }

    @PostMapping
    public ResponseEntity<Kangaroo> add(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() <= 0 || kangaroo.getName() == null || kangaroo.getName().isBlank()) {
            throw new ZooException("Invalid kangaroo payload", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Cannot update. No Kangaroo with id: " + id, HttpStatus.NOT_FOUND);
        }
        // İstersen body.id'yi path id ile hizala:
        // kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id) {
        Kangaroo removed = kangaroos.remove(id);
        if (removed == null) {
            throw new ZooException("Cannot delete. No Kangaroo with id: " + id, HttpStatus.NOT_FOUND);
        }
        return removed;
    }
}