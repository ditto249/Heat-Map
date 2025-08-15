import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class TemperatureDisplay extends JComponent {
    private boolean useColour; // what colour scheme to choose
    private TemperatureData temperatureData;
    //constructor to initialise TemperatureDisplay
    public TemperatureDisplay(TemperatureData temperatureData, boolean useColour){
        this.useColour = useColour;
        this.temperatureData = temperatureData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //dimensions of the component
        int width = getWidth();
        int height = getHeight();
        if (temperatureData != null) {
            Collection<DataPoint> dataPoints = temperatureData.getDataPoints().values();
            //System.out.println("Number of data points: " + dataPoints);
            // initialise variables to calculate range
            double maxLatitude = Double.MIN_VALUE;
            double minLatitude = Double.MAX_VALUE;
            double maxLongitude = Double.MIN_VALUE;
            double minLongitude = Double.MAX_VALUE;
        // temp range
        double tempRange = TemperatureData.getMaxTemperature(dataPoints) - TemperatureData.getMinTemperature(dataPoints);
        System.out.println("Component Size - Width: " + width + ", Height: " + height);
        System.out.println("Temperature Range: " + tempRange);
            //iterate to find max and min values
            for (DataPoint dataPoint : dataPoints) {
                if (dataPoint != null) {
                    maxLatitude = Math.max(maxLatitude, dataPoint.getLatitude());
                    minLatitude = Math.min(minLatitude, dataPoint.getLatitude());
                    maxLongitude = Math.max(maxLongitude, dataPoint.getLongitude());
                    minLongitude = Math.min(minLongitude, dataPoint.getLongitude());
                }
            }
            // Calculate latitude and longitude ranges
            double latitudeRange = maxLatitude - minLatitude;
            double longitudeRange = maxLongitude - minLongitude;
            // Calculate scaling factors for latitude and longitude
            double latitudeScalingFactor = height / latitudeRange;
            double longitudeScalingFactor = width / longitudeRange;
            //System.out.println("Received data points:");
            // Iterate over the data points to paint them on the component
        for (DataPoint dataPoint : dataPoints) {
            //System.out.println("Data Point: " + dataPoint);
            if (dataPoint != null) {
                double latitude = dataPoint.getLatitude();
                double longitude = dataPoint.getLongitude();
                double temperature = dataPoint.getTemperature();
                Color color;

                if (useColour) {
                    color = getColorForTemperature(temperature, tempRange);
                } else {
                    int colorValue = (int) ((temperature - TemperatureData.getMinTemperature(dataPoints)) / tempRange * 255);
                    color = new Color(colorValue, colorValue, colorValue);
                }
                //System.out.println("Temperature: " + temperature + ", Color: " + color);
                //System.out.println("Latitude: " + dataPoint.getLatitude() + ", Longitude: " + dataPoint.getLongitude());

                // Set color and position for drawing
                g.setColor(color);
                int x = (int) ((longitude - minLongitude) * longitudeScalingFactor);
                int y = (int) ((maxLatitude - latitude) * latitudeScalingFactor);
                //System.out.println("Position - X: " + x + ", Y: " + y);
                g.fillRect(x, y, 1,1);
            }
            }

        }
    }

    // The method to determine color based on temperature range
    private Color getColorForTemperature(double temperature, double tempRange) {
        // Get the values from the map of data points
        Collection<DataPoint> dataPoints = temperatureData.getDataPoints().values();
        // Get the minimum temperature from the collection of data points
        double minTemperature = TemperatureData.getMinTemperature(dataPoints);
        // Define color thresholds based on the temperature range and minimum temperature
        double lowThreshold = minTemperature + tempRange * 0.33;
        double mediumThreshold = minTemperature + tempRange * 0.66;
        // Determine color based on temperature range
        if (temperature < lowThreshold) {
            return Color.BLUE;
        } else if (temperature < mediumThreshold) {
            return Color.GREEN;
        } else {
            return Color.RED;
        }
    }
}
