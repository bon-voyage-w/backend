package com.bonvoyage.domain.route.controller;

import com.bonvoyage.domain.route.dto.RouteDto;
import com.bonvoyage.domain.route.service.RouteService;
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
        int routeId=routeService.routeAdd(routeDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> routeDelete(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("id") int routeId){
        try {
            routeService.deleteRoute(routeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> routeModify(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("id") int routeId,
                                         @RequestBody RouteDto routeDto){
        int modifiedRouteId=routeService.modifyRoute(routeDto);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedRouteId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> routeDetail(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("id") int routeId){

        RouteDto routeDto = routeService.findRouteDetail(routeId);
        return ResponseEntity.status(HttpStatus.OK).body(routeDto);
    }

}
