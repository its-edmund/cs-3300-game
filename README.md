# Masterpiece: CS 3300 Group 5's Board Game

## Installation Instructions

### Dependencies
* Python 3

### IntelliJ Idea IDE

Use the IntelliJ Idea IDE. Download the IntelliJ Idea Community [here](https://www.jetbrains.com/idea/).

### Java 11

To use JavaFX, you must use the Java 11 SDK. Fortunately, IntelliJ makes this easy.
Upon opening the project with `File > Open`, IntelliJ may prompt you to download a Java 11 SDK if you don't have
one installed. Please accept the prompt if it appears. If the prompt does not appear, go to 
`File > Project Structure > Project`. Find the section that says `Project SDK` and click on the dropdown.
Then, click on `Add SDK > Download JDK`. When the new menu appears, change the `version` to 11.
There should be an option labeled `Amazon Corretto`. Hit `Download`; once that finishes, hit `Apply` then `OK`.

### JavaFX

To download the JavaFX dependency, simply run:
``` 
python setup.py
```
A new `lib` folder should appear!

### Building and Running

Now, select the run configuration that matches your operating system. 
To change the run configuration, click on `Run > Edit Configurations`. Alternatively, you can hit
the rectangle between the build hammer and the run arrow in the top bar, then click on `Edit Configurations`.
If you are using Windows 10, select `JavaFX Windows` then hit `OK`.
If you are using Linux or macOS, select `JavaFX Linux/macOS` then hit `OK`.
To run the project, click on `Run > Run` or hit the green arrow in the top bar.