package fileHandling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import Entity.Enrollee;

public class FileOutput{

	public void write(List<List<Enrollee>> infoToWrite, String outPut)
	{
		BufferedWriter writer = null;
		
		if(infoToWrite.size() > 0 && infoToWrite.get(0).size() > 0)
		{
			System.out.println("writing files");
			try
			{
		        File file = new File(new Date().toString());
		        if(!file.exists()) 
		        {
		        	file.mkdir();
		        }
		        
				for(int i = 0;  infoToWrite.size() > i; i++)
				{
					writer = new BufferedWriter(new FileWriter(outPut + infoToWrite.get(i).get(0).getCompany()  + "-" + i +".txt"));

					for(int ii = 0; infoToWrite.get(i).size() > ii; ii++)
					{
						writer.write(infoToWrite.get(i).get(ii).toString());
						writer.write(System.lineSeparator());
					}
					
					writer.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
