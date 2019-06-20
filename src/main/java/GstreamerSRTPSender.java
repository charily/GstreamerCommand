import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerSRTPSender {
    public static void main(String[] args) {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("udpsrc port=5004 "
                                    +"caps=\'application/x-srtp, payload=(int)8, ssrc=(uint)1356955624, "
                                    +"srtp-key=(buffer)012345678901234567890123456789012345678901234567890123456789, "
                                    +"srtp-cipher=(string)aes-128-icm, srtp-auth=(string)hmac-sha1-80, "
                                    +"srtcp-cipher=(string)aes-128-icm, srtcp-auth=(string)hmac-sha1-80\' "
                                    +"! srtpdec ! rtppcmadepay ! alawdec ! pulsesink");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();
    }
}
/*
gst-launch-1.0 udpsrc port=5004
        caps='application/x-srtp, payload=(int)8, ssrc=(uint)1356955624,
        srtp-key=(buffer)012345678901234567890123456789012345678901234567890123456789,
        srtp-cipher=(string)aes-128-icm, srtp-auth=(string)hmac-sha1-80,
        srtcp-cipher=(string)aes-128-icm, srtcp-auth=(string)hmac-sha1-80' !
        srtpdec ! rtppcmadepay ! alawdec ! pulsesink
*/