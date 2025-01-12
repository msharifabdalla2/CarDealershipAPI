package com.example.CarDealershipAPI.Dealer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping("/api/dealers")
    public Dealer createDealer(
            @Valid @RequestBody Dealer dealer
    ) {
        return dealerService.createDealer(dealer);
    }

    @GetMapping("/api/dealers")
    public List<Dealer> getAllDealers() {
        return dealerService.getAllDealers();
    }

    @PutMapping("/api/dealers/{dealer-id}")
    public Dealer updateDealerById(
            @PathVariable("dealer-id") Integer id,
            @Valid @RequestBody Dealer updatedDealer
    ) {
        return dealerService.updateDealerById(id, updatedDealer);
    }

    @DeleteMapping("/api/dealers/{dealer-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDealerByID(
            @PathVariable("dealer-id") Integer id
    ) {
        dealerService.deleteDealerById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
