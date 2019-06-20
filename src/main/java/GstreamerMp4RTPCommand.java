import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.Version;
import org.freedesktop.gstreamer.lowlevel.MainLoop;

public class GstreamerMp4RTPCommand {
    public static void main(String[] args) {
        Gst.init(new Version(1,16));
        Pipeline pipe = new Pipeline();
        pipe = (Pipeline)Gst.parseLaunch("filesrc location=1560995824167625.mp4"
                                                        +"! decodebin name=d"
                                                        +"d. ! quene ");
        pipe.play();
        MainLoop loop = new MainLoop();
        loop.run();
    }
}
/*
* PEER_A={KMS_AUDIO_PORT} PEER_V={KMS_VIDEO_PORT} PEER_IP={KMS_PUBLIC_IP} \
SELF_PATH="{PATH_TO_VIDEO_FILE}" \
SELF_A=5006 SELF_ASSRC=445566 \
SELF_V=5004 SELF_VSSRC=112233 \
bash -c 'gst-launch-1.0 -e \
  rtpbin name=r sdes="application/x-rtp-source-sdes,cname=(string)\"user\@example.com\"" \
  filesrc location="$SELF_PATH" ! decodebin name=d \
  d. ! queue ! audioconvert ! opusenc \
    ! rtpopuspay ! "application/x-rtp,payload=(int)96,clock-rate=(int)48000,ssrc=(uint)$SELF_ASSRC" \
    ! r.send_rtp_sink_0 \
  d. ! queue ! videoconvert ! x264enc tune=zerolatency \
    ! rtph264pay ! "application/x-rtp,payload=(int)103,clock-rate=(int)90000,ssrc=(uint)$SELF_VSSRC" \
    ! r.send_rtp_sink_1 \
  r.send_rtp_src_0 ! udpsink host=$PEER_IP port=$PEER_A bind-port=$SELF_A \
  r.send_rtcp_src_0 ! udpsink host=$PEER_IP port=$((PEER_A+1)) bind-port=$((SELF_A+1)) sync=false async=false \
  udpsrc port=$((SELF_A+1)) ! r.recv_rtcp_sink_0 \
  r.send_rtp_src_1 ! udpsink host=$PEER_IP port=$PEER_V bind-port=$SELF_V \
  r.send_rtcp_src_1 ! udpsink host=$PEER_IP port=$((PEER_V+1)) bind-port=$((SELF_V+1)) sync=false async=false \
  udpsrc port=$((SELF_V+1)) ! tee name=t \
    t. ! queue ! r.recv_rtcp_sink_1 \
    t. ! queue ! fakesink dump=true async=false'
      */