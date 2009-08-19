/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.XtextSwitch;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.google.common.collect.Sets;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class XtextRuleInspector<Result, RuleType extends AbstractRule> extends XtextSwitch<Result> {

	private final ValidationMessageAcceptor acceptor;

	private Collection<AbstractRule> visitedRules;
	
	public XtextRuleInspector(ValidationMessageAcceptor acceptor) {
		this.acceptor = acceptor;
		visitedRules = Sets.newHashSet();
	}
	
	public void inspect(RuleType rule) {
		if (!canInspect(rule))
			return;
		visitedRules.add(rule);
		Result r = doSwitch(rule.getAlternatives());
		handleResult(r, rule);
	}
	
	protected boolean canInspect(RuleType rule) {
		// clients may override
		return true;
	}

	protected void handleResult(Result r, RuleType rule) {
		// clients may override
	}

	public void acceptError(String message, EObject object, Integer feature) {
		acceptor.acceptError(message, object, feature);
	}

	public void acceptWarning(String message, EObject object, Integer feature) {
		acceptor.acceptWarning(message, object, feature);
	}
	
	public boolean addVisited(AbstractRule rule) {
		return visitedRules.add(rule);
	}
	
	public boolean removeVisited(AbstractRule rule) {
		return visitedRules.remove(rule);
	}
	
	protected String getTypeRefName(TypeRef typeRef) {
		String simpleName = GrammarUtil.getTypeRefName(typeRef);
		if (typeRef.getMetamodel() != null && !Strings.isEmpty(typeRef.getMetamodel().getAlias()))
			return typeRef.getMetamodel().getAlias() + "::" + simpleName;
		return simpleName;
	}
}
