package ksr.project.project;

import ksr.project.project.gui.Window;
import ksr.project.project.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class SpringDesktopSampleApplication implements CommandLineRunner {

    private final Window window;

    @Autowired
    public SpringDesktopSampleApplication(Window window) {
        this.window = window;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringDesktopSampleApplication.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        window.showMainWindow();
    }
}

