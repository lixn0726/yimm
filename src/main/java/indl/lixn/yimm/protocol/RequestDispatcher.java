package indl.lixn.yimm.protocol;

/**
 * @author lixn
 * @description 请求分发
 * @date 2023/01/05 10:07
 **/
public interface RequestDispatcher<In> {

    /**
     * 根据流入系统的请求分发到对应的处理业务
     * @param incomeRequest 请求
     */
    void dispatch(In incomeRequest);

}
