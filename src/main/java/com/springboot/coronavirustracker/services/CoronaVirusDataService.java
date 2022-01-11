package com.springboot.coronavirustracker.services;

import com.springboot.coronavirustracker.model.LocationServices;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private ArrayList<LocationServices> allStats =  new ArrayList<>();
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        ArrayList<LocationServices> newStats = new ArrayList<>();
        // the above newStats is created for the result of current data processing. When user tries to access
        // data at the time when data is being processed, they should see previous results which is why allStats is present.
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest =  HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        StringReader csvreader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvreader);

        for (CSVRecord record : records) {
            LocationServices temp = new LocationServices();
            temp.setState(record.get("Province/State"));
            temp.setCountry(record.get("Country/Region"));
            temp.setLatestTotalCases(record.get(record.size()-1));
            System.out.println(temp);
            newStats.add(temp);

        }

        this.allStats = newStats;




    }
}
