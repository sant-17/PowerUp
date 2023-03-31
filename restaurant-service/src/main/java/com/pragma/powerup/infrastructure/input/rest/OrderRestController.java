package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.OrderUpdateRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantEmpResponseDto;
import com.pragma.powerup.application.service.IOrderSpringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create ORDER")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created",
                    content = @Content),

            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content),

            @ApiResponse(
                    responseCode = "409",
                    description = "User can't make a new order or Dish it's from a different restaurant",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Void> saveOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
        orderSpringService.saveOrder(orderRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Get orders with pagination")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Getting orders",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = OrderResponseDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Invalid page size or number",
                    content = @Content
            )
    })
    @GetMapping("/status/{status}/size/{size}/number/{number}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(@PathVariable("status") String status, @PathVariable("size") Integer size, @PathVariable("number") Integer number){
        return ResponseEntity.ok(orderSpringService.getOrdersByStatus(number, size, status));
    }

    @Operation(summary = "Setting Chef to Order and Order status to EN_PREPARACION")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Chef set to order",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Chef it's from a different restaurant or invalid new order status",
                    content = @Content
            )
    })
    @PutMapping("/set_chef/{id}")
    public ResponseEntity<Void> setOrdersChef(@PathVariable("id") Long id){
        orderSpringService.setChef(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Setting order to LISTO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status set LISTO and SMS sended",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Employee it's from a different restaurant or invalid new order status",
                    content = @Content
            )
    })
    @PutMapping("/set_ready/{id}")
    public ResponseEntity<Void> setOrderStatusReady(@PathVariable("id") Long id){
        orderSpringService.setOrderStatusReady(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Setting order to ENTREGADO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order code confirmed and status set to ENTREGADO",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing. Code wrong",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Employee it's from a different restaurant or invalid new order status",
                    content = @Content
            )
    })
    @PutMapping("/set_delivered/{id}/code/{code}")
    public ResponseEntity<Void> setOrderStatusDelivered(@PathVariable("id") Long id, @PathVariable("code") Integer code){
        orderSpringService.setOrderStatusDelivered(id, code);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Setting order to CANCELADO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status to CANCELADO successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Invalid new order status",
                    content = @Content
            )
    })
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable("id") Long id){
        orderSpringService.cancelOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}