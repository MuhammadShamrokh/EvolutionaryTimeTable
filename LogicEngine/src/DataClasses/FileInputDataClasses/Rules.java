package DataClasses.FileInputDataClasses;

import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;
import DataTransferClasses.EvolutionEngineData;
import DataTransferClasses.RuleFileData;
import DataTransferClasses.TeacherData;
import ParsedClasses.ETTRule;
import ParsedClasses.ETTRules;

import java.util.*;

public class Rules {
    private List<Rule> m_RulesList;
    private Integer m_HardRulesWeight;
    private Integer m_SoftRulesWeight;

    public Rules(ETTRules i_ETTRules)
    {
        m_HardRulesWeight=i_ETTRules.getHardRulesWeight();
        m_SoftRulesWeight=100-m_HardRulesWeight;
        m_RulesList=new ArrayList<>();
        List<ETTRule> ettRules = i_ETTRules.getETTRule();
        for(ETTRule rule:ettRules)
        {
            m_RulesList.add(new Rule(rule));
        }
    }

    public Rules(Rules i_Rules)
    {
        m_HardRulesWeight=i_Rules.getHardRulesWeight();
        m_SoftRulesWeight=100-m_HardRulesWeight;
        m_RulesList=new ArrayList<>();
        List<Rule> rules = i_Rules.getRulesList();
        for(Rule rule:rules)
        {
            m_RulesList.add(new Rule(rule));
        }
    }

    public Set<RuleFileData> getRulesData()
    {
        Set<RuleFileData> retRuleSet=new HashSet<>();
        m_RulesList.forEach(rule->retRuleSet.add(new RuleFileData(rule)));
        return retRuleSet;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "m_RulesList=" + m_RulesList +
                ", m_HardRulesWeight=" + m_HardRulesWeight +
                '}';
    }

    public List<Rule> getRulesList() {
        return m_RulesList;
    }
    public Map<String, String> getRulesNames2TypeMap(){
        Map<String,String> retMap=new HashMap<>();
        for(Rule r:m_RulesList)
        {
            retMap.put(r.getId().toString(),r.getType().toString());
        }
        return retMap;
    }
    public Integer getHardRulesWeight() {
        return m_HardRulesWeight;
    }

    public void calculateFitnesses(Generation i_Generation, TimeTable i_TimeTable)
    {
        List<Parent> parentsList = i_Generation.getParentsList();
        for(Parent parent:parentsList)
        {
            if(parent.getFitness()==-1)
            {
                List<Integer> hardRulesScores=new ArrayList<>();
                List<Integer> softRulesScores=new ArrayList<>();
                for(Rule rule:m_RulesList)
                {
                    if(rule.getType()== Rule.eType.HARD)
                    {
                        hardRulesScores.add(rule.getId().CheckRule(parent,i_TimeTable,rule.getTotalHours()));
                    }
                    else
                    {
                        softRulesScores.add(rule.getId().CheckRule(parent,i_TimeTable,rule.getTotalHours()));
                    }
                }
                int hardAverage =hardRulesScores.stream().mapToInt(i->i).sum()/hardRulesScores.size();
                int softAverage =softRulesScores.stream().mapToInt(i->i).sum()/softRulesScores.size();
                double fitness=(hardAverage*m_HardRulesWeight/100)+(softAverage*m_SoftRulesWeight/100);
                parent.setFitness((int)fitness);
            }
        }

    }

    public void recheckBestSolution(Parent i_Parent,TimeTable i_TimeTable, EvolutionEngineData i_Data)
    {
        for(Rule rule:m_RulesList)
        {
            i_Data.addToRulesDataList(rule,rule.getId().CheckRule(i_Parent,i_TimeTable,rule.getTotalHours()));
        }
    }
}
