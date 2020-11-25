package ui;

import model.Task;
import model.ToDoList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Represents the GUI of a todolist
public class List extends JFrame implements ActionListener {

    private ToDoList tdl = new ToDoList();
    private JList list;
    private DefaultListModel listModel;
    private JPanel buttonPanel;
    private static final String addTask = "Add task";
    private static final String removeTask = "Remove task";
    private JTextField textField;
    private JScrollPane listScrollPane;

    // MODIFIES: this
    // EFFECTS: calls the JFrame superconstructor and sets the title to "Todo List"
    // Perform basic setup methods for list
    // Creates new listScrollPane and initializes Jpanel, and adding 4 buttons on that panel
    // Initializes textfield and adds that to buttonPanel
    public List() {
        super("Todo List");

        createAndAddTasks();

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisible(true);
        list.setSize(600, 400);
//        list.setSelectedIndex(0);
//        list.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(list);


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

    // MODIFIES: this
    // EFFECTS: Assigns and initializes 2 new tasks, then adds it to the list
    public void createAndAddTasks() {
        Task t2 = new Task("Math", false);
        tdl.addTask(t2);
        Task t3 = new Task("Stats", false);
        tdl.addTask(t3);
        listModel = new DefaultListModel();
        listModel.addElement(t2.getTask());
        listModel.addElement(t3.getTask());
        list = new JList(listModel);
    }

    // MODIFIES: this
    // EFFECTS: Assigns and initializes a new JButton, meant for adding tasks
    // Create an action command specific to the functionality of the button
    public void createAddButton() {
        JButton addButton = new JButton(addTask);
        buttonPanel.add(addButton);
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Assigns and initializes a new JButton, meant for removing tasks
    // Create an action command specific to the functionality of the button
    public void createRemoveButton() {
        JButton removeButton = new JButton(removeTask);
        buttonPanel.add(removeButton);
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Assigns and initializes a new JButton, meant for loading tasks
    // Create an action command specific to the functionality of the button
    public void createLoadButton() {
        JButton loadButton = new JButton("Load Data");
        buttonPanel.add(loadButton);
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Assigns and initializes a new JButton, meant for saving tasks
    // Create an action command specific to the functionality of the button
    public void createSaveButton() {
        JButton saveButton = new JButton("Save Data");
        buttonPanel.add(saveButton);
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Assigns and initializes a new clip, opens that clip and starts playing the sound
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

    public static void main(String[] args) {
        new List();
    }

    @Override
    // MODIFIES: this
    //EFFECTS: if ActionCommand is remove, get the index selected via mouse or arrow keys and remove it from the list.
    // if nothing is selected, throw ArrayIndexOutOfBoundsException, label is simply for filler, so that if the user
    // clicks remove but nothing is selected, an exception wouldn't destroy the GUI
    // if ActionCommand is add, add the text from the textField to list, then create and initialize wav file called
    // Roddy2 via the relative path. Call the playSound method, and set the textField to an empty string.
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

// CITATIONS:
// Took bits and pieces of how Lists work from the List Demos on the Oracle website, ui swing section in tutorials
// Learned how action commands work using the "Swing JLabel text change on the running application" on stackOverflow
// Learned how to play a sound clip from the Youtube video "Java: How to play .wav files"
// by JavaTutorials101