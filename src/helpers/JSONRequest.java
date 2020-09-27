package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Biktol
 */
public class JSONRequest {

    private LocalDate localDate;

    private String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&";

    public String get(String startDate, double maxMag, double minMag) throws IOException {

        localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        url += "starttime=" + startDate + "&endtime=" + localDate + "&maxmagnitude=" + maxMag + "&minmagnitude=" + minMag;

        URL urlObj = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String json = "";

        StringBuffer response = new StringBuffer();

        while ((json = br.readLine()) != null) {
            response.append(json);
        }

        return response.toString();
    }

}
