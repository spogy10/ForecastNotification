# ForecastNotification

## In order to run application :

1. Download zip file from https://github.com/spogy10/ForecastNotification/releases/download/1/ForecastNotification.zip

2. Obtain the following api keys:

    Api Keys for SendGrid and OpenWeatherAPI needed in order to run application.

3. Place the Api Key for SendGrid in a file called "email.key"

4. Place the Api Key for OpenWeatherAPI in a file called "weather.key"

5. Extract the zip file previously downloaded and place both "email.key" and "weather.key" in that extracted folder

6. Run the executable "ForecastNotification.jar" file



The application uses an sqlite database called "WorkerDB.db" which is included in the zip file.
This contains a worker table, city table and role table which records may be inserted into as long as the database is in the same folder as the "ForecastNotification.jar" file.

Java 8 is needed to run the "ForecastNotification.jar" file.
