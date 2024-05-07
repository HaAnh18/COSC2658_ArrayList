import ADTs.ArrayQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Map2DTest {
    private Map2D map;
    private Place place1, place2, place3, place4, place5;

    @BeforeEach
    public void setUp() {
        map = new Map2D();

        // Define five different places with unique coordinates and services
        place1 = new Place("A1", "Restaurant A1", new Point(10, 20), new String[] {"Restaurant", "WiFi"});
        place2 = new Place("A2", "Cafe A2", new Point(15, 25), new String[] {"Cafe", "Parking"});
        place3 = new Place("A3", "Library A3", new Point(20, 30), new String[] {"Library", "WiFi"});
        place4 = new Place("A4", "Market A4", new Point(25, 35), new String[] {"Market", "Delivery"});
        place5 = new Place("A5", "Hospital A5", new Point(30, 40), new String[] {"Hospital", "Parking"});

        // Add all five places to the map
        map.add(place1.getName(), place1.getName(), place1.getPoint().getX(), place1.getPoint().getY(), place1.getServices());
        map.add(place2.getName(), place2.getName(), place2.getPoint().getX(), place2.getPoint().getY(), place2.getServices());
        map.add(place3.getName(), place3.getName(), place3.getPoint().getX(), place3.getPoint().getY(), place3.getServices());
        map.add(place4.getName(), place4.getName(), place4.getPoint().getX(), place4.getPoint().getY(), place4.getServices());
        map.add(place5.getName(), place5.getName(), place5.getPoint().getX(), place5.getPoint().getY(), place5.getServices());
    }

    @Test
    public void testAddAndSearchPlace() {
        // Verify that all five places can be found in the map
        Place foundPlace1 = map.search(10, 20);
        assertNotNull(foundPlace1, "Place should exist at coordinates (10, 20)");
        assertEquals("Restaurant A1", foundPlace1.getName(), "Place name should match");

        Place foundPlace2 = map.search(15, 25);
        assertNotNull(foundPlace2, "Place should exist at coordinates (15, 25)");
        assertEquals("Cafe A2", foundPlace2.getName(), "Place name should match");

        Place foundPlace3 = map.search(20, 30);
        assertNotNull(foundPlace3, "Place should exist at coordinates (20, 30)");
        assertEquals("Library A3", foundPlace3.getName(), "Place name should match");

        Place foundPlace4 = map.search(25, 35);
        assertNotNull(foundPlace4, "Place should exist at coordinates (25, 35)");
        assertEquals("Market A4", foundPlace4.getName(), "Place name should match");

        Place foundPlace5 = map.search(30, 40);
        assertNotNull(foundPlace5, "Place should exist at coordinates (30, 40)");
        assertEquals("Hospital A5", foundPlace5.getName(), "Place name should match");

        // Test for a non-existing place
        Place nonExistingPlace = map.search(100, 200);
        assertNull(nonExistingPlace, "Place should not exist at coordinates (100, 200)");
    }

    @Test
    public void testRemovePlace() {
        // Remove an existing place and check if it's gone
        assertTrue(map.remove(10, 20), "Place should be removed at coordinates (10, 20)");
        assertNull(map.search(10, 20), "Place should no longer exist at coordinates (10, 20)");

        // Attempt to remove a non-existent place
        assertFalse(map.remove(100, 200), "Attempting to remove a non-existing place should return false");
    }

    @Test
    public void testEditPlaceName() {
        // Update the name of a place
        Place updatedPlace = map.edit(10, 20, "Updated Restaurant A1");
        assertNotNull(updatedPlace, "Updated place should exist");
        assertEquals("Updated Restaurant A1", updatedPlace.getName(), "Name should be updated");
    }

    @Test
    public void testEditPlaceServices() {
        // Update the services of a place
        Place updatedPlace = map.edit(15, 25, new String[] {"Cafe", "WiFi"});
        assertNotNull(updatedPlace, "Updated place should exist");
        assertArrayEquals(new String[] {"Cafe", "WiFi"}, updatedPlace.getServices(), "Services should be updated");
    }

    @Test
    public void testSearchAvailablePlaces() {
        // Search for a place offering "Parking" within a specified region
        ArrayQueue<Place> results = map.searchAvailable(new Point(20, 30), 30, 30, "Parking", 3);
        assertEquals(2, results.size(), "Two places should be found with 'Parking'");
        assertEquals("Cafe A2", results.peekFront().getName(), "First found place should be 'Cafe A2'");
        results.deQueue();
        assertEquals("Hospital A5", results.peekFront().getName(), "Second found place should be 'Hospital A5'");
    }
}
