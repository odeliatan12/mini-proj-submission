package nus.project.server.repo;

public class Queries {

    public static final String SQL_FINDBYUSERNAME = """
        select * from users where name = ?;
    """;

    public static final String SQL_GETIDBYNAME = """
        select id from users where name = ?; 
    """;

    public static final String SQL_GETPASSWORDBYNAME = """
        select password from users where name = ?;        
    """;

    public static final String SQL_USERNAMEEXISTS = """
        select count(*) from users where name = ?;        
    """;

    public static final String SQL_FINDBYNAME_ROLE = """
        select * from roles where name = ?;        
    """;

    public static final String SQL_INSERTUSER = """
        insert into users(name, password, email, contact) values ( ?, ?, ?, ?);        
    """;

    public static final String SQL_INSERTUSERROLE = """
        insert into userRoles(user_id, role_id) values (?, ?);        
    """;

    public static final String SQL_GETIDYNAME_ROLE = """
        select id from roles where name = ?;        
    """; 
    
    public static final String SQL_GETLISTOFROLES = """
        select r.name from userRoles ur right join roles r on ur.role_id = r.id where id = ?
    """;

    public static final String SQL_GETROLE = """
        select r.name from users u inner join userRoles ur on u.id = ur.user_id inner join roles r ON ur.role_id = r.id where u.id = ?        
    """;

    public static final String SQL_GETUSERINFO = """
        select * from users where id = ?;        
    """;

    public static final String SQL_INSERTRESTUARANT = """
        insert into restaurant(name, about, contact, restaurantlink, menu, user_id, address, cuisine_id, latitude, longtitude) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

    """;

    public static final String SQL_GETIDBYRESTAURANTNAME = """
        select id from restaurant where name = ?;        
    """;

    public static final String SQL_INSERTDAYS = """
        insert into days(mondayOpening, mondayClosing, tuesdayOpening, tuesdayClosing, wednesdayOpening, wednesdayClosing, thursdayOpening, thursdayClosing, fridayOpening, fridayClosing, saturdayOpening, saturdayClosing, sundayOpening, sundayClosing, restaurant_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);   
    """;

    public static final String SQL_GETRESTAURANTDETAILS = """
        select r.id, r.name, r.about, r.contact, r.restaurantlink, r.menu, r.user_id, r.address, r.cuisine_id, d.mondayOpening, d.mondayClosing, d.tuesdayOpening, d.tuesdayClosing, d.wednesdayOpening, d.wednesdayClosing, d.thursdayOpening, d.thursdayClosing, d.fridayOpening, d.fridayClosing, d.saturdayOpening, d.saturdayClosing, d.sundayOpening, d.sundayClosing from restaurant r inner join days d on r.id = d.restaurant_id where r.id = ?;
    """;

    public static final String SQL_GETRESTAURANTDETAILSBYCUISINEID = """
        select r.id, r.name, r.contact, r.about, r.restaurantlink, r.menu, r.user_id, r.address, r.cuisine_id, d.mondayOpening, d.mondayClosing, d.tuesdayOpening, d.tuesdayClosing, d.wednesdayOpening, d.wednesdayClosing, d.thursdayOpening, d.thursdayClosing, d.fridayOpening, d.fridayClosing, d.saturdayOpening, d.saturdayClosing, d.sundayOpening, d.sundayClosing
        from restaurant r inner join days d on r.id = d.restaurant_id where r.cuisine_id = ?;        
    """;

    public static final String SQL_GETRESTAURANTBYNAME = """
        select r.id, r.name, r.about , r.restaurantlink from restaurant r where r.name = ?;
    """;

    public static final String SQL_GETRESTAURANTBYID = """
        select r.id, r.name, r.about , r.restaurantlink from users u inner join restaurant r on u.id = r.user_id where u.id = ?;        
    """;

    public static final String SQL_UPDATERESTAURANTWOIMAGE = """
        update restaurant set name = ?, about = ?, contact = ?, restaurantlink = ?, menu = ?, user_id = ?, address = 
        ?, cuisine_id = ? where id = ?
    """;

    public static final String SQL_UPDATEDAYS = """
        update days set mondayOpening = ?, mondayClosing = ?, tuesdayOpening = ?, tuesdayClosing = ?, wednesdayOpening = ?, wednesdayClosing = ?, thursdayOpening = ?, thursdayClosing = ?, fridayOpening = ?, fridayClosing = ?, saturdayOpening = ?, saturdayClosing = ?, sundayOpening = ?, sundayClosing = ? where restaurant_id = ?
    """;

