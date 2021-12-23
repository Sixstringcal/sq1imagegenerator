import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi, please enter the state of your image in the following format:");
        System.out.println("\t black white black black yellow black black white fileName");
        System.out.println("Note: you should change the colors to what you want them to be and replace fileName with what you want the svg to be titled.\n" +
                "End the program by typing \"DONE\"");
        String state = scanner.nextLine();

        while(!state.equals("DONE")){
            String[] info = state.split(" ");
            try {
                FileWriter myWriter = new FileWriter(info[8] + ".svg");
                myWriter.write(generateImage(info));
                myWriter.close();
                System.out.println("Success!");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            state = scanner.nextLine();
        }
    }

    public static String generateImage(String[] state){
        String image = generateHead();
        image = addPolygon(image, state[0], new int[][]{{0,35}, {0,0}, {35,0}, {49,49}});
        image = addPolygon(image, state[1], new int[][]{{0,63}, {0,35}, {49,49}});
        image = addPolygon(image, state[2], new int[][]{{0,63}, {0,98}, {35,98}, {49,49}});
        image = addPolygon(image, state[3], new int[][]{{35,98}, {63,98}, {49,49}});
        image = addPolygon(image, state[4], new int[][]{{98,63}, {98,98}, {63,98}, {49,49}});
        image = addPolygon(image, state[5], new int[][]{{98,63}, {98,35}, {49,49}});
        image = addPolygon(image, state[6], new int[][]{{98,35}, {98,0}, {63,0}, {49,49}});
        image = addPolygon(image, state[7], new int[][]{{35,0}, {63,0}, {49,49}});
        image += "\n</svg>";
        return image;
    }

    public static String generateHead(){
        return "<svg id=\"Layer_1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 98 98\"><defs>\n" +
                "    <style>.cls-1{\n" +
                "        fill:#fff;}\n" +
                "        .cls-1,\n" +
                "        .cls-2{stroke:#000; stroke-miterlimit:10;}\n" +
                "        .cls-2{fill:none;}</style>\n" +
                "</defs>\n";
    }

    public static String addPolygon(String image, String fill, int[][] coordinates){
        image += "<polygon\n" +
                "        points=\"";
        for(int i = 0; i < coordinates.length; i++){
            image += coordinates[i][0] + "," + coordinates[i][1] + " ";
        }
        image += "\"\n" +
                "        style=\"fill:" + fill + ";stroke:gray;stroke-width:2\"\n" +
                "        />";
        return image;
    }
}
