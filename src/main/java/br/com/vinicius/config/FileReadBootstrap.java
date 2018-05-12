package br.com.vinicius.config;

import br.com.vinicius.service.StartupFile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileReadBootstrap implements CommandLineRunner {

    private final StartupFile service;

    FileReadBootstrap(StartupFile service){
        this.service = service;
    }


    @Override
    public void run(String... args) throws Exception {
        service.readFile();
    }


}
