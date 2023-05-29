package nus.project.server.repo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static nus.project.server.Constants.*;

@Repository
public class PaypalRepoImpl {

    @Autowired
    private MongoTemplate template;

    /*
     * db.deals.updateOne({ id: ? },{ $inc: {  quantity: -1 } })
     */
    public void updateDeals(String id){

        Query query = Query.query(Criteria.where(FIELD_ID).is(id));
        Update update = new Update().inc(FIELD_QUANTITY, -1);
        template.updateMulti(query, update, Document.class, COLLECTION_DEALS);
        
    }

    // public List<Document> get
    
}
