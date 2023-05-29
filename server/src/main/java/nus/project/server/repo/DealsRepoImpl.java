package nus.project.server.repo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.project.server.Utils.DealsUtils;
import nus.project.server.dto.DealsDto;
import nus.project.server.dto.VoucherDto;

import static nus.project.server.Constants.*;
import static nus.project.server.repo.Queries.*;

import java.util.List;

@Repository
public class DealsRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JdbcTemplate template;

    public void saveDeals(DealsDto dto, String id, Integer restaurantId, String restaurantName){
        Document d = DealsUtils.insertDeal(dto, id, restaurantId, restaurantName);
        mongoTemplate.save(d, COLLECTION_DEALS);
    }

    public void saveVoucher(VoucherDto dto, String id, Integer restaurantId, Integer userId){   
        Document d = DealsUtils.insertVoucher(dto, id, restaurantId, userId);
        mongoTemplate.save(d, COLLECTION_VOUCHERS);
    }

    /*
     * db.categories.find(){}
     */
    public List<Document> getAllCategories(){
        Criteria criteria = Criteria.where(FIELD_ID).exists(true);
        Query query = Query.query(criteria);
        System.out.println(query);
        return mongoTemplate.find(query, Document.class, COLLECTION_CATEGORIES);
    }

    public List<Document> getAllDeals(){
        Criteria criteria = Criteria.where(FIELD_ID).exists(true);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Document.class, COLLECTION_DEALS);
    }

    public List<Document> findCategory(String id){
        Criteria criteria = Criteria.where(FIELD_ID).is(id);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Document.class, COLLECTION_CATEGORIES);
    }

    public List<Document> getDealsBasedonCategory(String id){
        Criteria criteria = Criteria.where(FIELD_CATEGORY).is(id);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Document.class, COLLECTION_DEALS);
    }

    /*
     * db.deals.find(
     *  { category: ? }
     * )
     */
    public List<Document> findDealsbasedonCategory(String category){
        Criteria criteria = Criteria.where(FIELD_CATEGORY).is(category);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Document.class, COLLECTION_DEALS);
    }

    /*
     * db.deals.find(
        { id : ?}
        )
     */
    public List<Document> getDealbyRestaurantId(String id){
        Criteria criteria = Criteria.where(FIELD_ID).is(id);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Document.class, COLLECTION_DEALS);
    }

    public String getRestaurantNamebyId(Integer id){
        return template.queryForObject(SQL_GETRESTAURANTNAMEBYID, String.class, id);
    }

    /*
     * db.vouchers.find(
     *  { userId: ?}
     * )
     */
    public List<Document> getDealsbyUserId(Integer id){
        Criteria criteria = Criteria.where(FIELD_USERID).is(id);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Document.class, COLLECTION_VOUCHERS);
    }

    /*
     * db.deals.aggregate([ { $match: { id: '8425e54d'}}, { $lookup: { from: 'vouchers', foreignField: 'dealId', localField: 'id', as: 'vouchers'}}])
     */



}
