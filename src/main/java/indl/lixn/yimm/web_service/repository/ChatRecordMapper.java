package indl.lixn.yimm.web_service.repository;

import indl.lixn.yimm.web_service.entity.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:39
 **/
@Mapper
public interface ChatRecordMapper {

    void addChatRecord(ChatRecord record);

    void delChatRecords(Collection<ChatRecord> records);

    void delChatRecord(ChatRecord record);
}
