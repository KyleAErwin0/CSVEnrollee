package main;

import java.io.FileNotFoundException;
import java.lang.NoClassDefFoundError;

import processor.Processor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			if(args.length == 2)
			{
					new Processor(args[0], args[1]);
			}
			else
			{
				System.out.println("Need to specifiy file to read and directory to write to");
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File provided was not found. Please check file path and try again");
		}

	}
}
