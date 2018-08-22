package ch.litexo.bomb.web.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Path> images = Files.list(Paths.get("C:\\temp\\_01-Temp-06.02.2018\\_cam_test"))
                .filter(path -> {
                    if (path.toFile().isFile()) {
                        return path.toFile().getName().matches("(?ims).*[.]jpg");
                    }
                    return false;
                }).collect(Collectors.toList());
        List<ImageObject> data = new ArrayList<>();
        for (Path image : images.subList(0,1000)) {
            data.add(new ImageObject(
                    "jpg",
                    Base64.getEncoder().encodeToString(Files.readAllBytes(image)),
                    image.toFile().getName()
            ));
        }
        System.out.println(data.size());
        System.out.println(        new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(data.get(0)));
    }
}
