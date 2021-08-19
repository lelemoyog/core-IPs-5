import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void Animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal( "elephant",1);
        assertEquals(true, testAnimal instanceof Animal);
    }
    @Test
    public void Animal_instantiatesWithAnimalSpecies_String() {
        Animal testAnimal = new Animal( "elephant",1);
        assertEquals("elephant", testAnimal.getAnimalSpecies());
    }
    @Test
    public void Animal_instantiatesWithId_int() {
        Animal testAnimal = new Animal( "elephant",1);
        assertEquals(0, testAnimal.getId());
    }
    @Test
    public void equals_returnsTrueIfNameAndIdAreSame_true() {
        Animal testAnimal = new Animal( "elephant",1);
        Animal anotherAnimal = new Animal( "elephant",1);
        assertTrue(testAnimal.equals(anotherAnimal));
    }
    @Test
    public void save_successfullyAddsAnimalToDatabase_List() {
        Animal testAnimal = new Animal( "elephant",1);
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = new Animal( "elephant",1);
        testAnimal.save();
        Animal otherAnimal = new Animal( "elephant",1);
        otherAnimal.save();
        assertEquals(true, testAnimal.all().get(0).equals(testAnimal));
        assertEquals(true, otherAnimal.all().get(1).equals(otherAnimal));
    }
    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = new Animal ( "elephant",1);;
        testAnimal.save();
        Sighting savedAnimal =Animal.all().get(0);
        assertEquals(savedAnimal.getId(), testAnimal.getId());
    }
    @Test
    public void find_returnsAnimalWithSameId_secondPerson() {
        Animal testAnimal = new Animal( "elephant",1);
        testAnimal.save();
        Animal otherAnimal = new Animal( "elephant",1);
        otherAnimal.save();
        assertEquals(Animal.find(otherAnimal.getId()), otherAnimal);
    }
}
