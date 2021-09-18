package io.github.glowman554.epic.parser.nodes;

import io.github.glowman554.epic.parser.ParserNode;
import io.github.glowman554.epic.parser.ParserNodeType;

public class VarAssignmentNode implements ParserNode
{
	public String name;
	public ParserNode value;

	public VarAssignmentNode(String name, ParserNode value)
	{
		this.name = name;
		this.value = value;
	}

	@Override
	public ParserNodeType getType() {
		return ParserNodeType.var_assignment_node;
	}
	
}
