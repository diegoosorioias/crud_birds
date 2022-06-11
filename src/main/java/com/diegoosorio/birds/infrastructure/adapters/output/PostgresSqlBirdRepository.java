package com.diegoosorio.birds.infrastructure.adapters.output;

import com.diegoosorio.birds.birds.aplication.domain.Bird;
import com.diegoosorio.birds.birds.aplication.domain.valueObjs.BirdId;
import com.diegoosorio.birds.birds.aplication.ports.output.BirdsRepository;
import com.diegoosorio.birds.infrastructure.models.birds.BirdDAO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PostgresSqlBirdRepository implements BirdsRepository {
    private final DataSource dataSource;

    public PostgresSqlBirdRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(Bird bird) {
        String sql="INSERT INTO birds (common_name,scientific_name,name_zone,confirmed_quantity) values ( ?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bird.getCommonName().getValue());
            preparedStatement.setString(2,bird.getScientificName().getValue());
            preparedStatement.setString(3,bird.getNameZone().getValue());
            preparedStatement.setInt(4,bird.getConfirmedQuantity().getValue());
            preparedStatement.execute();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("! Error in the query database ",exception);
        }
    }

    @Override
    public Optional<Bird> get(BirdId birdId) {
        String sql = "SELECT * FROM birds where id= ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,birdId.getValue().intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                BirdDAO birdDAO = BirdDAO.fromResulSet(resultSet);
                Bird bird = birdDAO.toDomain();
                return Optional.of(bird);
            }
            else{
                return Optional.empty();
            }
        }catch (SQLException ex){
            throw new RuntimeException("Error in the query ",ex);
        }
    }

    @Override
    public Optional<Bird> getUniqueBird(Bird bird) {
        String sql="SELECT * from birds where common_name = ? and  scientific_name = ? and name_zone = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bird.getCommonName().getValue());
            preparedStatement.setString(2,bird.getScientificName().getValue());
            preparedStatement.setString(3,bird.getNameZone().getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                BirdDAO birdDAO = BirdDAO.fromResulSet(resultSet);
                Bird birdOut = birdDAO.toDomain();
                return Optional.of(birdOut);
            }
            else{
                return Optional.empty();
            }
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("! Error in the query database ",exception);
        }
    }

    @Override
    public void update(Bird bird) {
        String sql="UPDATE birds set common_name = ? ,scientific_name= ?, name_zone = ?, confirmed_quantity = ? where id = ?";
        try(Connection connection = dataSource.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,bird.getCommonName().getValue());
            preparedStatement.setString(2,bird.getScientificName().getValue());
            preparedStatement.setString(3,bird.getNameZone().getValue());
            preparedStatement.setInt(4,bird.getConfirmedQuantity().getValue());
            preparedStatement.setInt(5,bird.getId().getValue().intValue());
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("! Error in the query database ",exception);
        }
    }

    @Override
    public Boolean delete(BirdId birdId) {
        String sql = "DELETE FROM birds where id = ?";
        try(Connection connection = dataSource.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,birdId.getValue().intValue());
            preparedStatement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException("Error in the query ",ex);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error in the query ",e);
        }
    }
}
