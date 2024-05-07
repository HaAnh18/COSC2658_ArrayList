import ADTs.ArrayList;
import ADTs.ArrayQueue;

public class Map2D {
    private ArrayList<Place> listOfPlace;

    public Map2D() {
        this.listOfPlace = new ArrayList<>();
    }

    public boolean add(String id, String name, int x, int y, String[] services) {
        return listOfPlace.insertAt(listOfPlace.size(), new Place(id, name, new Point(x, y), services));
    }

    public Place search(int x, int y) {
        Point searchPoint = new Point(x,y);
        for (int i = 0; i < listOfPlace.size(); i++) {
            if (searchPoint.compareTo(listOfPlace.get(i).getPoint()) == 0) {
                return listOfPlace.get(i);
            }
        }

        return null;
    }

    public boolean remove(int x, int y) {
        return listOfPlace.remove(search(x, y));
    }

    public Place edit(int x, int y, String name) {
        Place editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.setName(name);
            return editedPlace;
        }

        return null;
    }

    public Place edit(int x, int y, String[] services) {
        Place editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.setServices(services);
            return editedPlace;
        }

        return null;
    }

    public Place edit(int x, int y, String name, String[] services) {
        Place editedPlace = search(x, y);
        if (editedPlace != null) {
            editedPlace.setName(name);
            editedPlace.setServices(services);
            return editedPlace;
        }

        return null;
    }

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
