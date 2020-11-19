package ui;

import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import persistence.JsonWriter;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class List extends JFrame implements ActionListener {

    private ToDoList tdl = new ToDoList();
    private JList list;
    private DefaultListModel listModel;
    private JPanel buttonPanel;
    private static final String addTask = "Add task";
    private static final String removeTask = "Remove task";
    private JTextField textField;

    public List() {
        super("Todo List");

        createAndAddTasks();

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisible(true);
        list.setSize(600, 400);
//        list.setSelectedIndex(0);
//        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);


        buttonPanel = new JPanel();
//      buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
//      buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        createAddButton();
        createRemoveButton();
        createLoadButton();
        createSaveButton();

        textField = new JTextField(20); // why do these 3 lines of code not compile
        textField.setBounds(100, 20, 165, 25); // when I put them right underneath
        buttonPanel.add(textField);     // super("Todo List");

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createAndAddTasks() {
        Task t1 = new Task("Hello", false);
        tdl.addTask(t1);
        Task t2 = new Task("Math", false);
        tdl.addTask(t2);
        Task t3 = new Task("Stats", false);
        tdl.addTask(t3);

        listModel = new DefaultListModel();
        listModel.addElement(t1.getTask());
        listModel.addElement(t2.getTask());
        listModel.addElement(t3.getTask());
        listModel.removeElement(t1.getTask());
        list = new JList(listModel);

    }

    public void createAddButton() {
        JButton addButton = new JButton(addTask);
        buttonPanel.add(addButton);
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
    }

    public void createRemoveButton() {
        JButton removeButton = new JButton(removeTask);
        buttonPanel.add(removeButton);
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);
    }

    public void createLoadButton() {
        JButton loadButton = new JButton("Load Data");
        buttonPanel.add(loadButton);
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
    }

    public void createSaveButton() {
        JButton saveButton = new JButton("Save Data");
        buttonPanel.add(saveButton);
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
    }

    public static void main(String[] args) {
        new List();
    }

    static void playSound(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            System.out.println("Exception thrown");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel label = new JLabel();
        buttonPanel.add(label);
        if (e.getActionCommand().equalsIgnoreCase("remove")) {
            try {
                int index = list.getSelectedIndex();
                listModel.remove(index);
            } catch (ArrayIndexOutOfBoundsException xx) {
                label.setText("");
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("add")) {
            listModel.addElement(textField.getText());
            File theBox = new File("C:\\Users\\hello\\IdeaProjects\\CPSC210\\labs\\project_y5y7z\\src\\Roddy2.wav");
            File theBox2 = new File("./src/Roddy2.wav");
            playSound(theBox2);
            textField.setText("");
        }
    }


}
