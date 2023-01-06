package LoginLombard;

import javax.media.*;
import javax.media.protocol.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebCamExamle extends JFrame {

    private DataSource ds;
    private Player p;
    private Image img;
    private JFrame tf = this;
    MediaLocator ml;

    public static void main(String[] args) {
        WebCamExamle t = new WebCamExamle();
        t.getCam();
    }

    public void getCam() {
        try {

            /* Grab the default web cam*/
            ml = new MediaLocator("vfw://0");

            /* Create my data source */
            ds = Manager.createDataSource(ml);

            requestFormatResolution(ds);

            /* Create & start my player */
            p = Manager.createRealizedPlayer(ds);

            p.start();

            Thread.currentThread().sleep(1000);

            /* code for creating a JFrame and adding the visual component to it */
            JFrame jfrm = new JFrame("Testing Webcam");
            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton btn = new JButton("Capture");
            jfrm.getContentPane().add(btn, BorderLayout.NORTH);
            final ImagePanel imgpanel = new ImagePanel();
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrameGrabbingControl fgc = (FrameGrabbingControl) p.getControl("javax.media.control.FrameGrabbingControl");
                    Buffer buf = fgc.grabFrame();
                    BufferToImage bti = new BufferToImage((VideoFormat) buf.getFormat());
                    img = bti.createImage(buf);
                    imgpanel.setImage(img);
                    String path = "C:\\Users\\uchral\\Documents\\NetBeansProjects\\Lombard\\asd.jpg";
                    File f = new File(path);
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException ex) {
                            System.err.println("Cannot be create file");
                        }
                    }
                    saveJPG(img, path);
                    JOptionPane.showMessageDialog(rootPane, "Succesfully");
                    /*p.close();
                    p.deallocate();
                    ml = null;
                    try {
                        ds.stop();
                        finalize();
                    } catch (Throwable ex) {
                        Logger.getLogger(WebCamExamle.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                }
            });
            if (p.getVisualComponent() != null) {
                jfrm.getContentPane().add(p.getVisualComponent(), BorderLayout.CENTER);
            }
            if (p.getControlPanelComponent() != null) {
                jfrm.getContentPane().add(p.getControlPanelComponent(), BorderLayout.SOUTH);
            }
            jfrm.pack();
            jfrm.setLocationRelativeTo(null);
            jfrm.setVisible(true);
            jfrm.setSize(320, 240);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean requestFormatResolution(DataSource ds) {
        if (ds instanceof CaptureDevice) {
            FormatControl[] fcs = ((CaptureDevice) ds).getFormatControls();
            for (FormatControl fc : fcs) {
                Format[] formats = ((FormatControl) fc).getSupportedFormats();
                for (Format format : formats) {
                    if ((format instanceof VideoFormat)
                            && (((VideoFormat) format).getSize().getHeight() <= 240)
                            && (((VideoFormat) format).getSize().getWidth() <= 320)) {
                        ((FormatControl) fc).setFormat(format);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void saveJPG(Image img, String s) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, null, null);

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(s);
        } catch (java.io.FileNotFoundException io) {
            System.out.println("File Not Found");
        }

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
        param.setQuality(0.5f, false);
        encoder.setJPEGEncodeParam(param);

        try {
            encoder.encode(bi);
            out.close();
        } catch (java.io.IOException io) {
            System.out.println("IOException");
        }
    }

    public WebCamExamle() {
        tf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                p.close();
                p.deallocate();
                p.stop();
                ml = null;
                try {
                    ds.stop();
                    finalize();
                } catch (Throwable ex) {
                    Logger.getLogger(WebCamExamle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}

class ImagePanel extends Panel {

    public Image myimg = null;

    public ImagePanel() {
        setLayout(null);
        setSize(320, 240);
    }

    public void setImage(Image img) {
        this.myimg = img;
        repaint();
    }

    public void paint(Graphics g) {
        if (myimg != null) {
            g.drawImage(myimg, 0, 0, this);
        }
    }
}