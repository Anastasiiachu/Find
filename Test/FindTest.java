import Find_program.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static Find_program.Find.find;
public class FindTest {
    @Test
    public void test1() {
        /*test*/
        List<String> files = Arrays.asList("/Users/new/IdeaProjects/find/out/1/4/1.txt",
                "/Users/new/IdeaProjects/find/out/1/2/6/1.txt",
                "/Users/new/IdeaProjects/find/out/1/4/12/1.txt");
        try {
            assertEquals(files, find(new Flags(new String[]{"-r", "1.txt"}), new File("out/1")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        List<String> files = Arrays.asList("/Users/new/IdeaProjects/find/out/1/3/9/3.txt",
                "/Users/new/IdeaProjects/find/out/1/4/12/3.txt");
        try {
            assertEquals(files, find(new Flags(new String[]{"-r", "3.txt"}), new File("out/1")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            find(new Flags(new String[]{"-r", "2.txt"}), new File("out/2")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
