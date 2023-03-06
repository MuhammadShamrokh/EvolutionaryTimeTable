package Tasks;

import AlgorithmClasses.eStoppingCondition;
import DataTransferClasses.ProgressData;
import Manager.LogicEngineManager;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.Collection;
import java.util.function.Consumer;

public class ActivateAlgoTask extends Task<Boolean> {

    private Consumer<ProgressData> m_Consumer;
    private Collection<eStoppingCondition> m_StoppingCondition;
    private LogicEngineManager m_Engine;
    private Integer m_ReqGeneration;
    private Integer m_PrintingReq;
    private Integer m_ReqFitness;
    private Integer m_ReqTimeInMinutes;

    public ActivateAlgoTask(Consumer<ProgressData> i_Consumer, Collection<eStoppingCondition> i_StoppingCondition, LogicEngineManager i_Engine
            , Integer i_ReqGeneration, Integer i_PrintingReq, Integer i_ReqFitness, Integer i_ReqTimeInMinutes)
    {
        m_Consumer=i_Consumer;
        m_StoppingCondition=i_StoppingCondition;
        m_Engine=i_Engine;
        m_ReqGeneration=i_ReqGeneration;
        m_PrintingReq=i_PrintingReq;
        m_ReqFitness=i_ReqFitness;
        m_ReqTimeInMinutes=i_ReqTimeInMinutes;
    }

    @Override
    protected Boolean call() throws Exception {
        updateMessage("Waiting for result...");
        m_Engine.ActivateAlgorithm(m_ReqGeneration,m_PrintingReq,m_ReqFitness,m_ReqTimeInMinutes,this::updateUIAlgoTask,m_StoppingCondition);
        return true;
    }

    public void updateUIAlgoTask(ProgressData i_Progress)
    {
        Platform.runLater(()->m_Consumer.accept(i_Progress));
        if(i_Progress.getGenerationMade()%m_PrintingReq==0 || m_Engine.getStopBoolean())
            updateMessage("After "+i_Progress.getGenerationMade()+" generations, The best fitness is: "+i_Progress.getFitness());
        updateTitle("Generations made: "+i_Progress.getGenerationMade().toString());
    }

}
