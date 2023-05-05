package com.bonvoyage.domain.shareboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share-board")
public class ShareBoardController {

    @GetMapping()
    public ResponseEntity<?> shareBoardList(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping()
    public ResponseEntity<?> shareBoardAdd(){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> shareBoardDetail(@PathVariable("id") int shareBoardId){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> shareBoardRemove(@PathVariable("id") int shareBoardId){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping("/{id")
    public ResponseEntity<?> shareBoardModify(@PathVariable("id") int shareBoardId){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
