package meixi.github.tobato.fastdfs.proto.tracker.internal;

import meixi.github.tobato.fastdfs.proto.CmdConstants;
import meixi.github.tobato.fastdfs.proto.FdfsRequest;
import meixi.github.tobato.fastdfs.proto.ProtoHead;

/**
 * 列出分组命令
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsRequest extends FdfsRequest {

    public TrackerListGroupsRequest() {
        head = new ProtoHead(CmdConstants.TRACKER_PROTO_CMD_SERVER_LIST_GROUP);
    }
}
