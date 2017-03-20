import java.io.File;

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
 * Renames a file to something else. For example, lets say that you need all of your files in directory JPG, renamed
 * from 00-Copy.jpg, ..., n-Copy.jpg, to 00.jpg, ..., n.jpg. You're removing the -Copy.
 */
public class RenameFilesInDirectory {

    /**
     * Runs the program
     * @param args default
     */
    public static void main(String[] args) {
        // Specifies the directory where your files to be renamed are located
        File dir = new File("D:/2017 WD/JPG2");

        // Make sure it's a directory
        if (dir.isDirectory()) {

            // Loop through the directory and get the files
            for (final File f : dir.listFiles()) {
                try {
                    // The pattern you'll be searching for. In this case, it's anything starts with a dash,
                    // followed by any character. You may have to modify the pattern (regex) to suit your needs.
                    String pattern = "-.*";
                    String new_file_name = f.getName().replaceAll(pattern, "");

                    // You may have to modify the extension to whatever your file extension needs to be.
                    new_file_name = dir + "\\" + new_file_name + ".jpg";

                    // Creates the new file
                    File new_file = new File(new_file_name);

                    // Renames the file to the new file. Since renameTo() returns a bool, it also
                    // checks to make sure that each file has been renamed.
                    if (f.renameTo(new_file)) {
                        System.out.println("Rename successful");
                    } else {
                        System.out.println("Rename failed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}