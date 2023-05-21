package com.bonvoyage.domain.route.controller;

import com.bonvoyage.domain.route.dto.RouteDto;
import com.bonvoyage.domain.route.service.RouteService;
import com.bonvoyage.domain.user.service.JWTService;
import com.bonvoyage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final JWTService jwtService;

    @GetMapping()
    public ResponseEntity<?> routeList(){
        List<RouteDto> routeDtoList= routeService.findRouteList();
        return ResponseEntity.status(HttpStatus.OK).body(routeDtoList);
    }
    @GetMapping("/test")
    public ResponseEntity<?> routeListTest(){
        List<RouteDto> routeDtoList= routeService.findRouteListWithDetail();
        return ResponseEntity.status(HttpStatus.OK).body(routeDtoList);
    }
    @PostMapping
    public ResponseEntity<?> routeAdd(@RequestHeader("Authorization") String accessToken,
                                      @RequestBody RouteDto routeDto){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        int newRouteId=routeService.addRoute(userId,routeDto);
        return ResponseEntity.status(HttpStatus.OK).body(newRouteId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> routeDelete(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("id") int routeId){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        try {
            routeService.deleteRoute(userId,routeId);
        }catch (SecurityException e){return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 요청입니다.");}catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> routeModify(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("id") int routeId,
                                         @RequestBody RouteDto routeDto){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        try{
            int modifiedRouteId=routeService.modifyRoute(userId,routeId,routeDto);
            return ResponseEntity.status(HttpStatus.OK).body(modifiedRouteId);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> routeDetail(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("id") int routeId){
        if(jwtService.checkToken(accessToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다");
        }
        int userId=jwtService.getUserId(accessToken);
        RouteDto routeDto = routeService.findRouteDetail(routeId);
        return ResponseEntity.status(HttpStatus.OK).body(routeDto);
    }

}
