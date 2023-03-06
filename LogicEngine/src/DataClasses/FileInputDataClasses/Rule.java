package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTRule;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Rule {
    public enum eType{HARD,SOFT}

    private eRules m_eRule;
    private eType m_Type;
    private Integer m_TotalHours;

    public Rule(ETTRule i_ETTRule)
    {
        m_eRule=eRules.valueOf(i_ETTRule.getETTRuleId().toUpperCase());
        m_Type=eType.valueOf(i_ETTRule.getType().toUpperCase());
        String configuration=i_ETTRule.getETTConfiguration();
        extractConfiguration(configuration);
    }

    public Rule(Rule i_Rule)
    {
        m_eRule=eRules.valueOf(i_Rule.getId().name().toUpperCase());
        m_Type=eType.valueOf(i_Rule.getType().name().toUpperCase());
        m_TotalHours=i_Rule.getTotalHours();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return m_eRule.equals(rule.m_eRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_eRule);
    }

    public eRules getId() {
        return m_eRule;
    }

    public eType getType() {
        return m_Type;
    }

    public Integer getTotalHours() {
        return m_TotalHours;
    }

    public void extractConfiguration(String i_Configuration)
    {
        if(i_Configuration!=null) {
            Scanner in = new Scanner(i_Configuration.toString()).useDelimiter("[^0-9]+");
            m_TotalHours = in.nextInt();
            in.close();
        }
        else
            m_TotalHours=0;
    }
}
