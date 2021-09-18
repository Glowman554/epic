package io.github.glowman554.epic;

import java.io.IOException;
import java.util.ArrayList;

import io.github.glowman554.epic.lexer.Lexer;
import io.github.glowman554.epic.lexer.LexerToken;
import io.github.glowman554.epic.parser.Parser;
import io.github.glowman554.epic.parser.ParserNodeType;
import io.github.glowman554.epic.parser.ParserToken;
import io.github.glowman554.epic.parser.nodes.ListNode;
import io.github.glowman554.epic.utils.ArgParser;

public class Main
{
	public static void main(String[] args)
	{

		String[] test_args = { "java", "--test=1", "--test=2", "--other-test", "--other-test=1", "--test123", "--test123=aa" };
		ArgParser arg_parser = new ArgParser(test_args);
		arg_parser.parse();
		
		while (true)
		{
			try
			{
				System.out.println(arg_parser.consume_option("--test"));
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
				break;
			}
		}
		
		System.out.println(arg_parser.consume_option("--other-test", "a"));
		System.out.println(arg_parser.consume_option("--other-test", "a"));
		
		System.out.println(arg_parser.is_option("--test123"));
		System.out.println(arg_parser.consume_option("--test123", "test"));
		System.out.println(arg_parser.consume_option("--test123", "test"));
		
		try
		{
			String code = FileUtils.readFile("test.epic");

			Lexer lexer = new Lexer(code);

			ArrayList<LexerToken> tokens = lexer.tokenize();

			for (LexerToken token : tokens)
			{
				System.out.println(token.toString());
			}

			Parser parser = new Parser(tokens);

			ParserToken root = parser.parse();
			System.out.println(root.error);

			if (root.node.getType() == ParserNodeType.list_node)
			{
				for (Object i : ((ListNode) root.node).list)
				{
					System.out.println(i.getClass());
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

