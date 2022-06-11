package com.diegoosorio.birds.infrastructure.controllers;

import com.diegoosorio.birds.birds.aplication.ports.input.CreateBirdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.input.DeleteBirdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.input.QueryBirdByIdUseCase;
import com.diegoosorio.birds.birds.aplication.ports.input.UpdateBirdUseCase;
import com.diegoosorio.birds.infrastructure.models.birds.ApplicationError;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class BirdController {
    private final CreateBirdUseCase createBirdUseCase;
    private final UpdateBirdUseCase updateBirdUseCase;

    private final QueryBirdByIdUseCase queryBirdByIdUseCase;

    private final DeleteBirdUseCase deleteBirdUseCase;

    public BirdController(CreateBirdUseCase createBirdUseCase, UpdateBirdUseCase updateBirdUseCase, QueryBirdByIdUseCase queryBirdByIdUseCase, DeleteBirdUseCase deleteBirdUseCase) {
        this.createBirdUseCase = createBirdUseCase;
        this.updateBirdUseCase = updateBirdUseCase;
        this.queryBirdByIdUseCase = queryBirdByIdUseCase;
        this.deleteBirdUseCase = deleteBirdUseCase;
    }

    @RequestMapping(value = "/birds", method = RequestMethod.POST)
    public ResponseEntity<?> createBird(@RequestBody BirdDTO birdDTO){
        Map<String,Object> response = new HashMap<>();
        try{
            BirdDTO birdDTOOutput = createBirdUseCase.execute(birdDTO);
            response.put("bird",birdDTOOutput);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        catch(IllegalArgumentException | NullPointerException ex){
            ApplicationError applicationError = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of("error", ex.getMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        }
        catch (Exception e){
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            System.out.println("Error  " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value = "/birds", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBird(@RequestBody BirdDTO birdDTO){
        Map<String,Object> response = new HashMap<>();
        try{
            BirdDTO birdDTOOutput = updateBirdUseCase.execute(birdDTO);
            response.put("bird",birdDTOOutput);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        catch(IllegalArgumentException | NullPointerException ex){
            ApplicationError applicationError = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of("error", ex.getMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        }
        catch (Exception e){
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            System.out.println("Error  " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value = "/birds/{birdId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBird(@PathVariable Long birdId){
        Map<String,Object> response = new HashMap<>();
        try{
            Optional<BirdDTO> birdDTOOutput = queryBirdByIdUseCase.execute(birdId);
            if(birdDTOOutput.isEmpty()) {
                response.put("bird", null);
                response.put("status", "false");
                response.put("message", "Not found data");
            }
            else{
                response.put("bird",birdDTOOutput);
            }

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        catch(IllegalArgumentException | NullPointerException ex){
            ApplicationError applicationError = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of("error", ex.getMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        }
        catch (Exception e){
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            System.out.println("Error  " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value = "/birds/{birdId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBird(@PathVariable Long birdId){
        Map<String,Object> response = new HashMap<>();
        try{
            Boolean birdDeleted = deleteBirdUseCase.execute(birdId);
            if(birdDeleted){
                response.put("status", "Ok");
                response.put("message", "Deleted data");
            }
            else{
                response.put("status", "false");
                response.put("message", "An error occurred, validate that the id: "+ birdId +" exists.");
            }
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        catch(IllegalArgumentException | NullPointerException ex){
            ApplicationError applicationError = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of("error", ex.getMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        }
        catch (Exception e){
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            System.out.println("Error  " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }
}
