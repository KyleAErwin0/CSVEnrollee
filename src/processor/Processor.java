package processor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entity.Enrollee;
import fileHandling.FileInput;
import fileHandling.FileOutput;

public class Processor {
	
	private FileInput reader = new FileInput();
	private FileOutput writer = new FileOutput();
	private String file = "";
	private String outPut = "";
	
	public Processor(String file, String outPut) throws FileNotFoundException
	{
		this.outPut = outPut;
		this.file = file;
		List<List<Enrollee>> infoToWrite;
		try 
		{
			infoToWrite = processInputFile();
			createOutputFile(infoToWrite);
		} 
		catch (FileNotFoundException e) 
		{
			throw e;
		}
		
	}
	
	private List<List<Enrollee>> processInputFile() throws FileNotFoundException
	{
		List<List<String>> enrolleeInfo;
		try 
		{
			enrolleeInfo = reader.readFileToArray(file);
		} 
		catch (FileNotFoundException e) 
		{
			throw e;
		}
		
		return prepareEntities(enrolleeInfo);
	}
	
	private void createOutputFile(List<List<Enrollee>> infoToWrite)
	{
		writer.write(infoToWrite, outPut);
	}
	
	private List<List<Enrollee>> prepareEntities(List<List<String>> infoIn)
	{
		List<List<Enrollee>> entitiesToWrite = new ArrayList<>();
		HashMap<String, Enrollee> hash = new HashMap<String, Enrollee>();
		String processingCompany = "";
		
		for(int i = 0; infoIn.size() > i; i++)
		{
			Enrollee enrollee = setEnrolleeInfo(infoIn.get(i));
			if(processingCompany != null && processingCompany.equalsIgnoreCase(""))
			{
				hash.put(enrollee.getUserId(), enrollee);
				processingCompany = enrollee.getCompany();
			}
			else
			{
				if(enrollee.getCompany() != null && enrollee.getCompany().equalsIgnoreCase(processingCompany))
				{
					if(hash.containsKey(enrollee.getUserId()))
					{
						if(hash.get(enrollee.getUserId()).getVersion() < enrollee.getVersion())
						{
							hash.remove(enrollee.getUserId());
							hash.put(enrollee.getUserId(), enrollee);
						}
					}
					else
					{
						hash.put(enrollee.getUserId(), enrollee);
					}
				}
				else
				{
					List<Enrollee> enrolleeList = getListofEnrollees(hash);
					Collections.sort(enrolleeList);
					entitiesToWrite.add(enrolleeList);
					processingCompany = enrollee.getCompany();
					hash = new HashMap<String, Enrollee>();
					hash.put(enrollee.getUserId(), enrollee);
				}
			}
		}
		List<Enrollee> enrolleeList = getListofEnrollees(hash);
		Collections.sort(enrolleeList);
		entitiesToWrite.add(enrolleeList);
		
		return entitiesToWrite;
	}
	
	private List<Enrollee> getListofEnrollees(HashMap<String, Enrollee> enrolleeHash)
	{
		List<Enrollee> enrolleeList = new ArrayList<>();
		
		for (Map.Entry<String, Enrollee> entry : enrolleeHash.entrySet()) {
			enrolleeList.add(entry.getValue());
		}
		
		return enrolleeList;
	}
	
	private Enrollee setEnrolleeInfo(List<String> enrolleeIn)
	{
		Enrollee enrollee = new Enrollee();
		
		for(int iInner = 0; enrolleeIn.size() > iInner; iInner++)
		{
			switch(iInner)
			{
				case 0:
					enrollee.setUserId(enrolleeIn.get(iInner).trim());
					break;
				case 1:
					enrollee.setFirstName(enrolleeIn.get(iInner).trim());
					break;
				case 2:
					enrollee.setLastName(enrolleeIn.get(iInner).trim());
					break;
				case 3:
					try
					{
						enrollee.setVersion(Integer.parseInt(enrolleeIn.get(iInner).trim()));
					}
					catch(Exception e)
					{
						System.out.println("a version in the file was un parseable setting -1");
						enrollee.setVersion(-1);
					}
					break;
				case 4:
					enrollee.setCompany(enrolleeIn.get(iInner).trim());
					break;
			}
		}
		
		return enrollee;
	}

}

