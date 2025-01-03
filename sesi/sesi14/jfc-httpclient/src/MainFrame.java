
import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame {

    public static void main(String[] args) {
        final IOReactorConfig iOReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setIOReactorConfig(iOReactorConfig)
                .build();
        client.start();

        final HttpHost target = new HttpHost("672fbf9066e42ceaf15e9a9b.mockapi.io");
        final String requestUrl = "/api/contacts";

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("contoh http client swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JLabel statusLabel = new JLabel("tekan tombol untuk mulai mengunduh data", JLabel.CENTER);
            JButton startButton = new JButton("mulai");
            JProgressBar progressBar = new JProgressBar(0, 100);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel panel = new JPanel();
            panel.add(startButton);
            panel.add(progressBar);
            panel.setLayout(new FlowLayout());
            frame.add(panel, BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowClosing(WindowEvent e) {
                    client.close(CloseMode.GRACEFUL);
                    System.exit(0);
                }

                // Implement remaining methods as no-op
                public void windowOpened(WindowEvent e) {
                }

                public void windowClosed(WindowEvent e) {
                }

                public void windowIconified(WindowEvent e) {
                }

                public void windowDeiconified(WindowEvent e) {
                }

                public void windowActivated(WindowEvent e) {
                }

                public void windowDeactivated(WindowEvent e) {
                }
            });

            final SimpleHttpRequest request = SimpleRequestBuilder.get()
                    .setHttpHost(target)
                    .setPath(requestUrl)
                    .build();

            startButton.addActionListener(e -> {
                progressBar.setIndeterminate(true);
                startButton.setEnabled(false);
                statusLabel.setText("Proses berjalan...");
                textArea.setText("");
                client.execute(
                        SimpleRequestProducer.create(request),
                        SimpleResponseConsumer.create(),
                        new FutureCallback<>() {
                    @Override
                    public void completed(final SimpleHttpResponse response) {
                        System.out.println(request + "->" + new StatusLine(response));
                        System.out.println(response.getBodyText());

                        JSONParser parser = new JSONParser();
                        try {
                            @SuppressWarnings("unchecked")
                            JSONArray contacts = (JSONArray) parser.parse(response.getBodyText());
                            contacts.forEach(obj -> {
                                JSONObject contact = (JSONObject) obj;
                                String line = "Name: " + contact.get("name") + ", phone: " + contact.get("phone");
                                textArea.append(line + "\n");
                            });
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        progressBar.setIndeterminate(false);
                        startButton.setEnabled(true);
                        statusLabel.setText("proses selesai");
                    }

                    @Override
                    public void failed(final Exception ex) {
                        System.out.println(request + "->" + ex);
                        progressBar.setIndeterminate(false);
                        startButton.setEnabled(true);
                        statusLabel.setText("proses gagal");
                    }

                    @Override
                    public void cancelled() {
                        System.out.println(request + "cancelled");
                        progressBar.setIndeterminate(false);
                        startButton.setEnabled(true);
                        statusLabel.setText("proses dibatalkan");
                    }
                });
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
