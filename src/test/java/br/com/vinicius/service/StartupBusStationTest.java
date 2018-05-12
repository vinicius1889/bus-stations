package br.com.vinicius.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StartupBusStationTest {


    @Autowired
    private StartupFile startupFile;

    @Test
    public void readFileTest() throws IOException {
        Assert.assertTrue(startupFile.isAlreadyRead());
    }
}
