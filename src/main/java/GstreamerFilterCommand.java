import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerFilterCommand {
    public static void main(String[] args) {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("videotestsrc !'video/x-raw-yuv,format=(fourcc)YUY2;video/x-raw-yuv,format=(fourcc)YV12'! xvimagesink");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();
    }
}
