package meixi.github.tobato.fastdfs.proto.tracker;

import meixi.github.tobato.fastdfs.domain.StorageNode;
import meixi.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import meixi.github.tobato.fastdfs.proto.FdfsResponse;
import meixi.github.tobato.fastdfs.proto.tracker.internal.TrackerGetStoreStorageRequest;
import meixi.github.tobato.fastdfs.proto.tracker.internal.TrackerGetStoreStorageWithGroupRequest;

/**
 * 获取存储节点命令
 * 
 * @author tobato
 *
 */
public class TrackerGetStoreStorageCommand extends AbstractFdfsCommand<StorageNode> {

    public TrackerGetStoreStorageCommand(String groupName) {
        super.request = new TrackerGetStoreStorageWithGroupRequest(groupName);
        super.response = new FdfsResponse<StorageNode>() {
            // default response
        };
    }

    public TrackerGetStoreStorageCommand() {
        super.request = new TrackerGetStoreStorageRequest();
        super.response = new FdfsResponse<StorageNode>() {
            // default response
        };
    }

}
