import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerMp4SRTPReceiverCommand {
    public static void main(String[] args) {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("udpsrc port=9004 "
                                        +"! application/x-srtp"
                                        +", payload=(int)103,ssrc=(uint)112233,roc=(uint)0"
                                        +", srtp-key=(buffer)4142434445464748494A4B4C4D4E4F505152535455565758595A31323334"
                                        +", srtp-cipher=(string)aes-128-icm,srtp-auth=(string)hmac-sha1-80"
                                        +", srtcp-cipher=(string)aes-128-icm,srtcp-auth=(string)hmac-sha1-80"
                                        +"! srtpdec "
                                        +"! rtph264depay "
                                        +"! decodebin "
                                        +"! autovideosink");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();
    }
}
/*
* PEER_VSSRC=112233 \
PEER_KEY="4142434445464748494A4B4C4D4E4F505152535455565758595A31323334" \
SELF_V=9004 \
SRTP_CAPS="payload=(int)103,ssrc=(uint)$PEER_VSSRC,roc=(uint)0, \
srtp-key=(buffer)$PEER_KEY, \
srtp-cipher=(string)aes-128-icm,srtp-auth=(string)hmac-sha1-80, \
srtcp-cipher=(string)aes-128-icm,srtcp-auth=(string)hmac-sha1-80" \
bash -c 'gst-launch-1.5 -e \
udpsrc port=$SELF_V ! "application/x-srtp,$SRTP_CAPS" ! srtpdec \
! rtph264depay ! decodebin ! autovideosink'
* */