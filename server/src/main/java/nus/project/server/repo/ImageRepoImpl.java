package nus.project.server.repo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.RowSet;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import nus.project.server.dto.ImageDto;
import nus.project.server.model.Image;

import static nus.project.server.repo.Queries.*;

@Repository
public class ImageRepoImpl {

    @Autowired
    private JdbcTemplate template;

    public void uploadPost(Image i)
            throws SQLException, IOException {
        template.update((PreparedStatementCreator) con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERTIMAGES);
            ps.setBlob(1, i.getPicture());
            ps.setString(2, i.getFileType());
            ps.setInt(3, i.getId());
            return ps;
        });
    }

    // public ImageDto getImagebyId(Integer id){
    //     return template.queryForObject(SQL_GETIMAGEBYID, BeanPropertyRowMapper.newInstance(ImageDto.class), id);
    // }

    public ImageDto getImage(Integer id){
        Optional<ImageDto> dto = template.query(SQL_GETIMAGEBYID, (rs) -> {
            if(!rs.next())
                return Optional.empty();
            ImageDto o = new ImageDto();
            o.setId(rs.getInt("id"));
            try {
                Blob imageBlob = rs.getBlob("image");
                if (imageBlob == null || imageBlob.length() == 0) {
                    // set a default image
                    o.setImage(getDefaultImage());
                    o.setImage_fileType("image/jpeg");
                } else {
                    o.setImage(imageBlob);
                    o.setImage_fileType(rs.getString("image_filetype"));
                }
            } catch (SQLException e) {
                // handle the exception here
                System.out.println("Unable to get image from database.");
                // set a default image
                o.setImage(getDefaultImage());
                o.setImage_fileType("image/jpeg");
            }
            System.out.println(o);
            o.setRestaurantId(rs.getInt("restaurant_id"));
            return Optional.of(o);
        }, id);

        return dto.orElseGet(() -> {
            try {
                ImageDto o = new ImageDto();
                o.setId(id);
                o.setImage(getDefaultImage());
                o.setImage_fileType("image/jpeg");
                o.setRestaurantId(id);
                return o;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error loading default image");
            }
        });
    }

    private Blob getDefaultImage() throws SerialException, SQLException {
        try {
            ClassPathResource defaultImage = new ClassPathResource("static/defaultpic.jpeg");
            InputStream inputStream = defaultImage.getInputStream();
            byte[] imageBytes = inputStream.readAllBytes();
            return new javax.sql.rowset.serial.SerialBlob(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading default image");
        }
        
    }
    
}
