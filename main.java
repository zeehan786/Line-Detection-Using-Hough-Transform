//Main
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws IOException
    {
// TODO Auto-generated method stub
        String inputfilename = args[0];
        FileReader inputImage = null;
        BufferedReader buffinImage = null;
        Scanner input = null;
        String outputname = args[1];
        FileWriter outputwriter = null;
        BufferedWriter output = null;
        String outputname2 = args[2];
        FileWriter outputwriter2 = null;
        BufferedWriter output2 = null;
        try
        {
            inputImage = new FileReader(inputfilename);
            buffinImage = new BufferedReader(inputImage);


            input = new Scanner(buffinImage);
        outputwriter = new FileWriter(outputname);
        output = new BufferedWriter(outputwriter);
        outputwriter2 = new FileWriter(outputname2);
        output2 = new BufferedWriter(outputwriter2);
        int numrows = 0, numcols = 0, minval = 0, maxval = 0;
        if(input.hasNextInt()) numrows = input.nextInt();
        else System.out.println("Invalid format of header");
        if(input.hasNextInt()) numcols = input.nextInt();
        else System.out.println("Invalid format of header");
        if(input.hasNextInt()) minval = input.nextInt();
        else System.out.println("Invalid format of header");
        if(input.hasNextInt()) maxval = input.nextInt();
        else System.out.println("Invalid format of header");
        HTrans obj = new HTrans(numrows, numcols, minval, maxval);
        obj.loadimage(input);
        obj.BHSpace();
        obj.prettyprint(output, "PrettyPrint");
        obj.DetermineMinMax();
        output2.write(obj.HDist +" " + obj.HAngle + " " + obj.HMin + " " + obj.HMax);
        obj.Array2File(output2, "");
        }
        finally
        {
        if(input!=null) input.close();
        if(output!=null) output.close();
        if(output2!=null) output2.close();
        }
        }
        }
