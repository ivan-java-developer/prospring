package com.prospring.ch14

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("ruleEngine")
class RuleEngineImpl implements RuleEngine {
    private Logger logger = LoggerFactory.getLogger(RuleEngineImpl.class)

    @Override
    void run(Rule rule, Object object) {
        logger.info "Execute rule"

        def exit = false;

        rule.parameters.each {ArrayList params ->
            def paramIndex = 0
            def success = true
            if (!exit) {
                rule.conditions.each {
                    logger.info "Condition param index: " + paramIndex
                    success = success && it(object, params[paramIndex])
                    logger.info "Condition success: " + success
                    paramIndex++
                }
            }
            if (success && !exit) {
                rule.actions.each {
                    logger.info "Action param index: " + paramIndex
                    it(object, params[paramIndex])
                    paramIndex++
                }
                if (rule.singleHit) {
                    exit = true
                }
            }
        }
    }
}
