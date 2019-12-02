package com.example.weplay;

public class Location {
    String locationId;
    String playGroundName;
    String playGroundLocation;

    public Location(String locationId, String playGroundName, String playGroundLocation) {
        this.locationId = locationId;
        this.playGroundName = playGroundName;
        this.playGroundLocation = playGroundLocation;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getPlayGroundName() {
        return playGroundName;
    }

    public String getPlayGroundLocation() {
        return playGroundLocation;
    }
}
