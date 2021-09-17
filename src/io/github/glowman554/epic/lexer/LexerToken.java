package io.github.glowman554.epic.lexer;

public class LexerToken
{
	public LexerTokenType type;
	public Object value;

	public LexerToken(LexerTokenType type, Object value)
	{
		this.type = type;
		this.value = value;
	}

	public LexerToken(LexerTokenType type)
	{
		this.type = type;
		this.value = null;
	}

	public boolean matches(LexerTokenType type, Object value)
	{
		return this.type == type && this.value.equals(value);
	}

	public boolean matches(LexerTokenType type)
	{
		return this.type == type;
	}

	@Override
	public String toString()
	{
		return String.format("LexerToken{%s: %s}", type, value);
	}
}