package nus.project.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nus.project.server.dto.DealsDto;
import nus.project.server.dto.VoucherDto;
import nus.project.server.service.DealsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/deals")
public class DealsController {

    @Autowired
    private DealsService dealsService;

    @PostMapping(path = "/newDeals/{restaurantId}")
    public ResponseEntity<String> insertNewDeals(@PathVariable Integer restaurantId, @RequestBody DealsDto dealsDto){
        String id = dealsService.insertNewDeals(dealsDto, restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body("Deal has been successfully posted, id is " + id);
    }

    @PostMapping(path = "/vouchers")
    public ResponseEntity<String> insertVouchers(@RequestParam Integer restaurantId, @RequestParam String userId, @RequestBody VoucherDto dto){
        String id = dealsService.insertVouchers(dto, restaurantId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Voucher has been saved, id is " + id);
    }

    @GetMapping(path = "/getCategories")
    public ResponseEntity<String> getAllCategories(){
        System.out.println(dealsService.getAllCategories());
        return ResponseEntity.status(HttpStatus.OK).body(dealsService.getAllCategories().toString());
    }

    @GetMapping(path = "/getAllDeals")
    public ResponseEntity<String> getAllDeals(){
        return ResponseEntity.status(HttpStatus.OK).body(dealsService.getAllDeals().toString());
    }

    @GetMapping(path = "/getDeal")
    public ResponseEntity<String> getDeal(@RequestParam String id){
        return ResponseEntity.status(HttpStatus.OK).body(dealsService.getDealbyRestaurantId(id).toString());
    }

    @GetMapping(path = "/getDealbyCategory")
    public ResponseEntity<String> getDealbyCategory(@RequestParam String category){
        return ResponseEntity.status(HttpStatus.OK).body(dealsService.getDealsbyCategory(category).toString());
    }

    @GetMapping(path = "/getDealbyId")
    public ResponseEntity<String> getDealbyUserId(@RequestParam String id){
        return ResponseEntity.status(HttpStatus.OK).body(dealsService.getVouchers(id).toString());
    }
    
}
