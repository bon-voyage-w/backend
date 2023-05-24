package com.bonvoyage.domain.user.controller;

import com.bonvoyage.domain.review.dto.ReviewDto;
import com.bonvoyage.domain.review.service.ReviewService;
import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.service.JWTService;
import com.bonvoyage.domain.user.service.UserService;
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
    private final ReviewService reviewService;
    @GetMapping("/test")
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
    @GetMapping("")
    public ResponseEntity<?> userDetailByToken(@RequestHeader("Authorization") String accessToken){
        System.out.println(accessToken);
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        System.out.println(userId);
        UserDto userDto= userService.findUserByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
    @PostMapping("")
    public ResponseEntity<?> userRegister(UserDto userDto){
        int userId= userService.registerUser(userDto);
        Map<String,String> token=userService.setTokenInfo(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
    @PutMapping
    public ResponseEntity<?> userModify(@RequestHeader("Authorization") String accessToken,UserDto userDto){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        String loginId=userService.updateUserDetail(userId, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(loginId);
    }
    @DeleteMapping
    public ResponseEntity<?> userDelete(@RequestHeader("Authorization") String accessToken){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        if(!userService.withdrawalUser(userId)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제가 완료되지 않았습니다");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> userLogin(@RequestBody Map<String, String> loginInfo){
        System.out.println("안찍히나?"+loginInfo.get("id"));
        try{
        if(!userService.isAuthAvail(loginInfo)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디와 비밀번호가 일치하지 않습니다");
        }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        int userId=userService.getUserIdByLoginId(loginInfo.get("id"));
        Map<String,String> token=userService.setTokenInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @DeleteMapping("/auth")
    public ResponseEntity<?> userLogout(@RequestHeader("Authorization") String accessToken){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        userService.removeUserRefreshToken(userId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> userDetail(@PathVariable(value = "id") String loginId){
        UserDto userDto= userService.findUserByLoginId(loginId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @GetMapping("/like")
    public ResponseEntity<?> userLikeList(@RequestHeader("Authorization") String accessToken){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);

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
    public ResponseEntity<?> userReviewList(@RequestHeader("Authorization") String accessToken){
        if(jwtService.isUnavailToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        List<ReviewDto> reviewDtoList= reviewService.findReviewListByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewDtoList);
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
