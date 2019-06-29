package fileHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileInput{
	
	public List<List<String>> readFileToArray(String file) throws FileNotFoundException
	{
		List<List<String>> enrolleeInfo;
		try 
		{
			enrolleeInfo = read(file);
		} 
		catch (FileNotFoundException e) 
		{
			throw e;
		}

		return enrolleeInfo;
	}
	
	private List<List<String>> read(String file) throws FileNotFoundException
	{
		List<List<String>> enrolleesInfo = new ArrayList<>();
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    try 
		    {
				while ((line = br.readLine()) != null) 
				{
					if(line.compareTo("") != 0)
					{
						String[] values = line.split(",");
						enrolleesInfo.add(Arrays.asList(values));
					}
				}
			} 
		    catch (IOException e) 
		    {
				//e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			throw e;
			//e.printStackTrace();
		}
		
		return enrolleesInfo;
	}

}
