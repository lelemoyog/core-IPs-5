
import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void EndangeredAnimal_instantiatesWithEndangeredAnimalSpecies_String() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        assertEquals("elephant", testEndangeredAnimal.getAnimalSpecies());
    }
    @Test
    public void EndangeredAnimal_instantiatesWithId_int() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        assertEquals(0, testEndangeredAnimal.getId());
    }
    @Test
    public void equals_returnsTrueIfNameAndIdAreSame_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        assertTrue(testEndangeredAnimal.equals(anotherEndangeredAnimal));
    }
    @Test
    public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        testEndangeredAnimal.save();
        EndangeredAnimal otherEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        otherEndangeredAnimal.save();
        assertEquals(true, testEndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
        assertEquals(true, otherEndangeredAnimal.all().get(1).equals(otherEndangeredAnimal));
    }
    @Test
    public void save_assignsIdToEndangeredAnimal() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal ( "elephant",1);;
        testEndangeredAnimal.save();
        Sighting savedEndangeredAnimal =EndangeredAnimal.all().get(0);
        assertEquals(savedEndangeredAnimal.getId(), testEndangeredAnimal.getId());
    }
    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondPerson() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        testEndangeredAnimal.save();
        EndangeredAnimal otherEndangeredAnimal = new EndangeredAnimal( "elephant",1);
        otherEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(otherEndangeredAnimal.getId()), otherEndangeredAnimal);
    }
}
