package ksr.project.project.gui;

import ksr.project.project.model.House;
import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.service.FuzzyService.Labels.Bedroom.Bedroom;
import ksr.project.project.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class Window {

    private final HouseService houseService;
    private final List<Bedroom> bedroomList;

    @Autowired
    public Window(HouseService houseService, List<Bedroom> bedroomList) {
        this.houseService = houseService;
        this.bedroomList = bedroomList;
    }

    public void showMainWindow() {
        JFrame frame = new JFrame("Spring Boot Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        JPanel panel = new JPanel(new BorderLayout());
        JTextField text = new JTextField("Spring Boot can be used with Swing apps");
        panel.add(text, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setVisible(true);
//        List<HouseEntity> houseArrayList = houseService.getAllHouses();
//
//        ArrayList<Double> memberships = new ArrayList<>();
//
//
//        houseArrayList.stream().forEach(houseEntity -> {
//            String liczbaPokoi = "Ten dom posiada ";
//            String liczba = "";
//
//            liczba = bedroomList.stream()
//                    .max(Comparator.comparing(bedroom -> bedroom.getMembership(houseEntity.getBedrooms())))
//                    .get().getDescription();
//
//            bedroomList.stream().forEach(bedroom -> memberships.add(bedroom.getMembership(houseEntity.getBedrooms())));
//
//            System.out.println(liczbaPokoi + liczba);

//        });

    }
}
