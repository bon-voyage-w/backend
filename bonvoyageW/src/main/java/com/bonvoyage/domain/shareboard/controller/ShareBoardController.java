package com.bonvoyage.domain.shareboard.controller;

import com.bonvoyage.domain.shareboard.dto.ShareBoardCommentDto;
import com.bonvoyage.domain.shareboard.dto.ShareBoardDto;
import com.bonvoyage.domain.shareboard.entity.ShareBoardEntity;
import com.bonvoyage.domain.shareboard.service.ShareBoardService;
import com.bonvoyage.domain.user.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/share-board")
public class ShareBoardController {

    private final JWTService jwtService;
    private final ShareBoardService shareBoardService;
    @GetMapping()
    public ResponseEntity<?> shareBoardList(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping()
    public ResponseEntity<?> shareBoardAdd(@RequestHeader("Authorization") String accessToken,
                                           @RequestBody ShareBoardDto shareBoardDto){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        int shareBoardId=shareBoardService.addShareBoard(userId,shareBoardDto);
        return ResponseEntity.status(HttpStatus.OK).body(shareBoardId);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> shareBoardDetail(@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("id") int shareBoardId){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        ShareBoardDto shareBoardDto=shareBoardService.findShareBoardDetailById(shareBoardId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> shareBoardRemove(@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("id") int shareBoardId){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        try{
            shareBoardService.removeShareBoard(userId,shareBoardId);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> shareBoardModify(@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("id") int shareBoardId,
                                              @RequestBody ShareBoardDto shareBoardDto){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        try{
            shareBoardService.modifyShareBoard(userId,shareBoardId,shareBoardDto);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/comments")
    public ResponseEntity<?> commentAdd(@RequestHeader("Authorization") String accessToken,
                                              @RequestBody ShareBoardCommentDto shareBoardCommentDto){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        int shareBoardId= shareBoardCommentDto.getShareBoardId();
        shareBoardService.addComment(userId,shareBoardCommentDto);

        return ResponseEntity.status(HttpStatus.OK).body(shareBoardId);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> commentRemove(@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("id") int commentId){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        try{
            int shareBoardId=shareBoardService.removeComment(userId,commentId);
            return ResponseEntity.status(HttpStatus.OK).body(shareBoardId);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
