import com.cms.domain.AdminInfo;
import com.cms.domain.StudentInfo;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MainApp {


    public static int aMethod(int i)throws Exception
    {
        try{
            return i / 10;
        }
        catch (Exception ex)
        {
            throw new Exception("exception in a Method");
        } finally{
            System.out.printf("finally");
        }
    }

    public static void main(String[] args) {

        try
        {
            aMethod(0);
        }
        catch (Exception ex)
        {
            System.out.printf("exception in main");
        }
        System.out.printf("finished");

//        try {
//            String resource = "mybatis-config.xml";
//            Reader reader = Resources.getResourceAsReader(resource);
//            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
//            System.out.println("------------------------------"+sqlMapper.toString());
//            SqlSession sqlSession = sqlMapper.openSession();
//            List<StudentInfo> accountInfo = sqlSession.selectOne("com.cms.mapper.ClockingDtlMapper.list", new DefaultDynamicQuery(new FilterMap(), new OrderMap()));
//            System.out.println("name ->                   "+accountInfo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("----------the end--------------------");
    }
}
