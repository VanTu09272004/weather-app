import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class WeatherAppCodeJava {

    String apiKey = "Your_API_Key"; //Use your own openweathermap api key
    String rawLocation;
    MyFrame frame;

    // Constructor to initialize the GUI and set up action listeners
    public WeatherAppCodeJava() {
        MyActionListerner actionListener = new MyActionListerner();
        frame = new MyFrame();
        frame.searchButton.addActionListener(actionListener);
        frame.locationField.addActionListener(actionListener);
    };

    // Main method to run the application
    private void run() {

        try {

            String location = URLEncoder.encode(rawLocation, "UTF-8");
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location
                    + "&APPID=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String json = response.toString();

                // Simple parsing
                String city = extract(json, "\"name\":\"", "\"");
                String temp = extract(json, "\"temp\":", ",");
                String tempMin = extract(json, "\"temp_min\":", ",");
                String tempMax = extract(json, "\"temp_max\":", ",");
                String condition = extract(json, "\"description\":\"", "\"");
                String iconCode = extract(json, "\"icon\":\"", "\"");
                String feelsLike = extract(json, "\"feels_like\":", ",");
                String humidity = extract(json, "\"humidity\":", ",");
                String windSpeed = extract(json, "\"speed\":", ",");
                String windDirection = extract(json, "\"deg\":", ",");

                String iconUrl = "http://openweathermap.org/img/wn/" + iconCode + ".png";

                frame.locationLabel.setText("Location: " + city);
                frame.tempLabel.setText("Temperature: " + temp + "°C");
                frame.tempMinLabel.setText("Min Temperature: " + tempMin + "°C");
                frame.tempMaxLabel.setText("Max Temperature: " + tempMax + "°C");
                frame.conditionLabel.setText("Condition: " + condition);
                frame.feelsLikeLabel.setText("Feels Like: " + feelsLike + "°C");
                frame.humidityLabel.setText("Humidity: " + humidity + "%");
                frame.windLabel.setText("Wind: " + windSpeed + " m/s, " + windDirection + "°");
                URL imageUrl = new URL(iconUrl);
                ImageIcon weathericon = new ImageIcon(imageUrl);
                frame.conditionLabel.setIcon(weathericon);

            } else {
                // Handle error response
                JOptionPane.showMessageDialog(null,
                        "Error: Unable to fetch weather data. Response code: " + responseCode, "Error",
                        JOptionPane.ERROR_MESSAGE);
                frame.locationLabel.setText("Location: N/A");
                frame.tempLabel.setText("Temperature: N/A");
                frame.tempMinLabel.setText("Min Temperature: N/A");
                frame.tempMaxLabel.setText("Max Temperature: N/A");
                frame.conditionLabel.setText("Condition: N/A");
                frame.feelsLikeLabel.setText("Feels Like: N/A");
                frame.humidityLabel.setText("Humidity: N/A");
                frame.windLabel.setText("Wind: N/A");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Simple string extraction helper
    private String extract(String json, String start, String end) {
        int startIndex = json.indexOf(start);
        if (startIndex == -1)
            return "N/A";
        startIndex += start.length();
        int endIndex = json.indexOf(end, startIndex);
        if (endIndex == -1)
            return "N/A";
        return json.substring(startIndex, endIndex);
    }

    // ActionListener class to run the application
    private class MyActionListerner implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            rawLocation = frame.locationField.getText().trim();
            run();
        }
    }
}