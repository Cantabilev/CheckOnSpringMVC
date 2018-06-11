import com.cms.domain.StudentInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/19 18:37
 */
public class Test {

    public static void main(String[] args){

        Test t = new Test();
        List<A> list = new ArrayList<>();
        t.test();

        System.out.println(list);

    }

    public void test(){
        List<A> list = new ArrayList<>();
        list.add(new A("1", 1));
        list.add(new A("2", 2));

        A a = new A("1", 1);

        List<Long> strings = new ArrayList<>();

        strings.add(2222L);
        strings.add(123456789L);
        String ss = "1";

        System.out.println(strings.indexOf(123456789L));
    }

    class A {
        private String code;
        private int state;

        public A(String code, int state) {
            this.code = code;
            this.state = state;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
