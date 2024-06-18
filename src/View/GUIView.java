package View;

import Service.BruteForce;
import Service.Decode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static AppConstnt.ConstantsApp.*;
import static Service.Encode.encrypt;

public class GUIView {
    public static String Tempfile;

    public static void Initialization() {
        //Создание Главного окна
        JFrame window = new JFrame(NAME);
        window.setBounds(5, 5, 500, 500);
        window.setSize(600, 600);
        window.setLayout(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        //Иницыализацыя полей ввода !!!
        JTextField aField = new JTextField();
        JTextField keyEncrypt = new JTextField();
        JTextField keyDecrypt = new JTextField();
        aField.setBounds(20, 65, 250, 25);
        keyEncrypt.setBounds(180, 100, 50, 25);
        keyDecrypt.setBounds(180, 140, 50, 25);
        window.add(aField);
        window.add(keyEncrypt);
        window.add(keyDecrypt);

        //Encrypt Button
        JButton buttonEncrypt = new JButton(ENCRYPT);
        buttonEncrypt.setBounds(20, 100, 150, 30);
        buttonEncrypt.setBackground(Color.DARK_GRAY);
        buttonEncrypt.setForeground(Color.CYAN);
        window.add(buttonEncrypt);

        //Decrypt button
        JButton buttonDecrypt = new JButton(DECRYPT);
        buttonDecrypt.setBounds(20, 140, 150, 30);
        buttonDecrypt.setBackground(Color.DARK_GRAY);
        buttonDecrypt.setForeground(Color.CYAN);
        window.add(buttonDecrypt);

        //BruteForce button

        JButton buttonBruteForce = new JButton(BRUTE_FORCE);
        buttonBruteForce.setBounds(20, 180, 150, 30);
        buttonBruteForce.setBackground(Color.DARK_GRAY);
        buttonBruteForce.setForeground(Color.CYAN);
        window.add(buttonBruteForce);
        // Кнопка для выбора файла по умолчанию !!!
        JButton buttonDefaultFile = new JButton(DEFAULT);
        buttonDefaultFile.setBounds(390, 65, 140, 25);
        buttonDefaultFile.setBackground(Color.DARK_GRAY);
        buttonDefaultFile.setForeground(Color.CYAN);
        window.add(buttonDefaultFile);

        // Кнопка для выбора файла
        JButton selectFileButton = new JButton(SELECT_FILE);
        selectFileButton.setBounds(280, 65, 100, 25);
        window.add(selectFileButton);

        // label Выбора файла !!!
        JLabel label = new JLabel(ENTER_PATH);
        label.setBounds(20, 30, 300, 50);
        window.add(label);

        // label Для ввода ключа для шифрования !!!
        JLabel labelEncrypt = new JLabel(ENTER_KEY_ENCRYPT);
        labelEncrypt.setBounds(240, 90, 300, 50);
        window.add(labelEncrypt);

        // label Для ввода ключа для дешифровки
        JLabel labelDecrypt = new JLabel(ENTER_KEY_DECRYPT);
        labelDecrypt.setBounds(240, 130, 300, 50);
        window.add(labelDecrypt);

        // label для краткого вывода текста !
        JLabel labelResult = new JLabel(SHOW_RESULT);
        labelResult.setBounds(10, 30, 480, 450);
        window.add(labelResult);

        // Обработчик события для кнопки выбора файла
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    aField.setText(selectedFile.getAbsolutePath());
                    Tempfile = selectedFile.getAbsolutePath();
                }
            }
        });

        // ОбработчиК Кнопки Encrypt
        buttonEncrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = readFile(Tempfile);
                    int key = Integer.parseInt(keyEncrypt.getText());
                    // Логика шифрования
                    String encryptedText = encrypt(inputText, key);
                    labelResult.setText(SHOW_RESULT + encryptedText);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(window, ERROR);
                }
            }
        });
        // Кнопка Decrypt Исполнение
        buttonDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = readFile(Tempfile);
                    int key = Integer.parseInt(keyDecrypt.getText());
                    // Логика шифрования
                    String decryptedText = Decode.decrypt(inputText, key);
                    labelResult.setText(SHOW_RESULT + decryptedText);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(window, ERROR);
                }
            }
        });
        // Кнопка брут форсе Реализацыя
        buttonBruteForce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = readFile(Tempfile);
                    String decryptedText = BruteForce.bruteForce(inputText);
                    labelResult.setText(SHOW_RESULT + decryptedText);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(window, ERROR);
                }
            }
        });
        // Реализацыя кнопки ввода файла по умолчанию !!!
        buttonDefaultFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aField.setText("src/DefaultFile/Temptext.txt");
                Tempfile = "src/DefaultFile/Temptext.txt";
            }
        });

        window.setVisible(true);
    }

    // Метод для чтения данных из файла с использованием java.nio.file.Files
    private static String readFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

}
