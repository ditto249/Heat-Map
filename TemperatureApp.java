import javax.swing.*;
import java.util.List;
import java.util.Map;

public class TemperatureApp {
    public static void main(String[] args) {
        System.out.println("Temperature App");
        TemperatureData temperatureData = new TemperatureData();
        System.out.println("TemperatureData object created");
        // find absolute path of file
        temperatureData.readTemperatures("C:\\Users\\dithu\\IdeaProjects\\CE152 Java Programming Exercise\\src\\exercise3\\heatmap.csv");
        Map<String, DataPoint> dataPoints = temperatureData.getDataPoints();
        System.out.println("Number of data points read: " + dataPoints.size());
        // Create a JFrame to hold the TemperatureDisplay component
        JFrame frame = new JFrame("Temperature Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("JFrame created");
        boolean useColour = false;
        // Create a TemperatureDisplay component and add it to the frame
        TemperatureDisplay temperatureDisplay = new TemperatureDisplay(temperatureData, useColour);
        frame.getContentPane().add(temperatureDisplay);
        System.out.println("TemperatureDisplay component added to JFrame");
        // size of frame
        frame.setSize(600, 600);
        frame.setVisible(true);
        System.out.println("Temperature Display frame created and made visible");

    }
}
