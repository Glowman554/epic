package io.github.glowman554.epic.utils;

public class ArgParserNode
{
	public String option;
	public String value;
	
	public ArgParserNode(String option, String value)
	{
		this.option = option;
		this.value = value;
	}

	public ArgParserNode(String option)
	{
		this.option = option;
	}

	@Override
	public String toString()
	{
		return String.format("ArgParseNode[%s=%s]", option, value);
	}
}
