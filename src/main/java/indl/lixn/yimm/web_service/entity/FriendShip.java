package indl.lixn.yimm.web_service.entity;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:27
 **/
public class FriendShip {

    private Integer id;

    private String msId;

    /** 好友关系发起人 */
    private String from;

    /** 好友关系接收人 */
    private String to;

    /** 好友关系是否被同意 */
    private boolean agree;

    /** 备注 */
    private String remark;

    /** 好友关系创建时间 */
    private Long establishTime;

    /** 好友关系取消时间 default = null */
    private Long cancelTime;

    public String getMsId() {
        return msId;
    }
}
