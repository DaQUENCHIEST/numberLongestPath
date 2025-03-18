import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class trails {
    public static void main(String[] args) {
        LoadTrails loader = new LoadTrails();

        List<String> lines = loader.readFile("/Users/lucacoletti/Desktop/Java/lists.txt");

        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("\nLines with the count of zeros:");
        loader.countZeros();
        loader.createTrails();
        loader.replaceText();
    }
}

class LoadTrails {
    private final List<String> lines = new ArrayList<>();
    private int lineLength = 0;

    public List<String> readFile(String filePath) {
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                lineLength = line.length();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getPath());
        }

        return lines;
    }

    public void countZeros() {
        int totalZeros = 0;
        for (int i = 0; i < lines.size(); i++) {
            int zeroCount = countZerosInLine(lines.get(i));
            totalZeros += zeroCount;
            System.out.println("Line " + i + ": Zeros: " + zeroCount);
        }
        System.out.println("Total Zeros: " + totalZeros);
    }

    private int countZerosInLine(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

public ArrayList<Integer> path(int zeroIndex) {
    ArrayList<Integer> pathing = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();

    queue.add(zeroIndex);
    visited.add(zeroIndex);

    while (!queue.isEmpty()) {
        int index = queue.poll();
        pathing.add(index);
        int row = index / lineLength;
        int col = index % lineLength;
        int expectedValue = pathing.size(); 

        int[] rowMoves = {1, -1, 0, 0};
        int[] colMoves = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + rowMoves[i];
            int newCol = col + colMoves[i];
            int newIndex = newRow * lineLength + newCol;

            if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lineLength) {
                char nextChar = lines.get(newRow).charAt(newCol);

                if (Character.isDigit(nextChar) && Character.getNumericValue(nextChar) == expectedValue && !visited.contains(newIndex)) {
                    queue.add(newIndex);
                    visited.add(newIndex);
                }
            }
        }
    }
    return pathing;
}


    public void createTrails() {
        ArrayList<ArrayList<Integer>> pathManager = new ArrayList<>();

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lineLength; col++) {
                if (lines.get(row).charAt(col) == '0') {
                    int index = row * lineLength + col;
                    ArrayList<Integer> pathResult = path(index);
                    System.out.println("Path from index " + index + ": " + pathResult);
                    System.out.println("The ");
                    if (!pathResult.isEmpty()) {
                        pathManager.add(pathResult);
                    }
                }
            }
        }

        if (pathManager.isEmpty()) {
            System.out.println("No valid paths found.");
        } else {
            System.out.println("All valid paths:");
            for (ArrayList<Integer> path : pathManager) {
                System.out.println(path + "\n");
            }
        }
    }

public void replaceText() {
    final String RESET = "\u001B[0m";

    String[] colors = {
        "\u001B[31m", 
        "\u001B[32m", 
        "\u001B[33m", 
        "\u001B[34m", 
        "\u001B[35m", 
        "\u001B[36m", 
        "\u001B[91m", 
        "\u001B[92m", 
        "\u001B[93m", 
        "\u001B[94m", 
        "\u001B[95m", 
        "\u001B[96m"  
    };
    Random rand = new Random();
    List<ArrayList<Integer>> pathManager = new ArrayList<>();
    Map<Integer, String> pathColors = new HashMap<>();
    for (int row = 0; row < lines.size(); row++) {
        for (int col = 0; col < lineLength; col++) {
            if (lines.get(row).charAt(col) == '0') {
                int index = row * lineLength + col;
                ArrayList<Integer> pathResult = path(index);

                if (!pathResult.isEmpty()) {
                    pathManager.add(pathResult);
                    String randomColor = colors[rand.nextInt(colors.length)];
                    for (int idx : pathResult) {
                        pathColors.put(idx, randomColor);
                    }
                }
            }
        }
    }

    System.out.println("\nColor Coded:");

    for (int row = 0; row < lines.size(); row++) {
        StringBuilder coloredLine = new StringBuilder();
        
        for (int col = 0; col < lineLength; col++) {
            int index = row * lineLength + col;
            char currentChar = lines.get(row).charAt(col);

            if (pathColors.containsKey(index)) {
                coloredLine.append(pathColors.get(index)).append(currentChar).append(RESET);
            } else {
                coloredLine.append(currentChar);
            }
        }
        
        System.out.println(coloredLine);
    }
    System.out.println("\nlongest List: \n" + longestPath(pathManager));
}

    public int getLineLength() {
        return lineLength;
    }

    public int getLength(ArrayList arr){
        int length = 0;
        while(length <= arr.size()){
            length++;
        }   
        return length;
    }

    public ArrayList<Integer> longestPath(List<ArrayList<Integer>> pathList){
        ArrayList<Integer> longestList = pathList.get(0);
        int maxSum = getLength(longestList);
        for(ArrayList<Integer> col : pathList){
            int sum = getLength(col);
            if(sum > maxSum){
                maxSum = sum;
                longestList = col;
            }
        }
        return longestList;
    }  

}
