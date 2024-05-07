public class Place implements Comparable<Place> {
    private String id;
    private String name;
    private final Point point;
    private String[] services;

    // Constructor to initialize the attributes of the Place
    public Place(String id, String name, Point position, String[] services) {
        this.id = id;
        this.name = name;
        this.point = position;
        this.services = services;
    }

    // Getter and setter methods for name and services
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    // Getter method for the point
    public Point getPoint() {
        return point;
    }

    // Override toString() method to provide a string representation of the Place object
    @Override
    public String toString() {
        // Constructing the string representation with ID, name, point coordinates, and services
        String res = "";
        res += "ID: " + id + ", Name: " + name + ", Point: (" + point.getX() + "," + point.getY() + "), Services: ";
        for (String service : services) {
            res += service + ", ";
        }
        return res;
    }

    // Override compareTo() method to compare two Place objects based on their points
    @Override
    public int compareTo(Place otherPlace) {
        // Compare the points of two places
        if (point.compareTo(otherPlace.getPoint()) == 0) {
           return 0; // If points are equal, return 0
       }

        return -1; // Otherwise, return -1
    }
}
