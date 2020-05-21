package com.example.cornoa.services;


import com.example.cornoa.models.Location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class VirusData {


    @Autowired
    RestTemplate restTemplate;

    String baseUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<Location> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *")
    public void getData() throws IOException {
        ArrayList<Location> newStates = new ArrayList<>();
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl, String.class);

        String virusData = response.getBody();

        StringReader in = new StringReader(virusData);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            Location location = new Location();
            location.setState(record.get("Province/State"));
            location.setCountry(record.get("Country/Region"));
            int leatestTotalCases = Integer.parseInt(record.get(record.size() - 1));
            int previousTotalCases = Integer.parseInt(record.get(record.size() - 2));
            location.setTotalCases(leatestTotalCases);
            location.setPreviousCases(leatestTotalCases - previousTotalCases);
            newStates.add(location);
        }
        this.allStats = newStates;
    }

    public List<Location> getAllStats() {
        return allStats;
    }

    public void setAllStats(List<Location> allStats) {
        this.allStats = allStats;
    }
}
