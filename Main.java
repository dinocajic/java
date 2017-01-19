import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2017 Dino Cajic
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Opens a file in a specific file location. The file is opened during a specific time interval (i.e. every 24 hours).
 * To run this program, you'll need to create a JAR file.
 * Instructions for creating a JAR file with JetBrains IntelliJ IDEA
 *   1. Click File
 *   2. Click Project Structure
 *   3. Select Artifacts under Project Settings
 *   4. Click on the Plus Sign and Select JAR -> From Modules with Dependencies...
 *   5. Click on the ... next to the Main Class. The class with the main() method will populate. Select it.
 *   6. Click OK
 *   7. Select the checkbox "Build on Make"
 *   8. Click Apply
 *   9. Click OK and the Window will close
 *   10. In the top menu, click Build -> Build Artifacts
 *   11. The action menu will appear. Select Build
 *   12. If you've set this up as a project in IntelliJ, you'll just have to navigate
 *       to the /out/artifacts/launch-program.jar/launch-program
 *   13. Click on the launch-program (if that's what you named it)
 */
public class Main {

    // The program that opens the file in the file location
    public String   _executable    = "notepad.exe";

    // The file location of the file you want to open
    public String   _fileLocation  = "C:\\applications\\launch-program\\src\\some-text-file.txt";

    // How frequently do you want to open this file. i.e. Every 24 hours
    public int      _openEvery     = 24;
    public TimeUnit _timeFrequency = TimeUnit.HOURS;

    // Starts the application
    public static void main(String[] args) {
        Main main = new Main();

        // Builds a process
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(main._executable, main._fileLocation);

        // Schedules the process to be run at specific time interval
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    processBuilder.start();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, main._openEvery, main._timeFrequency);
    }
}