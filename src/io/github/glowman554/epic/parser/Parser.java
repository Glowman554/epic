package io.github.glowman554.epic.parser;

import java.util.ArrayList;

import io.github.glowman554.epic.lexer.LexerToken;
import io.github.glowman554.epic.lexer.LexerTokenType;
import io.github.glowman554.epic.parser.nodes.ListNode;

public class Parser
{
	private ArrayList<LexerToken> tokens;
	private LexerToken current;
	private int token_index = -1;

	public Parser(ArrayList<LexerToken> tokens)
	{
		this.tokens = tokens;
		this.advance();
	}

	private void advance()
	{
		this.token_index++;

		if (this.token_index >= this.tokens.size())
		{
			this.current = null;
			return;
		}

		this.current = this.tokens.get(token_index);
	}

	private void update_current()
	{
		if (this.token_index >= 0 && this.token_index >= this.tokens.size())
		{
			this.current = tokens.get(this.token_index);
		}
		else
		{
			this.current = null;
		}
	}

	private void reverse(int amount)
	{
		this.token_index -= amount;
		this.update_current();
	}

	private void reverse()
	{
		this.reverse(1);
	}

	public ParserToken parse()
	{
		ParserToken result = this.statements();
		
		if (result.error == null && this.current.type == LexerTokenType.end_of_file)
		{
			return result.failure("Token cannot appear after previous tokens");
		}

		return result;
	}

	private ParserToken statements()
	{
		ParserToken result = new ParserToken();
		ArrayList<ParserNode> statements = new ArrayList<>();

		while (this.current.type == LexerTokenType.new_line)
		{
			result.register_advancement();
			this.advance();
		}

		ParserNode statement = result.register(this.statement());
		
		if (result.error != null)
		{
			return result;
		}

		statements.add(statement);

		boolean more_statements = true;
		while (true)
		{
			int newline_count = 0;
			while (this.current.type == LexerTokenType.new_line)
			{
				result.register_advancement();
				this.advance();
				newline_count++;
			}

			if (newline_count == 0)
			{
				more_statements = false;
			}

			if (!more_statements)
			{
				break;
			}

			statement = result.try_register(this.statement());

			if (statement == null)
			{
				this.reverse(result.to_reserve_count);
				more_statements = false;
				continue;
			}

			statements.add(statement);
		}

		return result.success(new ListNode((statements.toArray(new Object[0]))));
	}

	private ParserToken statement()
	{
		ParserToken result = new ParserToken();

		if (this.current.matches(LexerTokenType.identifier, "return"))
		{
			System.out.println("return");
		}

		if (this.current.matches(LexerTokenType.identifier, "function"))
		{
			System.out.println("function");
		}
		
		return result;
	}
}
