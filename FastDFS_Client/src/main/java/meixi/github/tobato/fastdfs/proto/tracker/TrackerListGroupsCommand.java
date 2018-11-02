package meixi.github.tobato.fastdfs.proto.tracker;

import java.util.List;

import meixi.github.tobato.fastdfs.domain.GroupState;
import meixi.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import meixi.github.tobato.fastdfs.proto.tracker.internal.TrackerListGroupsRequest;
import meixi.github.tobato.fastdfs.proto.tracker.internal.TrackerListGroupsResponse;

/**
 * 列出组命令
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsCommand extends AbstractFdfsCommand<List<GroupState>> {

    public TrackerListGroupsCommand() {
        super.request = new TrackerListGroupsRequest();
        super.response = new TrackerListGroupsResponse();
    }

}
