package meixi.github.tobato.fastdfs.proto.storage;

import java.util.Set;

import meixi.github.tobato.fastdfs.domain.MataData;
import meixi.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import meixi.github.tobato.fastdfs.proto.storage.internal.StorageGetMetadataRequest;
import meixi.github.tobato.fastdfs.proto.storage.internal.StorageGetMetadataResponse;

/**
 * 设置文件标签
 * 
 * @author tobato
 *
 */
public class StorageGetMetadataCommand extends AbstractFdfsCommand<Set<MataData>> {

    /**
     * 设置文件标签(元数据)
     * 
     * @param groupName
     * @param path
     * @param metaDataSet
     * @param type
     */
    public StorageGetMetadataCommand(String groupName, String path) {
        this.request = new StorageGetMetadataRequest(groupName, path);
        // 输出响应
        this.response = new StorageGetMetadataResponse();
    }

}
