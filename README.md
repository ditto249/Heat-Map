# Temperature Heatmap (Java Coursework)
This project is a Java Swing application that reads geospatial temperature data from a CSV file and visualizes it as a heatmap.
It was developed as part of a university coursework exercise.

## Project Structure
### DataPoint.java
Represents a single data point with latitude, longitude, and temperature.
Provides getters and a toString() method for debugging.

### TemperatureData.java
Reads and stores temperature data from a CSV file.
Handles parsing, conversion from Kelvin to Celsius, and provides utility methods for finding min/max temperatures.

### TemperatureDisplay.java
A custom Swing component (JComponent) that renders the heatmap.
Supports two visualization modes:
Grayscale (default): darker = colder, lighter = hotter
Color: blue = cold, green = medium, red = hot

### TemperatureApp.java
The main entry point of the program.
Creates a Swing JFrame, loads the CSV data, and displays the heatmap.

### heatmap.csv
The dataset containing temperature readings with the following format:
latitude,longitude,temperature(Kelvin)

### How It Works
The program reads temperature data from heatmap.csv.
Temperatures are converted from Kelvin → Celsius.
The data is stored in a HashMap keyed by latitude & longitude.
TemperatureDisplay calculates scaling factors to map latitude/longitude to pixel positions.
Each data point is drawn as a 1×1 pixel rectangle on the heatmap.
The chosen color scheme (grayscale or color) determines how temperatures are represented visually.

### Running the Application
Requirements:
- Java 8+
- An IDE such as IntelliJ IDEA / Eclipse / NetBeans, or run via command line.

### Steps
- Clone or download the project files.
- Place heatmap.csv in the appropriate path.
(The current path is hardcoded in TemperatureApp.java, you may want to update it.)
- temperatureData.readTemperatures("C:\\path\\to\\heatmap.csv");
- Compile the project:
javac *.java
- Run the program:
java TemperatureApp
- A window will open showing the rendered heatmap.

### Example Visualization

Grayscale Mode:
Dark pixels = colder temperatures
Light pixels = hotter temperatures

Color Mode:
Blue = Cold
Green = Moderate
Red = Hot

You can toggle useColour in TemperatureApp.java:

boolean useColour = true; // switch between grayscale and color

### Features
Reads geospatial temperature data from CSV
Converts from Kelvin → Celsius
Dynamically scales data to fit the display
Supports grayscale and color-coded heatmaps
Object-oriented design (DataPoint, Data Loader, Display, Main App)

### License
This project was developed as a university coursework exercise.
You are free to use it for learning and reference.
