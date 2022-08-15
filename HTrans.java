//HTrans
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
public class HTrans
{
    public int numrows = 0, numcols = 0, minval = 0, maxval = 0, HAngle = 180, HDist = 0,
            HMin = 9999, HMax = 0;
    public int imagearray[][], HArray[][];
    public HTrans(int nr, int nc, int minv, int maxv)
    {
        this.numrows = nr;
        this.numcols = nc;
        this.minval = minv;
        this.maxval = maxv;
        HDist = (int) Math.sqrt(nr * nr + nc * nc);
        this.imagearray = new int[numrows] [numcols];
        this.HArray = new int[2*HDist] [HAngle];
        for(int i = 0; i < this.numrows; i++)
        {
            for(int j = 0; j < this.numcols; j++)
            {
                this.imagearray [i][j] = 0;
                this.HArray [i][j] = 0;
            }
        }
    }
    public void loadimage(Scanner m)
    {
        for(int i = 0; i < this.numrows; i++)
        {
            for(int j = 0; j < this.numcols; j++)
            {
                if(m.hasNextInt())
                    this.imagearray[i][j] = m.nextInt();
            }
        }
    }
    public void BHSpace()
    {
        for(int i = 0; i < this.numrows; i++)
        {
            for(int j = 0; j < this.numcols; j++)
            {
                if(this.imagearray[i][j]>0)
                    ComputeSinusoid(i,j);
            }
        }
    }
    public void ComputeSinusoid(int i, int j)
    {
        int DAngle = 0;
        while(DAngle < 180)
        {
            double RAngle = (double)DAngle/180.0 * Math.PI;
            int dist = (int)CalcPolarDist(i, j, RAngle);
            this.HArray[dist][DAngle] = this.HArray[dist][DAngle] + 1;
            DAngle++;
        }
    }
    public double CalcPolarDist(int i, int j, double Radians)
    {
        double dist = (double)i * Math.cos(Radians) + (double)j * Math.sin(Radians) + this.HDist; return dist;
    }
    public void DetermineMinMax()
    {
        for(int i = 0; i < 2*this.HDist; i++)
        {
            for(int j = 0; j < this.HAngle; j++)
            {
                if(this.HArray[i][j] < HMin)
                    HMin = this.HArray[i][j];
                if(this.HArray[i][j] > HMax)
                    HMax = this.HArray[i][j];
            }
        }
    }
    public void Array2File(BufferedWriter outputimage, String caption)
    {
        try
        {
            outputimage.write(caption+"\n");
            for(int i = 0; i < 2*this.HDist; i++)
            {
                for(int j = 0; j < this.HAngle; j++)
                {
                    outputimage.write(this.HArray[i][j]+" ");
                }
                outputimage.write("\n");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void prettyprint(BufferedWriter outputimage, String caption)
    {
        try
        {
            outputimage.write(caption+"\n");
            for(int i = 0; i < 2*this.HDist; i++)
            {
                for(int j = 0; j < this.HAngle; j++)
                {
                    if(this.HArray[i][j] > 0)
                        outputimage.write(this.HArray[i][j] + " ");
                    else
                        outputimage.write(". ");
                }
                outputimage.write("\n");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void showArray()
    {
        for(int i = 0; i < 2*this.HDist; i++)
        {
            for(int j = 0; j < this.HAngle; j++)
            {
                System.out.print(this.HArray[i][j]+" ");
            }
            System.out.println();
        }
    }
}
