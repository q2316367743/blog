package xyz.esion.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.esion.blog.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.esion.blog.entity.Notice
 * @author Esion
 * @since 2021/6/22
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}




