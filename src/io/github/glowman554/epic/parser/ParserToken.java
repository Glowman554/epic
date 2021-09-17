package io.github.glowman554.epic.parser;

public class ParserToken
{
	public ParserNode node;
	public String error = null;
	public int last_registered_advance_count = 0;
	public int advance_count = 0;	
	public int to_reserve_count = 0;

	public void register_advancement()
	{
		this.last_registered_advance_count = 1;
		this.advance_count++;
	}

	public ParserNode register(ParserToken token)
	{
		this.last_registered_advance_count = this.advance_count;
		this.advance_count += token.advance_count;

		if (token.error != null)
		{
			this.error = token.error;
		}

		return token.node;
	}

	public ParserNode try_register(ParserToken token)
	{
		if (token.error != null)
		{
			this.to_reserve_count += token.advance_count;
			return null;
		}
		else
		{
			return this.register(token);
		}
	}

	public ParserToken success(ParserNode node)
	{
		this.node = node;
		return this;
	}

	public ParserToken failure(String error)
	{
		if (this.error == null || this.last_registered_advance_count == 0)
		{
			this.error = error;
		}
		return this;
	}
}
