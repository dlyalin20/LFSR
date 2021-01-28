/*
    Names: Daniel Lyalin; Anwesa Brahmachary
    Compilation: javac PhotoMagic.java
    Execution: java PhotoMagic filename seed tap

    Problem Statement:
        Your next task is write a LFSR client PhotoMagic.java that 
        can encrypt images. PhotoMagic should take three command-line 
        arguments, an image name, a binary password (an initial LFSR seed), 
        and a tap number. It should save an encrypted image in a file 
        named by prepending an X to the given file name (use the save() 
        method in the Picture class). It should also show the encrypted 
        image on the screen (use the show() method, also in the Picture 
        class).
*/
import java.awt.Color;

public class PhotoMagic {
    
    /*
        For each pixel (x, y), in the order (0, 0), (0, 1), (0, 2), ..., extract the red, 
        green, and blue components of the color (each component is an integer between 0 and 
        255). Then, xor the red component with 8 newly generated bits. Do the same for the 
        green (using another 8 newly generated bits) and, finally, the blue. Create a new 
        color using the result of the xor operations, and set the pixel to that color.
    */
    private static Picture convert(Picture picture, LFSR lfsr) {
        int width = picture.width();
        int height = picture.height();

        for (int col = 0; col < width; col++) { 
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                int red = lfsr.generate(8)^color.getRed();
                int green = lfsr.generate(8)^color.getGreen();
                int blue = lfsr.generate(8)^color.getBlue();
                picture.set(col, row, new Color(red, green, blue));
            }
        }
        return picture;
    }

    // PhotoMagic should take three command-line arguments, an image name, a binary password 
    // (an initial LFSR seed), and a tap number. 
   public static void main(String[] args) {
       Picture picture = new Picture(args[0]);
       String seed = args[1];
       int tap = Integer.parseInt(args[2]);

       LFSR lfsr = new LFSR(seed, tap);
       Picture newPicture = convert(picture, lfsr);
       newPicture.save("X" + args[0]);
       newPicture.show();
   }



}
