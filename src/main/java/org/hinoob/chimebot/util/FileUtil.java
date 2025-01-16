package org.hinoob.chimebot.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Scanner;

@UtilityClass
public class FileUtil {

    @SneakyThrows
    public String read(String path) {
        Scanner scanner = new Scanner(new File(path));
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return builder.toString().trim();
    }
}
