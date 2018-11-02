package meixi.github.tobato.fastdfs.service;

import java.io.InputStream;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import meixi.github.tobato.fastdfs.conn.ConnectionManager;
import meixi.github.tobato.fastdfs.domain.FileInfo;
import meixi.github.tobato.fastdfs.domain.MataData;
import meixi.github.tobato.fastdfs.domain.StorageNode;
import meixi.github.tobato.fastdfs.domain.StorageNodeInfo;
import meixi.github.tobato.fastdfs.domain.StorePath;
import meixi.github.tobato.fastdfs.proto.storage.DownloadCallback;
import meixi.github.tobato.fastdfs.proto.storage.StorageDeleteFileCommand;
import meixi.github.tobato.fastdfs.proto.storage.StorageDownloadCommand;
import meixi.github.tobato.fastdfs.proto.storage.StorageGetMetadataCommand;
import meixi.github.tobato.fastdfs.proto.storage.StorageQueryFileInfoCommand;
import meixi.github.tobato.fastdfs.proto.storage.StorageSetMetadataCommand;
import meixi.github.tobato.fastdfs.proto.storage.StorageUploadFileCommand;
import meixi.github.tobato.fastdfs.proto.storage.StorageUploadSlaveFileCommand;
import meixi.github.tobato.fastdfs.proto.storage.enums.StorageMetdataSetType;

/**
 * 基本存储客户端操作实现
 * 
 * @author tobato
 *
 */
@Component
public class DefaultGenerateStorageClient implements GenerateStorageClient {

    /** trackerClient */
    @Autowired
    protected TrackerClient trackerClient;

    /** connectManager */
    @Autowired
    protected ConnectionManager connectionManager;

    /** 日志 */
    protected static Logger LOGGER = LoggerFactory.getLogger(DefaultGenerateStorageClient.class);

    /**
     * 上传不支持断点续传的文件
     */
    @Override
    public StorePath uploadFile(String groupName, InputStream inputStream, long fileSize, String fileExtName) {
        StorageNode client = trackerClient.getStoreStorage(groupName);
        StorageUploadFileCommand command = new StorageUploadFileCommand(client.getStoreIndex(), inputStream,
                fileExtName, fileSize, false);
        return connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 上传从文件
     */
    @Override
    public StorePath uploadSlaveFile(String groupName, String masterFilename, InputStream inputStream, long fileSize,
            String prefixName, String fileExtName) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, masterFilename);
        StorageUploadSlaveFileCommand command = new StorageUploadSlaveFileCommand(inputStream, fileSize, masterFilename,
                prefixName, fileExtName);
        return connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 获取metadata
     */
    @Override
    public Set<MataData> getMetadata(String groupName, String path) {
        StorageNodeInfo client = trackerClient.getFetchStorage(groupName, path);
        StorageGetMetadataCommand command = new StorageGetMetadataCommand(groupName, path);
        return connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 覆盖metadata
     */
    @Override
    public void overwriteMetadata(String groupName, String path, Set<MataData> metaDataSet) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, path);
        StorageSetMetadataCommand command = new StorageSetMetadataCommand(groupName, path, metaDataSet,
                StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
        connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 合并metadata
     */
    @Override
    public void mergeMetadata(String groupName, String path, Set<MataData> metaDataSet) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, path);
        StorageSetMetadataCommand command = new StorageSetMetadataCommand(groupName, path, metaDataSet,
                StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_MERGE);
        connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 查询文件信息
     */
    @Override
    public FileInfo queryFileInfo(String groupName, String path) {
        StorageNodeInfo client = trackerClient.getFetchStorage(groupName, path);
        StorageQueryFileInfoCommand command = new StorageQueryFileInfoCommand(groupName, path);
        return connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 删除文件
     */
    @Override
    public void deleteFile(String groupName, String path) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, path);
        StorageDeleteFileCommand command = new StorageDeleteFileCommand(groupName, path);
        connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 下载整个文件
     */
    @Override
    public <T> T downloadFile(String groupName, String path, DownloadCallback<T> callback) {
        long fileOffset = 0;
        long fileSize = 0;
        return downloadFile(groupName, path, fileOffset, fileSize, callback);
    }

    /**
     * 下载文件片段
     */
    @Override
    public <T> T downloadFile(String groupName, String path, long fileOffset, long fileSize,
            DownloadCallback<T> callback) {
        StorageNodeInfo client = trackerClient.getFetchStorage(groupName, path);
        StorageDownloadCommand<T> command = new StorageDownloadCommand<T>(groupName, path, fileOffset, fileSize, callback);
        return connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    public void setTrackerClientService(TrackerClient trackerClientService) {
        this.trackerClient = trackerClientService;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

}