    public static final String SQL_UPDATEUSER = """
        update users set name = ?, email = ?, contact = ? where id = ?;        
    """;

    public static final String SQL_DELETEREVIEWS = """
        delete from reviews where restaurant_id = ?        
    """;

    public static final String SQL_DELETERESTAURANT = """
        delete from restaurant where id = ?;       
    """;

    public static final String SQL_DELETEIMAGES = """
        delete from images where restaurant_id = ?;        
    """;

    public static final String SQL_DELETEDAYS = """
        delete from days where restaurant_id = ?;        
    """;

    public static final String SQL_DELETEMEALS = """
        delete from meals where restaurant_id = ?;        
    """;
    public static final String SQL_GETRESTAURANTS = """
        select * from restaurant inner join days on restaurant.id = days.restaurant_id;        
    """;

    public static final String SQL_GETREVIEWS = """
        select * from reviews where restaurant_id = ?        
    """;

    public static final String SQL_INSERTREVIEWS = """
        insert into reviews(description, ratings, restaurant_id, user_id, timestamp) values ( ?, ?, ?, ?, ?)        
    """;

    public static final String SQL_GETRESTAURANTNAMEBYID = """
        select name from restaurant where id = ?; 
    """;

    public static final String SQL_GETCUISINE = """
        select * from cuisine;        
    """;

    public static final String SQL_GETCUISINEBYID = """
        select type from cuisine where id = ?;        
    """;

    public static final String SQL_INSERTIMAGES = """
        insert into images(image, image_filetype, restaurant_id) values ( ?, ?, ?);        
    """;

    public static final String SQL_GETIMAGEBYID = """
        select * from images where restaurant_id = ?;        
    """;

    public static final String SQL_GETREVIEWCOUNT = """
        select count(*) from reviews where restaurant_id = ?;        
    """;

    public static final String SQL_GETMEALNAMES = """
        select * from mealNames order by name asc;         
    """;

    public static final String SQL_GETMEALCATEGORIES = """
        select * from mealCategories;        
    """;

    public static final String SQL_INSERTMEALS = """
        insert meals(restaurant_id, name_id, category_id, amount) values (?, ?, ?, ?);
    """;

    public static final String SQL_GETMEALANDRESTINFO = """
        select mN.name, m.amount, m.restaurant_id, c.name as category_name , r.name as restaurant_name,r.latitude, r.longtitude, r.address, r.contact from meals m inner join mealCategories c on m.category_id = c.id inner join mealNames mN on m.name_id = mN.id inner join restaurant r on m.restaurant_id = r.id where mN.name = ?;       
    """;

    public static final String SQL_GETDISTANCE = """
        select * from distance order by distance asc;        
    """;

    public static final String SQL_INSERTCAPACITY = """
        insert into capacity(capacity, starttiming, endtiming, restaurant_Id) values (?, ?, ?, ?)        
    """;

    public static final String SQL_GETRESERVATIONTIMINGS = """
        select * from capacity where restaurant_id = ?;        
    """;

    public static final String SQL_INSERTRESERVATION = """
        insert reservations(time_id, restaurant_id, user_id, date_reserve, pax) values (?, ?, ?, ?, ?)        
    """;

    public static final String SQL_GETTOTALPAX = """
        select sum(pax) from reservations where date_reserve = ? AND time_id = ?;        
    """;

    public static final String SQL_GETTOTALCAPACITY = """
        select capacity from capacity where id = ?;        
    """;

    public static final String SQL_GETRESERVATIONBYUSERID = """
        select r.id, r.restaurant_id, r.date_reserve, r.pax, c.id as capacity_id, c.starttiming, c.endtiming, re.name, re.contact, re.address
        from reservations r
        inner join capacity c on r.time_id = c.id
        inner join restaurant re on r.restaurant_id = re.id
        where r.user_id = ?;        
    """;

    public static final String SQL_GETRESERVATIONBYID = """
        select r.id, r.restaurant_id, r.date_reserve, r.pax, c.id as capacity_id, c.starttiming, c.endtiming, re.name, re.contact, re.address
        from reservations r
        inner join capacity c on r.time_id = c.id
        inner join restaurant re on r.restaurant_id = re.id
        where r.id = ?; 
    """;

    public static final String SQL_DELETERESERVATION = """
        delete from reservations where id = ?;        
    """;
}
