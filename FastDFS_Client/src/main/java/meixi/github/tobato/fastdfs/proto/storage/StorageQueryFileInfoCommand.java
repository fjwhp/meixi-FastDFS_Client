package meixi.github.tobato.fastdfs.proto.storage;

import meixi.github.tobato.fastdfs.domain.FileInfo;
import meixi.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import meixi.github.tobato.fastdfs.proto.FdfsResponse;
import meixi.github.tobato.fastdfs.proto.storage.internal.StorageQueryFileInfoRequest;

/**
 * 文件删除命令
 * 
 * @author tobato
 *
 */
public class StorageQueryFileInfoCommand extends AbstractFdfsCommand<FileInfo> {

    /**
     * 文件上传命令
     * 
     * @param storeIndex 存储节点
     * @param inputStream 输入流
     * @param fileExtName 文件扩展名
     * @param size 文件大小
     * @param isAppenderFile 是否添加模式
     */
    public StorageQueryFileInfoCommand(String groupName, String path) {
        super();
        this.request = new StorageQueryFileInfoRequest(groupName, path);
        // 输出响应
        this.response = new FdfsResponse<FileInfo>() {
            // default response
        };
    }

}
