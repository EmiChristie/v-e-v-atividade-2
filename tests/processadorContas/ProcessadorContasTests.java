package processadorContas;

import org.junit.Test;
import processadorContas.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProcessadorContasTests {
    Conta conta1;


    @Test
    public void setup(){
        Date dataConta1 = new Date(124,02,05);
        conta1 = new Conta(1, dataConta1,500.00);
    }
}