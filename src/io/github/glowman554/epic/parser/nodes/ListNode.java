package io.github.glowman554.epic.parser.nodes;

import io.github.glowman554.epic.parser.ParserNode;
import io.github.glowman554.epic.parser.ParserNodeType;

public class ListNode implements ParserNode
{
	public Object[] list;

	public ListNode(Object[] list)
	{
		this.list = list;
	}

	@Override
	public ParserNodeType getType() {
		return ParserNodeType.list_node;
	}
}
