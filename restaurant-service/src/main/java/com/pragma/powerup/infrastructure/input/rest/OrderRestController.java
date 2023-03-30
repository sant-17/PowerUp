package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.OrderUpdateRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.service.IOrderSpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderSpringService orderSpringService;

    @PostMapping("/")
    public ResponseEntity<Void> saveOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
        orderSpringService.saveOrder(orderRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/{status}/{size}/{number}/")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(@PathVariable("status") String status, @PathVariable("size") Integer size, @PathVariable("number") Integer number){
        return ResponseEntity.ok(orderSpringService.getOrdersByStatus(number, size, status));
    }

    @PutMapping("/set-chef/{id}")
    public ResponseEntity<Void> setOrdersChef(@PathVariable("id") Long id, @RequestBody OrderUpdateRequestDto orderUpdateRequestDto){
        orderSpringService.setChef(id, orderUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/set_ready/{id}")
    public ResponseEntity<Void> setOrderStatusReady(@PathVariable("id") Long id){
        orderSpringService.setOrderStatusReady(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/set_delivered/{id}/code/{code}")
    public ResponseEntity<Void> setOrderStatusDelivered(@PathVariable("id") Long id, @PathVariable("code") Integer code){
        orderSpringService.setOrderStatusDelivered(id, code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable("id") Long id){
        orderSpringService.cancelOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
