package com.woutwoot.cch;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author woutwoot
 *         Created by on 31/05/2015 - 19:28.
 */
public class CCH {

    private File file;
    private List<String> lines = new LinkedList<>();

    public CCH(File file) {
        this.file = file;
    }

    /**
     * Adds a comment to a YML config file.
     */
    public boolean addComment(String path, String... comment) {
        int level = path.split("\\.").length;
        int line = getSectionLine(path);
        if (line != -1) {
            String pre = getPrefix(level) + "# ";
            for (int i = 0; i < comment.length; i++)
                insertLine(line + i, pre + comment[i]);
        } else {
            return false;
        }
        writeFile();
        return true;
    }

    /**
     * Writes the file to disk, only containing the newly added comments.
     */
    public void writeFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, false));
            for (String line : lines)
                pw.println(line);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void insertLine(int line, String s) {
        lines.add(line, s);
    }

    private int getSectionLine(String path) {
        String[] sections = path.split("\\.");
        String prefix = getPrefix(sections.length);
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (currentLine.startsWith(prefix + sections[sections.length - 1] + ":"))
                return i;
        }
        return -1;
    }

    private String getPrefix(int level) {
        int amount = (level - 1) * 2;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < amount; i++)
            sb.append(" ");
        return sb.toString();
    }

    /**
     * Reads the file into memory so that the contents can be manipulated.
     */
    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                if (!line.contains("#"))
                    lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
