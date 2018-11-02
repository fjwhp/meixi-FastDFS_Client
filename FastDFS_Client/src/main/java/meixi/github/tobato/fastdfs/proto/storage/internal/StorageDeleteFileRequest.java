package meixi.github.tobato.fastdfs.proto.storage.internal;

import meixi.github.tobato.fastdfs.proto.CmdConstants;
import meixi.github.tobato.fastdfs.proto.FdfsRequest;
import meixi.github.tobato.fastdfs.proto.OtherConstants;
import meixi.github.tobato.fastdfs.proto.ProtoHead;
import meixi.github.tobato.fastdfs.proto.mapper.DynamicFieldType;
import meixi.github.tobato.fastdfs.proto.mapper.FdfsColumn;

/**
 * 文件上传命令
 * 
 * @author tobato
 *
 */
public class StorageDeleteFileRequest extends FdfsRequest {

    /** 组名 */
    @FdfsColumn(index = 0, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    private String groupName;
    /** 路径名 */
    @FdfsColumn(index = 1, dynamicField = DynamicFieldType.allRestByte)
    private String path;

    /**
     * 删除文件命令
     * 
     * @param groupName
     * @param path
     */
    public StorageDeleteFileRequest(String groupName, String path) {
        super();
        this.groupName = groupName;
        this.path = path;
        this.head = new ProtoHead(CmdConstants.STORAGE_PROTO_CMD_DELETE_FILE);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}