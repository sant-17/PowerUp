package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishCategoryRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishCategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantEmpResponseDto;
import com.pragma.powerup.application.service.IDishSpringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishSpringService dishSpringService;

    @Operation(summary = "Create dish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Dish created",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User it's not the restaurant owner",
                    content = @Content
            ),
    })
    @PostMapping("/create")
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto){
        dishSpringService.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dish updated",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User it's not the restaurant owner",
                    content = @Content
            ),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateDishById(@PathVariable("id") Long id,@Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto){
        dishSpringService.updateDishById(id, dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Set dish active = true or false")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dish updated",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User it's not the restaurant owner",
                    content = @Content
            ),
    })
    @PutMapping("/active/{id}")
    public ResponseEntity<Void> setDishActive(@PathVariable("id") Long id,@Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto){
        dishSpringService.setDishActive(id, dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all dishes from a single restaurant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dishes found",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = DishResponseDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Invalid page size or number",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Restaurant not found",
                    content = @Content
            )
    })
    @GetMapping("/restaurant/{restaurant}/size/{size}/number/{number}")
    public ResponseEntity<List<DishResponseDto>> getAllDishesPaging(
            @PathVariable("restaurant") Long restaurant,
            @PathVariable("number") Integer pageNumber,
            @PathVariable("size") Integer pageSize){
        return ResponseEntity.ok(dishSpringService.getAllDishesPaging(restaurant, pageNumber, pageSize));
    }
}
