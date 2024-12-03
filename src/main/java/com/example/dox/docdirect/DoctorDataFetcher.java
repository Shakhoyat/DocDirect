package com.example.dox.docdirect;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoctorDataFetcher {

    static class Doctor {
        public int id;
        public String name;
        public String title;
        public List<String> specialities;
        public int consultationFee;
        public String qualification;
        public String description;
        public String imageUrl;
        public List<Schedule> schedule;

        // Getters and Setters for all fields
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public List<String> getSpecialities() { return specialities; }
        public void setSpecialities(List<String> specialities) { this.specialities = specialities; }
        public int getConsultationFee() { return consultationFee; }
        public void setConsultationFee(int consultationFee) { this.consultationFee = consultationFee; }
        public String getQualification() { return qualification; }
        public void setQualification(String qualification) { this.qualification = qualification; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
        public List<Schedule> getSchedule() { return schedule; }
        public void setSchedule(List<Schedule> schedule) { this.schedule = schedule; }

        public static class Schedule {
            private String date;
            private String time;
            private String day;

            public String getDate() { return date; }
            public void setDate(String date) { this.date = date; }
            public String getTime() { return time; }
            public void setTime(String time) { this.time = time; }
            public String getDay() { return day; }
            public void setDay(String day) { this.day = day; }
        }
    }

    public static List<Doctor> fetchDoctorsFromApi(String apiUrl) {
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Build HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            JsonElement rootElement = JsonParser.parseString(response.body());
            if (!rootElement.isJsonArray()) {
                throw new IllegalArgumentException("Expected JSON array as the root element");
            }

            JsonArray doctorArray = rootElement.getAsJsonArray();
            List<Doctor> doctors = new ArrayList<>();

            for (JsonElement element : doctorArray) {
                if (!element.isJsonObject()) continue;

                JsonObject doctorObject = element.getAsJsonObject();
                Doctor doctor = new Doctor();

                // Extract data from JSON object
                doctor.id = doctorObject.has("id") ? doctorObject.get("id").getAsInt() : 0;
                doctor.name = doctorObject.has("name") ? doctorObject.get("name").getAsString() : "";
                doctor.title = doctorObject.has("title") ? doctorObject.get("title").getAsString() : "";
                doctor.consultationFee = doctorObject.has("consultationFee") ? doctorObject.get("consultationFee").getAsInt() : 0;
                doctor.qualification = doctorObject.has("qualification") ? doctorObject.get("qualification").getAsString() : "";
                doctor.description = doctorObject.has("description") ? doctorObject.get("description").getAsString() : "";
                doctor.imageUrl = doctorObject.has("imageUrl") ? doctorObject.get("imageUrl").getAsString() : "";

                // Parse specialities
                if (doctorObject.has("specialities") && doctorObject.get("specialities").isJsonArray()) {
                    JsonArray specialitiesArray = doctorObject.get("specialities").getAsJsonArray();
                    doctor.specialities = new ArrayList<>();
                    for (JsonElement speciality : specialitiesArray) {
                        doctor.specialities.add(speciality.getAsString());
                    }
                }

                // Parse schedule
                if (doctorObject.has("schedule") && doctorObject.get("schedule").isJsonArray()) {
                    JsonArray scheduleArray = doctorObject.get("schedule").getAsJsonArray();
                    doctor.schedule = new ArrayList<>();
                    for (JsonElement scheduleElement : scheduleArray) {
                        if (!scheduleElement.isJsonObject()) continue;

                        JsonObject scheduleObject = scheduleElement.getAsJsonObject();
                        Doctor.Schedule schedule = new Doctor.Schedule();

                        schedule.date = scheduleObject.has("date") ? scheduleObject.get("date").getAsString() : "";
                        schedule.time = scheduleObject.has("time") ? scheduleObject.get("time").getAsString() : "";
                        schedule.day = scheduleObject.has("day") ? scheduleObject.get("day").getAsString() : "";

                        doctor.schedule.add(schedule);
                    }
                }

                // Add doctor to list
                doctors.add(doctor);
            }

            return doctors;

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        }
    }
}
