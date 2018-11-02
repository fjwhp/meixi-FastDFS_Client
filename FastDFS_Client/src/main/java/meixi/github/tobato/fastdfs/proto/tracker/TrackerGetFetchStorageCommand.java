package meixi.github.tobato.fastdfs.proto.tracker;

import meixi.github.tobato.fastdfs.domain.StorageNodeInfo;
import meixi.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import meixi.github.tobato.fastdfs.proto.FdfsResponse;
import meixi.github.tobato.fastdfs.proto.tracker.internal.TrackerGetFetchStorageRequest;

/**
 * 获取源服务器
 * 
 * @author tobato
 *
 */
public class TrackerGetFetchStorageCommand extends AbstractFdfsCommand<StorageNodeInfo> {

    public TrackerGetFetchStorageCommand(String groupName, String path, boolean toUpdate) {
        super.request = new TrackerGetFetchStorageRequest(groupName, path, toUpdate);
        super.response = new FdfsResponse<StorageNodeInfo>() {
            // default response
        };
    }

}
