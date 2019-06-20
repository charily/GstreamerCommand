import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.lowlevel.MainLoop;


public class GstreamerCameraCommand {
    //调取摄像头生成流
    public static void main(String args[])
    {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("autovideosrc !autovideosink");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();

    }
}

