package com.bonvoyage.domain.user.controller;

import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    @GetMapping("")
    public ResponseEntity<?> userList(){
        try {
            List<UserDto> list = userService.findUserList(null);
            if(list != null && !list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("")
    public ResponseEntity<?> userRegister(){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping
    public ResponseEntity<?> userModify(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping
    public ResponseEntity<?> userDelete(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userDetail(@PathVariable(value = "id") int userId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> userLogin(){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/auth")
    public ResponseEntity<?> userLogout(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/like")
    public ResponseEntity<?> userLikeList(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/like/{id}")
    public ResponseEntity<?> userLikeRegister(@PathVariable(value = "id") int likeId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping("/like/{id}")
    public ResponseEntity<?> userLikeDelete(@PathVariable(value = "id") int likeId){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/reviews")
    public ResponseEntity<?> userReviewList(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/share-boards")
    public ResponseEntity<?> userShareBoardList(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
