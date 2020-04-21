package com.webapp.timeStampMicroservice;

import com.webapp.timeStampMicroservice.models.TimeFormats;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/api/timestamp")
public class mainController {
    @GetMapping("/")
    public ResponseEntity<Object> noDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        long unixTime = localDateTime.toEpochSecond(ZoneOffset.UTC);
        //  long unixTime = System.currentTimeMillis() / 1000L;
        // LocalTime x=LocalTime.ofNanoOfDay()
        String utcTime = LocalDateTime.now(ZoneOffset.UTC).toString();
        // Instant instant = Instant.now();
        //     String utcTime = now.toString();
        TimeFormats response1 = output(unixTime, utcTime);
        return new ResponseEntity<Object>(response1, HttpStatus.OK);
    }

    @GetMapping(value = "/{dateString}", produces = "application/json")
    public ResponseEntity<Object> getDate(@PathVariable("dateString") String date_String) throws JSONException {
        try {
            //   long unixTime = date.getTime() / 1000L;
            LocalDate date = LocalDate.parse(date_String);
            LocalTime time = LocalTime.parse("00:00:00");
            LocalDateTime date1 = date.atTime(time);
            long unixTime = date1.toEpochSecond(ZoneOffset.UTC);
            String utcTime = date1.atOffset(ZoneOffset.UTC).toString();
            TimeFormats response1 = output(unixTime, utcTime);
            return new ResponseEntity<Object>(response1, HttpStatus.OK);
        } catch (Exception e) {
            String regex = "\\d+";
            if (date_String.matches(regex)) {
                Long unixTime = Long.parseLong(date_String);
                String utcTime = LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.UTC).toString();
                TimeFormats response1 = output(unixTime, utcTime);
                return new ResponseEntity<Object>(response1, HttpStatus.OK);
            } else {
                JSONObject entity = new JSONObject();
                entity.put("error", "Invalid Date");
                return new ResponseEntity<Object>(entity.toString(), HttpStatus.OK);
            }
        }
    }

    private TimeFormats output(long unixTime, String utcTime) {
        return new TimeFormats(unixTime, utcTime);
    }
}
