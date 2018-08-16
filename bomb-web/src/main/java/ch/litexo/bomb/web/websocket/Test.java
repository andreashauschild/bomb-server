package ch.litexo.bomb.web.websocket;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.apache.commons.lang3.builder.ToStringStyle.*;

/**
 * @author Andreas Hauschild
 */
public class Test {

    public int x = 10;

    public int y = 20;

    public Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    public static void main(String[] args) {


        System.out.println(ReflectionToStringBuilder.toString(new Test(),new MyStyle()             ));

    }

    public static class MyStyle extends ToStringStyle {
        MyStyle() {
            super();
            this.setUseClassName(false);
            this.setUseIdentityHashCode(false);
            this.setUseFieldNames(false);
            this.setContentStart(StringUtils.EMPTY);
            this.setContentEnd(StringUtils.EMPTY);
        }

        @Override
        protected String getArrayStart() {
            return "";
        }

        @Override
        protected String getArraySeparator() {
            return "";
        }

        @Override
        protected String getArrayEnd() {
            return "";
        }

        @Override
        protected String getFieldSeparator() {
            return "";
        }
    }
}
