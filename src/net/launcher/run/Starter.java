package net.launcher.run;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.launcher.components.Frame;
import net.launcher.utils.BaseUtils;
import net.launcher.utils.ProcessUtils;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Starter extends Application {
	public static int memory = BaseUtils.getPropertyInt("memory", Settings.defaultmemory);
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("TestCraft");

		primaryStage.setScene(new Scene(root, 582, 321));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.getIcons().add(new Image(Starter.class.getResourceAsStream("workbench.png")));
		URL url = Starter.class.getResource("lol.wav");
		AudioClip clip= Applet.newAudioClip(url);
		try {
			clip.loop();
		} catch (Exception e) {
		}
		try {
			String jarpath = Starter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

			ArrayList<String> params = new ArrayList<String>();
			params.add(System.getProperty("java.home") + "/bin/java");
			if (System.getProperty("os.arch").contains("64")
					&& System.getProperty("sun.arch.data.model").equals("32")) {
				JOptionPane.showMessageDialog(Frame.main, "Рекомендуется использовать\njava 64 bit", "Предупреждение!",
						javax.swing.JOptionPane.ERROR_MESSAGE, null);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(Frame.main, e, "Ошибка запуска", javax.swing.JOptionPane.ERROR_MESSAGE, null);
			try {
				Class<?> af = Class.forName("java.lang.Shutdown");
				Method m = af.getDeclaredMethod("halt0", int.class);
				m.setAccessible(true);
				m.invoke(null, 1);
			} catch (Exception x) {
			}
		}

	}

	public static void main(String[] args) throws Exception {
			launch(args);
	}
}
