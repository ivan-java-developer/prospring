import com.prospring.ch14.Rule
import com.prospring.ch14.RuleFactory
import org.joda.time.DateTime
import org.joda.time.Years
import org.springframework.stereotype.Component

@Component
class RuleFactoryImpl implements RuleFactory {
    Closure age = {
        birthDate -> Years.yearsBetween(birthDate, new DateTime()).getYears()
    }

    @Override
    Rule getAgeCategoryRule() {
        Rule rule = new Rule()
        rule.conditions = [
                {object, param -> age(object.birthDate) >= param},
                {object, param -> age(object.birthDate) <= param}
        ]

        rule.actions = [{object, param -> object.ageCategory = param}]

        rule.parameters = [
                [0, 10, 'Kid'],
                [11, 20, 'Youth'],
                [21, 30, 'Adult'],
                [31, 60, 'Middle-aged'],
                [60, 120, 'Old']
        ]

        return rule
    }
}
