import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerPlaybinCommand {
    public static void main(String[] args) {
            Gst.init(new Version(1,16));
            Pipeline pipe = new Pipeline();
            pipe = (Pipeline)Gst.parseLaunch("playbin " +
                    "uri=file:/Users/tianjiang/Documents/GitHub/GstreamerCommand/1560995824167625.mp4 ");
            pipe.play();
            MainLoop loop = new MainLoop();
            loop.run();

    }
}
