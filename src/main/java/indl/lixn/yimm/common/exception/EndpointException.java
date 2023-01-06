package indl.lixn.yimm.common.exception;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 14:28
 **/
public class EndpointException extends RuntimeException {

    private ExceptionCodeEnum expCode;
    private String errorMessage;
    private Throwable innerCause;



}
