
# ReadMe

This GIT Repository holds the Source Code for the below mentioned Application.

This Standalone Application downloads Bing Photo of the Day.

We can configure the number of images to be downloaded.

This Application is based on Spring Boot.

### How to use the Application

 - Clone the repository.
 - Run the command 'mvn clean package'.
 - Execute the command 'java -jar target\bing-photo-downloader.jar <# of images to download>'
    Note that the # of images can be a maximum of 7. Working on hacking around the limitation.
    Example: 'java -jar target\bing-photo-downloader.jar 7'
