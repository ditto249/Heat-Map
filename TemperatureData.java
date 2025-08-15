import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TemperatureData {
    private Map<String, DataPoint> datapoints; // Map to store data points

    public TemperatureData() {
        datapoints = new HashMap<>();
    } // Constructor to initalise Map

    public void readTemperatures(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Splits the data by the ","
                String[] parts = line.split(",");
                //Puts the data into different variables
                if (parts.length == 3) {
                    double latitude = Double.parseDouble(parts[0]);
                    double longitude = Double.parseDouble(parts[1]);
                    double temperature = Double.parseDouble(parts[2]) - 273.15;
                    // puts data into hashMap
                    DataPoint dataPoint = new DataPoint(latitude, longitude, temperature);
                    // Makes a key to put into the Map
                    String key = generateKey(latitude, longitude);
                    //System.out.println("Key: " + key + ", Data Point: " + dataPoint);
                    datapoints.put(key, dataPoint);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading temperatures from file", e);
        }
    }
// getter method to return the datapoints Map
    public Map<String, DataPoint> getDataPoints() {
        return datapoints;
    }
// the format of the key
    private String generateKey(double latitude, double longitude) {
        return Double.toString(latitude) + "," + Double.toString(longitude);
    }
// will find the minTemp in the data
    public static double getMinTemperature(Collection<DataPoint> points) {
        double minTemperature = Double.MAX_VALUE;
        // Iterate through the data to find the highest value
        for (DataPoint dataPoint : points) {
            if (dataPoint != null && dataPoint.getTemperature() < minTemperature) {
                minTemperature = dataPoint.getTemperature();
            }
        }
        return minTemperature;
    }
    // will find the maxTemp in the data
    public static double getMaxTemperature(Collection<DataPoint> points) {
        double maxTemp = Double.MIN_VALUE;
        // Iterate through the data to find the lowest value
        for (DataPoint data : points) {
            maxTemp = Math.max(maxTemp, data.temperature);
        }
        return maxTemp;
    }

}
