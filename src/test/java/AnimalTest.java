import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void Animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal(1,"elephant");
        assertEquals(true, testAnimal instanceof Animal);
    }
    @Test
    public void Animal_instantiatesWithName_String() {
        Animal testAnimal = new Animal(1,"elephant");
        assertEquals("elephant", testAnimal.getName());
    }
    @Test
    public void Animal_instantiatesWithId_int() {
        Animal testAnimal = new Animal(1,"elephant");
        assertEquals(1, testAnimal.getId());
    }
    @Test
    public void equals_returnsTrueIfNameAndIdAreSame_true() {
        Animal testAnimal = new Animal(1,"elephant");
        Animal anotherAnimal = new Animal(1,"elephant");
        assertTrue(testAnimal.equals(anotherAnimal));
    }
    @Test
    public void save_successfullyAddsAnimalToDatabase_List() {
        Animal testAnimal = new Animal(1,"elephant");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = new Animal(1,"elephant");
        testAnimal.save();
        Animal otherAnimal = new Animal(2,"lion");
        otherAnimal.save();
        assertEquals(true, testAnimal.all().get(0).equals(testAnimal));
        assertEquals(true, otherAnimal.all().get(1).equals(otherAnimal));
    }
    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = new Animal(1,"elephant");
        testAnimal.save();
        Animal savedAnimal =Animal.all().get(0);
        assertEquals(savedAnimal.getId(), testAnimal.getId());
    }
    @Test
    public void find_returnsAnimalWithSameId_secondPerson() {
        Animal testAnimal = new Animal(1,"elephant");
        testAnimal.save();
        Animal otherAnimal = new Animal(2,"lion");
        otherAnimal.save();
        assertEquals(Animal.find(otherAnimal.getId()), otherAnimal);
    }
}
