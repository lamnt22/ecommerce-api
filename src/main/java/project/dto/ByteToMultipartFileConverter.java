package project.dto;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
public class ByteToMultipartFileConverter {
	public static MultipartFile convert(byte[] bytes, String fileName) throws IOException {
        // Create a MultipartFile object
        return new MultipartFile() {
			
			@Override
			public void transferTo(File dest) throws IOException, IllegalStateException {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("transferTo() is not supported for ByteToMultipartFileConverter");
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return bytes == null || bytes.length == 0;
			}
			
			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return bytes.length;
			}
			
			@Override
			public String getOriginalFilename() {
				// TODO Auto-generated method stub
				return fileName; 
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "file";
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return new ByteArrayInputStream(bytes);
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return "application/octet-stream";
			}
			
			@Override
			public byte[] getBytes() throws IOException {
				// TODO Auto-generated method stub
				return bytes;
			}
		};
    }
}
