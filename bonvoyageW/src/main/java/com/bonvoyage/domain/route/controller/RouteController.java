package com.bonvoyage.domain.route.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @GetMapping()
    public ResponseEntity<?> routeList(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping
    public ResponseEntity<?> routeAdd(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> routeDelete(@PathVariable("id") int routeId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> routeModify(@PathVariable("id") int routeId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> routeDetail(@PathVariable("id") int routeId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
