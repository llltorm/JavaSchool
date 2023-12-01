import java.util.Scanner;

public class Game
{
    public static void main(String[] args)
    {
        int[][] grid = {
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };
        int row = grid[0].length;
        int column = grid[1].length;
        int generationQuantity = enteringNumber();
        printGrid(grid, row, column, 0);
        createGeneration(grid, row, column, generationQuantity);
    }

    static void createGeneration(int grid[][], int row, int column, int generationQuantity)
    {
        int[][] newGrid = grid;
        for(int i = 0; i < generationQuantity; i++)
        {
            newGrid = nextGeneration(newGrid, row, column);
            printGrid(newGrid, row, column, i + 1);
        }
    }
    
    static int[][] nextGeneration(int grid[][], int row, int column)
    {
        int[][] future = new int[row][column];
        for (int l = 0; l < row; l++)
        {
            for (int m = 0; m < column; m++)
            {
                // Нахождение количества живых соседей
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if ((l+i>=0 && l+i<row) && (m+j>=0 && m+j<column))
                            aliveNeighbours += grid[l + i][m + j];
                aliveNeighbours -= grid[l][m];
                future[l][m] = implementRules(grid[l][m], aliveNeighbours);
            }
        }
        return future;
    }

    static int implementRules(int currentSell, int aliveNeighbours)
    {
        int futureSell;
        // Клетка одинока и умирает
        if ((currentSell == 1) && (aliveNeighbours < 2))
        {
            futureSell = 0;
        }
        // Клетка погибает из-за перенаселения
        else if ((currentSell == 1) && (aliveNeighbours > 3))
        {
            futureSell = 0;
        }

        // Рождается новая клетка
        else if ((currentSell == 0) && (aliveNeighbours == 3))
        {
            futureSell = 1;
        }

        // Остается тем же самым
        else
        {
            futureSell = currentSell;
        }
        return futureSell;
    }

    static int enteringNumber()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество поколений, которые будут сгенерированы:");
        String enteredValue = in.nextLine();
        int generationQuantity = 0;
        boolean numberOk=false;
        while(!numberOk)
        {
            try
            {
                generationQuantity =  Integer.parseInt(enteredValue);
                numberOk=true;
            }
            catch (NumberFormatException ex)
            {
                System.out.print("введите целочисленное число:");
                enteredValue = in.nextLine();
            }
        }
        return generationQuantity;
    }

    static void printGrid(int grid[][], int row, int column, int generationNumber)
    {
        System.out.println("Поколение " + generationNumber + " :");
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                if (grid[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print("* ");
            }
            System.out.println();
        }
    }
}