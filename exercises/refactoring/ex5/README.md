# More refactoring

Refactor the following code and make it as clean as possible !

The `Room` class will be your main focus. It has a name, a location and it's occupancies are represented by a `Map` that links a `TimeSlot` to a `Course`. The latter are not fully implemented here but you can assume that they have correct attributes and working methods.

```java

public class Location {
    private double locationLatitude;
    private double locationLongitude;
    private int floor;

    private static final double FLOOR_HEIGHT = 10.0;  

    public double distanceFrom (Location other) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(locationLatitude - other.getLocationLatitude());
        double lonDistance = Math.toRadians(locationLongitude - other.getLocationLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(other.getLocationLongitude())) * Math.cos(Math.toRadians(locationLatitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        // approximate elevations between 2 floors to be 10m 
        double elevationDiff = (other.getFloor()- floor) * FLOOR_HEIGHT;

        distance = Math.pow(distance, 2) + Math.pow(elevationDiff, 2);

        return Math.sqrt(distance);
    }


    // getters 
}

public class Room {
    private String name;
    private Location; 
    private Map<TimeSlot, Course> occupancies;
    
    public Room(RoomName name, Location location, Map<TimeSlot, Course> occupancies) {
        // initialize attributes
    }
    
    public boolean isAvailable() {
        TimeSlot now = TimeSlot.now(); // returns the TimeSlot that we're currently in
        return isAvailableAt(now); 
    }
    
    public boolean isAvailableAt(TimeSlot slot) {
        return occupancies.containsKey(slot); 
    }
    
    public void getName() {
        return name;
    }
    
    // distance in meters from other to this room's location
    public double distanceFrom(Location otherLocation) {
        return location.distanceFrom(otherLocation); 
    }
    
    private void updateSubject(int nbSubject, Course.TYPE subject, Course.TYPE currentBest, int currentMax) {
        nbSubject++;
        if(nbSubject > currentMax) {
            currentMax = nbSubject;
            currentBest = subject;
        }
    }

    public Course.TYPE mostCommonCourseType() {
        int nbMath, nbArt, nbEnglish, nbHistory, nbGeography;
        Course.TYPE currentBest = null;
        int currentMax = -1;
        for (Course c : occupancies.values()) {
            switch(c.getType()) {
                case MATH:
                    updateSubject(nbMath, MATH, currentBest, currentMax); 
                    break;
                case ART:
                    updateSubject(nbArt, ART, currentBest, currentMax); 
                    break;
                case ENGLISH:
                   updateSubject(nbEnglish, ENGLISH, currentBest, currentMax); 
                    break;
                case HISTORY:
                    updateSubject(nbHistory, HISTORY, currentBest, currentMax); 
                    break;
                case GEOGRAPHY:
                   updateSubject(nbGeography, GEOGRAPHY, currentBest, currentMax); 
                    break;
                default:
                    throw new Error("Undefined course type !");
            }
        }
        return currentBest;
    }
}


public class Course {
    // ...
    
    public enum TYPE {
        MATH, ART, ENGLISH, HISTORY, GEOGRAPHY
    };
}

public class TimeSlot {
    // ...
}
```