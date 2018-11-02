package meixi.github.tobato.fastdfs.proto.storage.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

import meixi.github.tobato.fastdfs.domain.MataData;
import meixi.github.tobato.fastdfs.proto.FdfsResponse;
import meixi.github.tobato.fastdfs.proto.mapper.MetadataMapper;

/**
 * 列出分组信息执行结果
 * 
 * @author tobato
 *
 */
public class StorageGetMetadataResponse extends FdfsResponse<Set<MataData>> {

    /**
     * 解析反馈内容
     */
    @Override
    public Set<MataData> decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        byte[] bytes = new byte[(int) getContentLength()];
        int contentSize = in.read(bytes);
        if (contentSize != getContentLength()) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }
        return MetadataMapper.fromByte(bytes, charset);

    }

}
