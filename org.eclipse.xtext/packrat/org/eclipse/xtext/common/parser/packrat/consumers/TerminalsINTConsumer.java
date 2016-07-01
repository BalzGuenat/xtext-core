/*
* generated by Xtext
*/
package org.eclipse.xtext.common.parser.packrat.consumers;

import org.eclipse.xtext.parser.packrat.consumers.ITerminalConsumerConfiguration;
import org.eclipse.xtext.parser.packrat.consumers.AbstractRuleAwareTerminalConsumer;
import org.eclipse.xtext.parser.packrat.consumers.ConsumeResult;


public final class TerminalsINTConsumer extends AbstractRuleAwareTerminalConsumer {

	public TerminalsINTConsumer(ITerminalConsumerConfiguration configuration) {
		super(configuration);
	}
	
	@Override
	protected int doConsume() {
		return consumeCharacterRange$1() ? ConsumeResult.SUCCESS : ConsumeResult.EMPTY_MATCH;
	}

	protected boolean consumeCharacterRange$1() {
		if (doConsumeCharacterRange$1()) {
			while(doConsumeCharacterRange$1()) {}
			return true;
		}
		return false;
	}

	protected boolean doConsumeCharacterRange$1() {
		return readCharBetween('0', '9');
	}
	
}