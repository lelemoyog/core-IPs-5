import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RangerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Henry");
        assertEquals(true, testRanger instanceof Ranger);
    }
    @Test
    public void getName_rangerInstantiatesWithName_Henry() {
        Ranger testRanger = new Ranger("Henry");
        assertEquals("Henry", testRanger.getName());
    }


    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Ranger firstRanger = new Ranger("Henry");
        Ranger anotherRanger = new Ranger("Henry");
        assertTrue(firstRanger.equals(anotherRanger));
    }
    @Test
    public void save_insertsObjectIntoDatabase_Ranger() {
        Ranger testRanger = new Ranger("Henry");
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
    }
    @Test
    public void all_returnsAllInstancesOfRanger_true() {
        Ranger firstRanger = new Ranger("Henry");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Harriet");
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }
    @Test
    public void save_assignsIdToObject() {
        Ranger testRanger = new Ranger("Henry");
        testRanger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }
    @Test
    public void find_returnsRangerWithSameId_secondRanger() {
        Ranger firstRanger = new Ranger("Henry");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Harriet");
        secondRanger.save();
        assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
    }
}
