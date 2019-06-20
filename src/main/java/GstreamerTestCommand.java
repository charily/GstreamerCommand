import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerTestCommand {
    //生成Gstreamer测试流
    public static void main(String[] args) {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("videotestsrc" +"! autovideosink");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();
    }
}
