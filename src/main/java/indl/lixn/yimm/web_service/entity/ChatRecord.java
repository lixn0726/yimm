package indl.lixn.yimm.web_service.entity;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:29
 **/
public class ChatRecord {

    /*
    1、userA -> userB    - from: uId  to: uId 单聊
    2、userA -> groupA   - from: uId  to: gId 群聊
    3、system -> userA   - from: system  to: uId 单人通知
    4、system -> groupA  - from: system  to: gId 群通知
     */

    private Integer id;

    private String from;

    private String to;

    private String content;

}
