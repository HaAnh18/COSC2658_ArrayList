import ADTs.ArrayList;
import ADTs.ArrayQueue;

public class Map2D {
    private ArrayList<Place> listOfPlace;

    // Constructor initializes the list of places
    public Map2D() {
        this.listOfPlace = new ArrayList<>();
    }

    // Method to add a new place to the map
    public boolean add(String id, String name, int x, int y, String[] services) {
        // Create a new Place object and add it to the list of places
        return listOfPlace.insertAt(listOfPlace.size(), new Place(id, name, new Point(x, y), services));
    }

    // Method to search for a place at the specified coordinates
    public Place search(int x, int y) {
        Point searchPoint = new Point(x,y);
        // Iterate through the list of places
        for (int i = 0; i < listOfPlace.size(); i++) {
            // If a place with matching coordinates is found, return it
            if (searchPoint.compareTo(listOfPlace.get(i).getPoint()) == 0) {
                return listOfPlace.get(i);
            }
        }

        // If no place is found at the specified coordinates, return null
        return null;
    }

    // Method to remove a place at the specified coordinates
    public boolean remove(int x, int y) {
        // Search for the place at the specified coordinates
        return listOfPlace.remove(search(x, y));
    }

    // Method to edit the name of a place at the specified coordinates
    public Place edit(int x, int y, String name) {
        Place editedPlace = search(x, y);
        // If a place is found at the specified coordinates, update its name
        if (editedPlace != null) {
            editedPlace.setName(name);
            return editedPlace;
        }

        return null;
    }

    // Method to edit the services of a place at the specified coordinates
    public Place edit(int x, int y, String[] services) {
        Place editedPlace = search(x, y);
        // If a place is found at the specified coordinates, update its services
        if (editedPlace != null) {
            editedPlace.setServices(services);
            return editedPlace;
        }

        return null;
    }

    // Method to edit both the name and services of a place at the specified coordinates
    public Place edit(int x, int y, String name, String[] services) {
        Place editedPlace = search(x, y);
        // If a place is found at the specified coordinates, update its name and services
        if (editedPlace != null) {
            editedPlace.setName(name);
            editedPlace.setServices(services);
            return editedPlace;
        }

        return null;
    }

    // Method to search for available places within a specified area and offering a specific service
    public ArrayQueue<Place> searchAvailable(Point center, int width, int height, String service, int maxResults) {
        ArrayQueue<Place> results = new ArrayQueue<>();
        int minX = center.getX() - width / 2;
        int maxX = center.getX() + width / 2;
        int minY = center.getY() - height / 2;
        int maxY = center.getY() + height / 2;

        // Use a standard for loop
        for (int i = 0; i < listOfPlace.size(); i++) {
            Place place = listOfPlace.get(i);

            if (results.size() >= maxResults) {
                break; // Stop searching once we reach the maximum number of results
            }

            int x = place.getPoint().getX();
            int y = place.getPoint().getY();

            // Check if the place falls within the search area
            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                for (String availableService : place.getServices()) {
                    if (availableService.equals(service)) {
                        results.enQueue(place);
                        break; // Stop checking other services once a match is found
                    }
                }
            }
        }

        return results;
    }

}
