
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MainFrame {

    public static void main(String[] args) {
        //membuat frame utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh konkurensi di swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            //label untuk status
            JLabel statusLabel = new JLabel("tekan tombol untuk mulai tugas berat", JLabel.CENTER);

            //tombol untuk memulai proses
            JButton startButton = new JButton("Mulai");

            //progres bar
            JProgressBar progressBar = new JProgressBar();
            progressBar.setValue(0);
            progressBar.setStringPainted(true);

            //tambahkan komponen ke frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.add(startButton, BorderLayout.SOUTH);
            //tombol aksi latihan 1
            // startButton.addActionListener(e -> {
            //     //update progres bar 1% per detik
            //     for (int i = 0; i <= 60; i++) {
            //         progressBar.setValue(i);
            //         try {
            //             Thread.sleep(1000);
            //         } catch (Exception ex) {
            //             System.err.println(ex.getMessage());
            //         }
            //     }
            // });

            //tombol aksi latihan 2
            startButton.addActionListener(e -> {
                startButton.setEnabled(false); //nonaktifkan tombol saat proses berjalan
                statusLabel.setText("proses berjalan...");

                //buat swingworker untuk menanangani tugas berat
                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50); //simulasi delay
                            publish(i); //perbarui progres
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        //perbarui progres bar
                        int latesProgress = chunks.get(chunks.size() - 1);
                        progressBar.setValue(latesProgress);
                    }

                    @Override
                    protected void done() {
                        //aksi setelah tugas selesai
                        startButton.setEnabled(true);
                        statusLabel.setText("proses selesai");
                    }
                };
                //jalankan swingworker
                worker.execute();
            });
            //tampilkan frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }
}
