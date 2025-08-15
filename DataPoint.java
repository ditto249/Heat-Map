public class DataPoint {
    private  double latitude;
    private double longitude;
    double temperature;


    DataPoint(double latitude, double longitude, double temperature){ // Constructor to initialise values
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }
    // getter methods to return values
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return String.format("Latitude: %.2f, Longitude: %.2f, Temperature: %.2f", latitude, longitude, temperature);
    }

}
