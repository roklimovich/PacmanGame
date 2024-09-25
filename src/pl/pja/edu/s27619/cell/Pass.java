package pl.pja.edu.s27619.cell;

import pl.pja.edu.s27619.content.BasicClass;
import pl.pja.edu.s27619.content.Pacman;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Pass extends Cell{
//    private BasicClass content;
    private LinkedList<BasicClass> contentOfPass;


    public Pass(BasicClass content) {
        contentOfPass = new LinkedList<>();
        setTopContent(content);
    }

    public BasicClass getTopContent() {
        if (contentOfPass.size() > 0) {
            return contentOfPass.getLast();
        } else {
            return null;
        }
    }

    public List<BasicClass> getContentOfPass() {
        return contentOfPass;
    }

    public void setTopContent(BasicClass content) {
        contentOfPass.add(content);
    }
}
