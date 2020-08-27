package core.tools.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonathan on 10/18/2015.
 * 08/27/2020 update: This file isn't used anywhere.
 */
public final class PluginFixer {

    public static Map<Path, List<String>> FILES = new HashMap<>();

    public static void main(String[] args) throws IOException {
        if (true) {
            return;
        }
        for (String l : Files.readAllLines(new File("fuck.txt").toPath())) {
            if (l.contains("$")) {
                continue;
            }
            String input = l;
            input = "Server/src." + input;
            input = input.replace(".", "/") + ".java";
            Path path = null;
            try {
                path = new File(input).toPath();
                List<String> lines = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                }

                boolean good = false;
                outerloop:
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    if ((line.contains("extends") || line.contains("implements")) && line.contains("class") && line.contains("{")) {
                        lines.add(i, "@InitializablePlugin");
                        for (int j = i; j > 0; j--) {
                            String im = lines.get(j);
                            if (im.contains("import")) {
                                lines.add(j, "import InitializablePlugin;");
                                good = true;
                                break outerloop;
                            }
                        }
                    }
                }
                if (!good) {
                    System.out.println("Couldnt do " + l + ", " + input);
                    System.exit(9);
                    continue;
                } else {
                    System.out.println("Did: " + input);
                }
                FILES.put(path, lines);
            } catch (Throwable t) {
                System.out.println("errored do " + l + ", " + input);
                FILES.remove(path);
                t.printStackTrace();
                System.exit(9);
            }
        }
        for (Path p : FILES.keySet()) {
            Files.write(p, FILES.get(p));
        }
    }
}

