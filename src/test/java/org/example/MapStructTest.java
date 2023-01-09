package org.example;
import org.deep.copy.Man;
import org.deep.copy.ManDto;
import org.deep.copy.ManMapper;
import org.deep.copy.Son;
import org.junit.Test;

public class MapStructTest {
    @Test
    public void testMapStruct(){
        Man man = new Man("Jason", 18);
        man.setSon(new Son("Jason's son"));
        ManDto manDto = ManMapper.INSTANCES.toManDto(man);
        System.out.println(manDto);
    }
}
