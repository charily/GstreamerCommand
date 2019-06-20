import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerMp4SRTPCommand {
    //MP4文件转换为srtp流
    public static void main(String[] args) {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("filesrc location=1560995824167625.mp4 "
                                                        +"! decodebin "
                                                        +"! x264enc tune=zerolatency "
                                                        +"! rtph264pay "
                                                        +"! application/x-rtp,payload=(int)103,ssrc=(uint)112233 "
                                                        +"! srtpenc key=\"4142434445464748494A4B4C4D4E4F505152535455565758595A31323334\" "
                                                        +"  rtp-cipher=aes-128-icm rtp-auth=hmac-sha1-80 "
                                                        +"  rtcp-cipher=aes-128-icm rtcp-auth=hmac-sha1-80 "
                                                        +"! udpsink host=127.0.0.1 port=9004 ");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();
    }
}
/*
* PEER_V=9004 PEER_IP=127.0.0.1 \
SELF_PATH="$HOME/videos/bbb" \
SELF_VSSRC=112233 \
SELF_KEY="4142434445464748494A4B4C4D4E4F505152535455565758595A31323334" \
bash -c 'gst-launch-1.5 -e \
filesrc location="$SELF_PATH" ! decodebin \
! x264enc tune=zerolatency \
! rtph264pay ! "application/x-rtp,payload=(int)103,ssrc=(uint)$SELF_VSSRC" \
! srtpenc key="$SELF_KEY" \
rtp-cipher="aes-128-icm" rtp-auth="hmac-sha1-80" \
rtcp-cipher="aes-128-icm" rtcp-auth="hmac-sha1-80" \
! udpsink host=$PEER_IP port=$PEER_V'*/
