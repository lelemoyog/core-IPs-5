import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest {
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
}
