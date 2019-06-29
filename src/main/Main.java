package main;

import java.io.FileNotFoundException;

import processor.Processor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try 
		{
			new Processor("/Users/urdad/Desktop/Java/CVSReader/DevTest.csv");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File provided was not found. Please check file path and try again");
		}
		
	}
}
