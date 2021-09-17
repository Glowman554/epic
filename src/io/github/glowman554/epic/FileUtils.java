package io.github.glowman554.epic;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils
{
	public static String readFile(String file_name) throws IOException
	{
		FileReader fr = new FileReader(file_name);
		String file = "";

		int i;
		while ((i = fr.read()) != -1)
		{
			file += (char) i;
		}
		
		fr.close();
		return file;
	}

	public static void writeFile(String file_name, String file_contents) throws IOException
	{
		FileWriter fw = new FileWriter(file_name);
		fw.write(file_contents);
		fw.close();
	}
}
