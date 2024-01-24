import ids.CassandraIds;
import manager.SessionManager;
import model.Adult;
import model.Children;
import model.Passenger;
import model.Senior;
import org.junit.jupiter.api.Test;
import repositories.cas.Repository;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassengerRepositoryTest {
    public Passenger passenger;
    public Adult adult;
    public Senior senior;
    public Children children;
    @Test
    public void addTest(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Passenger> passengerRepository = sessionManager.createRepository(SessionManager.dataType.PASSENGER);
            adult = new Adult("adult","Ola","kwa", UUID.randomUUID(),25,false,0.0);
            passengerRepository.insert(adult);
            Passenger foundPassenger = passengerRepository.select(adult.getId(), CassandraIds.selectType.ADULT);
            assertEquals(adult.getDiscriminator(),foundPassenger.getDiscriminator());
            assertEquals(adult.getId(),foundPassenger.getId());
            assertEquals(adult.getFirstName(),foundPassenger.getFirstName());
            assertEquals(adult.getLastName(),foundPassenger.getLastName());
            assertEquals(adult.getId(),foundPassenger.getId());
            assertEquals(adult.getAge(),foundPassenger.getAge());
            assertEquals(adult.isArchive(),foundPassenger.isArchive());
            assertEquals(adult.getDiscount(),foundPassenger.getDiscount());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void updateTest(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Passenger> passengerRepository = sessionManager.createRepository(SessionManager.dataType.PASSENGER);
            children = new Children("children","ola","lwa",UUID.randomUUID(),14,false,0.75);
            passengerRepository.insert(children);
            Passenger foundPassenger = passengerRepository.select(children.getId(),CassandraIds.selectType.CHILDREN);
            assertEquals(children.getDiscriminator(),foundPassenger.getDiscriminator());
            assertEquals(children.getFirstName(),foundPassenger.getFirstName());
            assertEquals(children.getLastName(),foundPassenger.getLastName());
            assertEquals(children.getId(),foundPassenger.getId());
            assertEquals(children.getAge(),foundPassenger.getAge());
            assertEquals(children.isArchive(),foundPassenger.isArchive());
            assertEquals(children.getDiscount(),foundPassenger.getDiscount());
            children.setAge(17);;
            children.setLastName("apv");
            children.setLastName("abgd");
            passengerRepository.update(children);
            foundPassenger=passengerRepository.select(children.getId(),CassandraIds.selectType.CHILDREN);
            assertEquals(children.getDiscriminator(),foundPassenger.getDiscriminator());
            assertEquals(children.getFirstName(),foundPassenger.getFirstName());
            assertEquals(children.getLastName(),foundPassenger.getLastName());
            assertEquals(children.getId(),foundPassenger.getId());
            assertEquals(children.getAge(),foundPassenger.getAge());
            assertEquals(children.isArchive(),foundPassenger.isArchive());
            assertEquals(children.getDiscount(),foundPassenger.getDiscount());
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
    @Test
    public void deleteTest(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Passenger> passengerRepository = sessionManager.createRepository(
                    SessionManager.dataType.PASSENGER);
            senior = new Senior("Senior","abc","def",UUID.randomUUID(),64,false,0.7);
            passengerRepository.insert(senior);
            Passenger foundPassenger = passengerRepository.select(senior.getId(),CassandraIds.selectType.SENIOR);
            assertEquals(senior.getFirstName(),foundPassenger.getFirstName());
            assertEquals(senior.getLastName(),foundPassenger.getLastName());
            assertEquals(senior.getId(),foundPassenger.getId());
            assertEquals(senior.getAge(),foundPassenger.getAge());
            assertEquals(senior.isArchive(),foundPassenger.isArchive());
            assertEquals(senior.getDiscount(),foundPassenger.getDiscount());
            passengerRepository.delete(senior);
            foundPassenger = passengerRepository.select(senior.getId(),CassandraIds.selectType.SENIOR);
            assertNull(foundPassenger);

            }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void selectTest(){
        try(SessionManager sessionManager = new SessionManager()){
            Repository<Passenger> passengerRepository = sessionManager.createRepository(
                    SessionManager.dataType.PASSENGER
            );
            passenger = new Passenger("passenger","tre","snf",UUID.randomUUID(),19,false);
            children = new Children("children","apf","bos",UUID.randomUUID(),15,false,0.85);
            passengerRepository.insert(passenger);
            passengerRepository.insert(children);
            Passenger foundPassenger1 = passengerRepository.select(passenger.getId(),CassandraIds.selectType.PASSENGER);
            assertEquals(passenger.getDiscriminator(),foundPassenger1.getDiscriminator());
            assertEquals(passenger.getFirstName(),foundPassenger1.getFirstName());
            assertEquals(passenger.getLastName(),foundPassenger1.getLastName());
            assertEquals(passenger.getId(),foundPassenger1.getId());
            assertEquals(passenger.getAge(),foundPassenger1.getAge());
            assertEquals(passenger.isArchive(),foundPassenger1.isArchive());
            Passenger foundPassenger2 = passengerRepository.select(children.getId(),CassandraIds.selectType.CHILDREN);
            assertEquals(children.getDiscriminator(),foundPassenger2.getDiscriminator());
            assertEquals(children.getFirstName(),foundPassenger2.getFirstName());
            assertEquals(children.getLastName(),foundPassenger2.getLastName());
            assertEquals(children.getId(),foundPassenger2.getId());
            assertEquals(children.getAge(),foundPassenger2.getAge());
            assertEquals(children.isArchive(),foundPassenger2.isArchive());
            assertEquals(children.getDiscount(),foundPassenger2.getDiscount());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
