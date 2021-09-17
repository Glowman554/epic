package io.github.glowman554.epic.lexer;

import java.util.ArrayList;

public class Lexer
{
	public ArrayList<LexerToken> tokens = new ArrayList<>();
	
	private String code;
	private int index = -1;
	private String current;

	public Lexer(String code)
	{
		this.code = code;
		this.advance();
	}

	private void advance()
	{
		this.index++;

		if (index >= this.code.length())
		{
			this.current = null;
			return;
		}

		this.current = this.code.substring(this.index, this.index + 1);
	}

	public ArrayList<LexerToken> tokenize()
	{
		while (this.current != null)
		{
			if (Character.isLetter(this.current.charAt(0)))
			{
				tokens.add(this.get_identifier());
				continue;
			}

			if (Character.isDigit(this.current.charAt(0)))
			{
				tokens.add(this.get_number());
				continue;
			}

			switch (this.current.charAt(0))
			{
				case ' ':
				case '\t':
				case '\r':
					{
						this.advance();
					}
					break;
				
				case '\n':
				case ';':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.new_line));
					}
					break;
				
				case '+':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.plus));
					}
					break;
				
				case '-':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.minus));
					}
					break;
				
				case '*':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.multiply));
					}
					break;
				
				case '/':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.divide));
					}
					break;
				
				case '^':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.power));
					}
					break;

				case '(':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.left_paren));
					}
					break;
				
				case ')':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.right_paren));
					}
					break;

				case '{':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.left_brace));
					}
					break;
				
				case '}':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.right_brace));
					}
					break;

				case '=':
					{
						this.advance();
						if (this.current.charAt(0) == '=')
						{
							tokens.add(new LexerToken(LexerTokenType.equal_to));
							this.advance();
						}
						else
						{
							tokens.add(new LexerToken(LexerTokenType.assignment));
						}
					}
					break;
				
				case '!':
					{
						this.advance();
						if (this.current.charAt(0) == '=')
						{
							tokens.add(new LexerToken(LexerTokenType.not_equal_to));
							this.advance();
						}
						else
						{
							throw new IllegalArgumentException("Unexpected character '!'");
						}
					}
					break;
				
				case ',':
					{
						this.advance();
						tokens.add(new LexerToken(LexerTokenType.comma));
					}
					break;

				default:
					{
						System.out.println(String.format("Unexpected character: %s", this.current));
						this.advance();
					}
					break;
			}
		}

		this.tokens.add(new LexerToken(LexerTokenType.end_of_file));

		return this.tokens;
	}

	private LexerToken get_identifier()
	{
		String identifier = "";

		while (this.current != null && Character.isLetter(this.current.charAt(0)))
		{
			identifier += this.current;
			this.advance();
		}

		return new LexerToken(LexerTokenType.identifier, (Object) identifier);
	}

	private LexerToken get_number()
	{
		String number = "";
		int dot_count = 0;

		while (this.current != null && (Character.isDigit(this.current.charAt(0)) ||this.current.charAt(0) == '.'))
		{
			number += this.current;
			this.advance();

			if (this.current.charAt(0) == '.')
			{
				dot_count++;
			}
		}

		if (dot_count > 1)
		{
			throw new IllegalArgumentException("Invalid number: " + number);
		}

		if (dot_count == 1)
		{
			return new LexerToken(LexerTokenType._float, (Object) Double.parseDouble(number));
		}
		else
		{
			return new LexerToken(LexerTokenType.integer, (Object) Integer.parseInt(number));
		}
	}
}