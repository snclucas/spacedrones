package org.spacedrones.utils.math.io.gui;

import org.spacedrones.utils.math.data.Text;

import javax.swing.*;

public class TextWindow extends JPanel {

  private Text text;

  public TextWindow(Text t) {
    text  = t;
    toWindow();
  }

  public TextWindow(String s) {
    text = new Text(s);
    toWindow();
  }

  private void toWindow() {
    JTextArea textArea = new JTextArea(text.getString());
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane);
  }

}