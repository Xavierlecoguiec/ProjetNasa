package Driver;

import java.io.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataReceiverFromDonkiAPI {


    public static void main(String args[]) throws IOException, ParseException {
        String file = "D:\\Cours\\ProjetNasa\\singleJsonSample.txt";
        String beginDate = "2016-01-01";
        String endDate = "2016-02-01";
        String url = "https://kauai.ccmc.gsfc.nasa.gov/DONKI/WS/get/FLR?startDate="+beginDate+"&endDate="+endDate;


        String source = "";
        URL flareSources = new URL(url);
        URLConnection conection = flareSources.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            source += inputLine;

            System.out.println(source);
        }

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();


        //Read JSON file

        Object obj = jsonParser.parse(source);

        JSONArray solarFlareList = (JSONArray) obj;
        System.out.println(solarFlareList);

        //Iterate over employee array
        solarFlareList.forEach(emp -> parseflareObject((JSONObject) emp));


    }

    private static void parseflareObject(JSONObject flare)
    {
        //Get employee object within list
        JSONObject flareObject = (JSONObject) flare.get("flrId");

        //Get employee first name
        String flrID = (String) flareObject.get("flrID");
        System.out.println(flrID);

        //Get employee last name
        String instruments = (String) flareObject.get("instruments");
        System.out.println(instruments);

        //Get employee website name
        String beginTime = (String) flareObject.get("beginTime");
        System.out.println(beginTime);


        //Get flare peakTime
        String peakTime = (String) flareObject.get("peakTime");

        //Get flare endTime
        String endTime = (String) flareObject.get("endTime");

        //Get flare classType
        String classType = (String) flareObject.get("classType");

        //Get flare sourceLocation
        String sourceLocation = (String) flareObject.get("sourceLocation");

        //Get flare activeRegionNum
        String activeRegionNum = (String) flareObject.get("activeRegionNum");

        //Get flare linkedEvents
        String linkedEvents = (String) flareObject.get("linkedEvents");

    }

}
