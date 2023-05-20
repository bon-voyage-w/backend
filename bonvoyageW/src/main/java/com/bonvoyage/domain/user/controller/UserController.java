package com.bonvoyage.domain.user.controller;

import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.service.JWTService;
import com.bonvoyage.domain.user.service.UserService;
import com.bonvoyage.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    @GetMapping("")
    public ResponseEntity<?> userList(){
        try {
            List<UserDto> list = userService.findUserList();
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
    public ResponseEntity<?> userRegister(UserDto userDto){
        int userId= userService.registerUser(userDto);
        Map<String,String> token=userService.setTokenInfo(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
    @PutMapping
    public ResponseEntity<?> userModify(@RequestHeader("Authorization") String accessToken,UserDto userDto){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        String loginId=userService.updateUserDetail(userId, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping
    public ResponseEntity<?> userDelete(@RequestHeader("Authorization") String accessToken){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        if(!userService.deleteUser(userId)){
            return ResponseEntity.status(Htt)
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userDetail(@PathVariable(value = "id") String loginId){
        UserDto userDto= userService.findUserByLoginId(loginId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
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
