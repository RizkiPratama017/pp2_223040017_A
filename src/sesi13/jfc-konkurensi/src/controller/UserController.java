package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.User;
import model.UserMapper;
import view.UserPdf;
import view.UserView;

public class UserController {

    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !email.isEmpty()) {
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);
                        mapper.insertUser(user);
                        return null;
                    }

                    @Override
                    protected void done() {
                        JOptionPane.showMessageDialog(view, "User added successfully!");
                    }
                };
                worker.execute();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class RefreshListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<List<User>, Void> worker = new SwingWorker<>() {
                @Override
                protected List<User> doInBackground() {
                    return mapper.getAllUsers();
                }

                @Override
                protected void done() {
                    try {
                        List<User> users = get();
                        String[] userArray = users.stream()
                                .map(u -> u.getName() + " (" + u.getEmail() + ")")
                                .toArray(String[]::new);
                        view.setUserList(userArray);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "Error refreshing user list: " + ex.getMessage());
                    }
                }
            };
            worker.execute();
        }
    }

    class ExportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    List<User> users = mapper.getAllUsers();
                    pdf.exportPdf(users);
                    return null;
                }

                @Override
                protected void done() {
                    JOptionPane.showMessageDialog(view, "Export to PDF completed successfully!");
                }
            };
            worker.execute();
        }
    }
}
