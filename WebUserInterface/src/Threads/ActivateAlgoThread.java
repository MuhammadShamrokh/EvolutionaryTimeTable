package Threads;

import DataTransferClasses.ProgressData;
import Manager.LogicEngineManager;
import java.util.function.Consumer;

public class ActivateAlgoThread extends Thread{

    private LogicEngineManager m_EngineManager;
    private Consumer<ProgressData> m_Consumer;
    private Integer m_PrintingReq;


    public ActivateAlgoThread(LogicEngineManager i_LogicEngineManager,Consumer<ProgressData> i_Consumer,Integer i_ShowEvery)
    {
        m_EngineManager =i_LogicEngineManager;
        m_Consumer=i_Consumer;
        m_PrintingReq=i_ShowEvery;
    }

    private void setPrintReq(Integer i_PrintingReq)
    {
        m_PrintingReq=i_PrintingReq;
    }

    @Override
    public void run()
    {
        m_EngineManager.ActivateAlgorithm(m_Consumer,m_PrintingReq);
    }

}
