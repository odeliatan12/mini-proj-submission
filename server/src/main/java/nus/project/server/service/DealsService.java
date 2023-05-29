package nus.project.server.service;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import nus.project.server.Utils.DealsUtils;
import nus.project.server.dto.DealsDto;
import nus.project.server.dto.VoucherDto;
import nus.project.server.model.Categories;
import nus.project.server.model.Deals;
import nus.project.server.model.Vouchers;
import nus.project.server.repo.DealsRepoImpl;

@Service
public class DealsService {

    @Autowired
    private DealsRepoImpl dealsRepository;

    public String insertNewDeals(DealsDto dto, Integer restaurantId){
        String id = UUID.randomUUID().toString().substring(0, 8);
        String restaurantName = dealsRepository.getRestaurantNamebyId(restaurantId);
        dealsRepository.saveDeals(dto, id, restaurantId, restaurantName);
        return id;
    }

    public List<JsonObject> getAllCategories(){

        List<Document> doc = dealsRepository.getAllCategories();

        System.out.println(doc);

        List<Categories> c = doc.stream().map(v -> DealsUtils.toCategories(v)).toList();

        System.out.println(c);

        List<JsonObject> o = c.stream().map(v -> DealsUtils.toJSON(v)).toList();
        System.out.println(o);
        return o;
    }

    public List<JsonObject> getAllDeals(){

        List<Document> doc = dealsRepository.getAllDeals();

        List<Deals> dto = doc.stream().map(v -> DealsUtils.toDeals(v)).toList();
        
        List<JsonObject> o = dto.stream().map(v -> DealsUtils.toJSONDeals(v)).toList();
        return o;
    }

    public List<JsonObject> getDealbyRestaurantId(String id){
        List<Document> doc = dealsRepository.getDealbyRestaurantId(id);
        List<Deals> dto = doc.stream().map(v -> DealsUtils.toDeals(v)).toList();
        return dto.stream().map(v -> DealsUtils.toJSONDeals(v)).toList();
    }

    public List<JsonObject> getDealsbyCategory(String category){
        List<Document> doc = dealsRepository.findDealsbasedonCategory(category);
        List<Deals> dto = doc.stream().map(v -> DealsUtils.toDeals(v)).toList();
        return dto.stream().map(v -> DealsUtils.toJSONDeals(v)).toList();
    }

    public String insertVouchers(VoucherDto dto, Integer restaurantId, String userId){
        String id = UUID.randomUUID().toString().substring(0, 8);
        Integer uId = Integer.parseInt(userId);
        dealsRepository.saveVoucher(dto, id, restaurantId, uId);
        return id;
    }

    public List<JsonObject> getVouchers(String userId){
        Integer id = Integer.parseInt(userId);
        List<Document> doc = dealsRepository.getDealsbyUserId(id);
        List<Vouchers> v = doc.stream().map(b -> DealsUtils.toVouchers(b)).toList();
        List<JsonObject> j = v.stream().map(a -> DealsUtils.toJSON(a)).toList(); 
        return j;
    }
    
}
