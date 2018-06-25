package mybatis.generator;

import java.util.List;

public interface BaseMapper<T extends BaseModel> {

    List<T> selectAll();

    int delete(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T fetch(Integer id);

    int update(T record);
}